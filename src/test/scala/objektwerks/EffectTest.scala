package objektwerks

import kyo.*

import munit.FunSuite

/**
  * Version 0.11.0 destroyed this test!
  * Rebuilding will take time.
  */
final class EffectTest extends FunSuite:
  test("non-effect > eval"):
    val i: Int < Any = 1
    val j: Int = i.eval
    assert(j == 1)