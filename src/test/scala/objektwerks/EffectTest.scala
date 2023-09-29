package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*

class EffectTest extends AnyFunSuite with Matchers:
  test(">") {
    val a: Int > Any = 1
    a.pure shouldBe 1
  }