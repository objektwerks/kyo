package objektwerks

import kyo.*

import munit.FunSuite

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
    def add(x: Int, y: Int): Int < (Abort[Exception] & Sync) =
      direct:
        Sync.defer(x).now + Sync.defer(y).now

    direct:
      val answer = add(1, 2).now
      assertEquals(answer, 3)