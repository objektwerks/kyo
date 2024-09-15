package objektwerks

import kyo.*

import munit.FunSuite

/**
  * Version 0.11.0 destroyed this test!
  * Rebuilding will take time.
  */
final class EffectTest extends FunSuite:
  test("no effect"):
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

  test("abort"):
    val success: String < Abort[String] = Abort.get(Right("succeeded"))
    assert( Abort.run(success).eval.isSuccess )

    val failure: String < Abort[String] = Abort.get(Left("failed"))
    assert( Abort.run(failure).eval.isFail )

    val fail: String < Abort[String] = Abort.fail("failed")
    assert( Abort.run(fail).eval.isFail )

    val catching: String < Abort[Exception] = Abort.catching(throw new Exception)
    assert( Abort.run(catching).eval.isFail )

  test("io"):
    def triple(i: Int): Int < IO = i * i * i

    val answer = IO.run(triple(2)).eval
    assertEquals(answer, 8)