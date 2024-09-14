package objektwerks

import kyo.*

import munit.FunSuite

/**
  * Version 0.11.0 destroyed this test!
  * Rebuilding will take time.
  */
final class EffectTest extends FunSuite:
  test("no effect > eval"):
    val i: Int < Any = 1
    val j: Int = i.eval
    assertEquals(j, 1)

  test("effect"):
    def double(i: Int): Int < Abort[Exception] = i * i

    val result: Result[Exception, Int] < Any = Abort.run(double(2))
    val answer: Int = result.eval.getOrElse(0)
    assertEquals(answer, 4)

  test("direct"):
    def add(x: Int, y: Int): Int < (IO & IO) =
      defer:
        await(IO(x)) + await(IO(y))

    val answer = IO.run(add(1, 2)).eval
    assertEquals(answer, 3)