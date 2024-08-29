package objektwerks

import munit.FunSuite

final class EffectTest extends FunSuite:
  test("todo"):
    val message = "*** The version 0.11.0 upgrade destroyed this test!"
    println(message)
    assert(message.nonEmpty)
