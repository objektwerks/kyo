package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
import kyo.concurrent.channels.{Channel, Channels}
import kyo.concurrent.fibers.{Fiber, Fibers}
import kyo.concurrent.queues.{Queue, Queues}
import kyo.direct.*
import kyo.ios.IOs

import scala.annotation.tailrec

final class ConcurrentEffectTest extends AnyFunSuite with Matchers:
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  test("fiber"):
    val fiber: Fiber[Int] = Fibers.value( factorial(4) )
    fiber.onComplete(f => f shouldBe 24) // Other Fiber code compiles, but doesn't evaluate!

  test("queue"):
    val queue: Queue[Int] > IOs = Queues.bounded(capacity = 1)

    val offer: Boolean > IOs = queue.map(_.offer(1))
    IOs.run(offer) shouldBe true

    val poll: Option[Int] > IOs = queue.map(_.poll)
    IOs.run(poll) shouldBe None // Should be Some(1)

  test("channel"):
    val channel: Channel[Int] > IOs = Channels.init(capacity = 1)
    val channelInt: Channel[Int] = IOs.run(channel)
    val fiber: Int > (Fibers with IOs) = defer {
      await( channelInt.put(1) )
      val i = await( channelInt.take )
      i shouldBe 1 // Never evaluates!
      i
    }
    val result: Fiber[Int] >IOs = Fibers.run(IOs.runLazy(fiber))
    Fibers.run(IOs.runLazy(result)) // Dead end! Other Channel code compiles, but doesn't evaluate!