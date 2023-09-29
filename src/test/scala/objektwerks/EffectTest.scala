package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
import kyo.options.Options
import kyo.tries.Tries

import scala.util.Success

final class EffectTest extends AnyFunSuite with Matchers:
  test("empty"):
    val i: Int > Any = 1
    i.pure shouldBe 1

  test("widening"):
    val i: Int > Any = 1
    val io: Int > Options = i
    val iot: Int > (Options with Tries) = io
    Options.run(io) shouldBe Some(1)
    Tries.run(iot) shouldBe Success(1)