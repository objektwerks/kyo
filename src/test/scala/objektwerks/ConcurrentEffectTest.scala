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

    val put: Unit > (Fibers with IOs) = channel.map(_.put(1))
    val putFiber: Fiber[Unit] > IOs = Fibers.run(IOs.runLazy(put))
    for
      u: Unit <- put
      fu: Fiber[Unit] <- putFiber
    yield
      u shouldBe () // Never evaluated!
      fu.get shouldBe () // Never evaluated!
    defer {
      val i = await(put)
      i shouldBe ()
    }

    val take: Int > (Fibers with IOs) = channel.map(_.take)
    val takeFiber: Fiber[Int] > IOs = Fibers.run(IOs.runLazy(take))
    for
      i: Int <- take
      fi: Fiber[Int] <- takeFiber
    yield
      i shouldBe 1 // Never evaluated!
      fi shouldBe 1 // Never evaluated!
    defer {
      val i: Int = await(take)
      i shouldBe 1 // Never evaluated!
    }