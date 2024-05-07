package objektwerks

import kyo.*

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.io.{BufferedSource, Codec, Source}

final class EffectTest extends AnyFunSuite with Matchers:
  test("empty"):
    val i: Int < Any = 1
    i.pure shouldBe 1

  test("widening"):
    val i: Int < Any = 1
    val io: Int < Options = i
    Options.run(io) shouldBe Some(1)

  test("direct widening"):
    val o: Int < (Options) = 1
    o shouldBe 1

  test("map"):
    val o: Int < Options = Options.get( Some(1) )
    val t: Int < (Options) = o
    o.map(v => t.map(_ + v)) shouldBe 2

  test("flatmap"):
    val o: Int < Options = Options.get( Some(1) )
    val t: Int < (Options) = o
    o.flatMap(v => t.map(_ + v)) shouldBe 2

  test("aborts"):
    val right: Int < Aborts[String] = Aborts.get( Right(1) )
    right.map(i => i shouldBe 1)

    val left: Int < Aborts[String] = Aborts.get( Left("Failure!") )
    left.map(i => i shouldBe 0)

    val fail: Int < Aborts[String] = Aborts.fail("Fail!")
    fail.map(i => i shouldBe 0)

    val catching: Int < Aborts[Exception] = Aborts.catching( throw new Exception("Execption") )
    catching.map(i => i shouldBe 0)

  test("ios"):
    val apply: Int < IOs = IOs(1)
    apply.map(i => i shouldBe 1)

    val value: Int < IOs = IOs(1)
    value.map(i => i shouldBe 1)

  test("envs"):
    trait Db:
      def count: Int < IOs 

    val db = new Db:
      def count = 1

    val query: Int < IOs = Envs.run(db)(db.count)
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

  test("logs"):
    val info: Unit < IOs = Logs.info("*** Test log message.")
    IOs.run(info) // see ./target/test.log