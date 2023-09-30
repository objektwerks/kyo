package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import kyo.*
import kyo.aborts.Aborts
import kyo.direct.*
import kyo.envs.Envs
import kyo.ios.IOs
import kyo.locals.{Local, Locals}
import kyo.options.Options
import kyo.tries.Tries

import scala.util.{Random, Success, Try}

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

  test("direct widening"):
    val ot: Int > (Options with Tries) = 1
    ot shouldBe 1

  test("map"):
    val o: Int > Options = Options.get( Some(1) )
    val t: Int > (Options with Tries) = o
    o.map(v => t.map(_ + v)) shouldBe 2

  test("flatmap"):
    val o: Int > Options = Options.get( Some(1) )
    val t: Int > (Options with Tries) = o
    o.flatMap(v => t.map(_ + v)) shouldBe 2

  test("order"):
    def optionsFirst(a: Int > (Options with Tries)): Try[Option[Int]] =
      val b: Option[Int] > Tries = Options.run(a)
      val c: Try[Option[Int]] > Any = Tries.run(b)
      c.pure
    def triesFirst(a: Int > (Options with Tries)): Option[Try[Int]] =
      val b: Try[Int] > Options = Tries.run(a)
      val c: Option[Try[Int]] > Any = Options.run(b)
      c.pure

    optionsFirst( Options.get(Some(1)) ) shouldBe Success( Some(1) )
    optionsFirst( Tries.get(Success(1)) ) shouldBe Success( Some(1) )

    triesFirst( Options.get(Some(1)) ) shouldBe Some( Success(1) )
    triesFirst( Tries.get(Success(1)) ) shouldBe Some( Success(1) )

  test("direct"):
    val direct: Int > (Tries with Options) =
      defer {
        val b: Int = await( Options.get( Some(1) ) )
        val c: Int = await( Tries.get( Try(1) ) )
        b + c
      }

    val classic: Int > (Tries with Options) =
      Options.get( Some(1) ).map { b =>
        Tries.get( Try(1) ).map { c =>
          b + c
        }
      }

    for
      d <- direct
      c <- classic
    yield
      d shouldBe c
      d + c shouldBe 4

  test("aborts"):
    val right: Int > Aborts[String] = Aborts[String].get( Right(1) )
    right.map(i => i shouldBe 1)

    val left: Int > Aborts[String] = Aborts[String].get( Left("Failure!") )
    left.map(i => i shouldBe 0)

    val fail: Int > Aborts[String] = Aborts[String].fail("Fail!")
    fail.map(i => i shouldBe 0)

    val catching: Int > Aborts[Exception] = Aborts[Exception].catching( throw new Exception("Execption") )
    catching.map(i => i shouldBe 0)

  test("ios"):
    val apply: Int > IOs = IOs(Random.nextInt(1))
    apply.map(i => i >= 1 shouldBe true)

    val value: Int > IOs = IOs.value(1)
    value.map(i => i shouldBe 1)

  test("envs"):
    trait Db:
      def count: Int > IOs 

    val db = new Db:
      def count = 1

    val query: Int > IOs = Envs[Db].run(db)(db.count)
    query.map(i => i shouldBe 1)

  test("locals"):
    val defaultValue = 1
    val local: Local[Int] = Locals.init(defaultValue)
    val currentValue: Int > IOs = local.get
    currentValue.map(i => i shouldBe 1)