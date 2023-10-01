package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
import kyo.concurrent.channels.{Channel, Channels}
import kyo.concurrent.fibers.{Fiber, Fibers}
import kyo.concurrent.queues.{Queue, Queues}
import kyo.ios.IOs

import scala.annotation.tailrec

final class ConcurrentEffectTest extends AnyFunSuite with Matchers:
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  test("fiber"):
    val fiber: Fiber[Int] = Fibers.value( factorial(4) )
    fiber.onComplete(f => f shouldBe 24)

  test("queue"):
    val queue: Queue[Int] > IOs = Queues.bounded(capacity = 1)

    val offer: Boolean > IOs = queue.map(_.offer(1))
    IOs.run(offer) shouldBe true

    val poll: Option[Int] > IOs = queue.map(_.poll)
    IOs.run(poll) shouldBe None // Should be Some(1)

  test("channel"):
    val channel: Channel[Int] > IOs = Channels.init(capacity = 1)

    val put: Unit > (Fibers with IOs) = channel.map(_.put(1))
    Fibers.run(IOs.runLazy(put))

    val take: Int > (Fibers with IOs) = channel.map(_.take)
    val fiber: Fiber[Int] > IOs = Fibers.run(IOs.runLazy(take))
    for
      fi <- fiber
    yield fi.onComplete(i => i shouldBe 1) // Never evaluated!
