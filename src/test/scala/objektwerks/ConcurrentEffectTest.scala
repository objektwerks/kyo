package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
import kyo.concurrent.fibers.{Fiber, Fibers}

import scala.annotation.tailrec

final class ConcurrentEffectTest extends AnyFunSuite with Matchers:
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  test("fiber"):
    val fiber: Fiber[Int] = Fibers.value( factorial(4) )
    fiber.onComplete(f => f shouldBe 24)