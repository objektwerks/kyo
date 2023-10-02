package objektwerks

import kyo.*
import kyo.concurrent.fibers.Fibers
import kyo.consoles.Consoles
import kyo.loggers.Loggers

object FactorialApp extends App:
  val logger = Loggers.init(getClass())

  def run(args: List[String]) = 
    for
      _         <- Consoles.println("*** Enter a factorial candidate:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      factorial <- Fibers.fork( factorial(number) )
      _         <- Consoles.println(s"*** Factorial of $number is: $factorial")
      _         <- logger.info(s"*** Factorial of $number is: $factorial")
    yield ()