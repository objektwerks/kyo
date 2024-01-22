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
      _         <- Consoles.println("*** Enter a factorial candidate:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      factorial <- Fibers.run( factorial(number) )
      _         <- Consoles.println(s"*** Factorial of $number is: $factorial")
      _         <- Logs.info(s"*** Factorial of $number is: $factorial")
    yield ()