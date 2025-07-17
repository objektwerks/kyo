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
      _         <- Console.print("*** Enter a factorial candidate:")
      candidate <- Console.readLine
      number    =  candidate.toIntOption.getOrElse(1)
      result    <- Async.defer( factorial(number) )
      _         <- Console.print(s"*** Factorial of $number is: $result")
      _         <- Log.info(s"*** Factorial of $number is: $result")
    yield result