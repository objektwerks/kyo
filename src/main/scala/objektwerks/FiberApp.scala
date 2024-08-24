package objektwerks

import kyo.*

import scala.annotation.tailrec

object FiberApp extends App:
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  def run(args: List[String]) =
    for
      _         <- Console.println("*** Enter a factorial candidate:")
      candidate <- Console.readln
      number    =  candidate.toIntOption.getOrElse(1)
      factorial <- Async.run( factorial(number) )
      _         <- Console.println(s"*** Factorial of $number is: $factorial")
      _         <- Log.info(s"*** Factorial of $number is: $factorial")
    yield ()
