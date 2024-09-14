package objektwerks

import kyo.*

import scala.annotation.tailrec

object AsyncApp extends KyoApp:
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  run:
    for
      _         <- Console.println("*** Enter a factorial candidate:")
      candidate <- Console.readln
      number    =  candidate.toIntOption.getOrElse(1)
      fiber     <- Async.run( factorial(number) )
      result    <- fiber.get
      _         <- Console.println(s"*** Factorial of $number is: $result")
      _         <- Log.info(s"*** Factorial of $number is: $result")
    yield result