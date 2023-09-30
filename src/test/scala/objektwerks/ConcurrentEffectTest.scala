package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*

final class ConcurrentEffectTest extends AnyFunSuite with Matchers:
  test("todo"):
    val i: Int > Any = 1
    i.pure shouldBe 1