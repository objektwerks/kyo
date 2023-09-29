package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*

class EffectTest extends AnyFunSuite with Matchers:
  test("empty") {
    val i: Int > Any = 1
    i.pure shouldBe 1
  }