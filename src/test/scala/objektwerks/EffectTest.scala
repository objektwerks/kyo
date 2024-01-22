package objektwerks

import kyo.*
import kyo.direct.*

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.io.{BufferedSource, Codec, Source}
import scala.util.{Success, Try}

final class EffectTest extends AnyFunSuite with Matchers:
  test("empty"):
    val i: Int < Any = 1
    i.pure shouldBe 1

  test("widening"):
    val i: Int < Any = 1
    val io: Int < Options = i
    val iot: Int < (Options & Tries) = io
    Options.run(io) shouldBe Some(1)
    Tries.run(iot) shouldBe Success(1)

  test("direct widening"):
    val ot: Int < (Options & Tries) = 1
    ot shouldBe 1

  test("map"):
    val o: Int < Options = Options.get( Some(1) )
    val t: Int < (Options & Tries) = o
    o.map(v => t.map(_ + v)) shouldBe 2

  test("flatmap"):
    val o: Int < Options = Options.get( Some(1) )
    val t: Int < (Options & Tries) = o
    o.flatMap(v => t.map(_ + v)) shouldBe 2

  test("order"):
    def optionsFirst(a: Int < (Options & Tries)): Try[Option[Int]] =
      val b: Option[Int] < Tries = Options.run(a)
      val c: Try[Option[Int]] < Any = Tries.run(b)
      c.pure

    optionsFirst( Options.get(Some(1)) ) shouldBe Success( Some(1) )
    optionsFirst( Tries.get(Success(1)) ) shouldBe Success( Some(1) )

    def triesFirst(a: Int < (Options & Tries)): Option[Try[Int]] =
      val b: Try[Int] < Options = Tries.run(a)
      val c: Option[Try[Int]] < Any = Options.run(b)
      c.pure

    triesFirst( Options.get(Some(1)) ) shouldBe Some( Success(1) )
    triesFirst( Tries.get(Success(1)) ) shouldBe Some( Success(1) )

  test("direct"):
    val direct: Int < (Tries & Options) =
      defer {
        val b: Int = await( Options.get( Some(1) ) )
        val c: Int = await( Tries.get( Try(1) ) )
        b + c
      }

    val classic: Int < (Tries & Options) =
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
    val right: Int < Aborts[String] = Aborts[String].get( Right(1) )
    right.map(i => i shouldBe 1)

    val left: Int < Aborts[String] = Aborts[String].get( Left("Failure!") )
    left.map(i => i shouldBe 0)

    val fail: Int < Aborts[String] = Aborts[String].fail("Fail!")
    fail.map(i => i shouldBe 0)

    val catching: Int < Aborts[Exception] = Aborts[Exception].catching( throw new Exception("Execption") )
    catching.map(i => i shouldBe 0)

  test("ios"):
    val apply: Int < IOs = IOs(1)
    apply.map(i => i shouldBe 1)

    val value: Int < IOs = IOs.value(1)
    value.map(i => i shouldBe 1)

  test("envs"):
    trait Db:
      def count: Int < IOs 

    val db = new Db:
      def count = 1

    val query: Int < IOs = Envs[Db].run(db)(db.count)
    query.map(i => i shouldBe 1)

  test("locals"):
    val defaultValue = 1
    val local: Local[Int] = Locals.init(defaultValue)

    val currentIos: Int < IOs = local.get
    IOs.run(currentIos) shouldBe 1

    val newIos: Int < IOs = local.let(11)(currentIos.map(_ + 1))
    IOs.run(newIos) shouldBe 12

  test("resources"):
    val source: BufferedSource < (Resources & IOs) =
      Resources.acquire(
        Source.fromFile("./LICENSE", Codec.UTF8.name)
      )

    val wordCount: Int < IOs =
      Resources.run(
        source.map(file => file.mkString.split("\\W+").length)
      )

    IOs.run(wordCount) shouldBe 1609

  test("seqs"):
    val seqs: Int < Seqs = Seqs.get(Seq(1, 2))

    val evens: Int < Seqs = seqs.map(i => Seqs.filter(i % 2 == 0).map(_ => i))
    Seqs.run(evens) shouldBe List(2)

    val odds: Int < Seqs = seqs.map(i => Seqs.filter(i % 2 != 0).map(_ => i))
    Seqs.run(odds) shouldBe List(1)

    val newSeqs: Int < Seqs =
      seqs.map {
        case 1 => 11
        case _ => Seqs.drop
      }
    Seqs.run(newSeqs) shouldBe List(11)

  test("logs"):
    val info: Unit < IOs = Logs.info("*** Test log message.")
    IOs.run(info) // see ./target/test.log