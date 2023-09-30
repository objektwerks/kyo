package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
import kyo.concurrent.fibers.Fibers
import kyo.direct.*
import kyo.ios.IOs
import kyo.loggers.{Logger, Loggers}

import scala.annotation.tailrec

final class ConcurrentEffectTest extends AnyFunSuite with Matchers:
  val logger: Logger = Loggers.init(getClass())

  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  test("fiber"):
    val fiber: Int > (Fibers with IOs) = Fibers.fork(factorial(4))
    Fibers.run(IOs.runLazy(fiber)).map(r => r shouldBe 24)