package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
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
    val updated: Boolean > IOs = queue.map(_.offer(1))
    IOs.run(updated) shouldBe true