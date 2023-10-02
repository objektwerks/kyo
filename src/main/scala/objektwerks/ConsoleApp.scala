package objektwerks

import kyo.*
import kyo.clocks.Clocks
import kyo.consoles.Consoles
import kyo.loggers.Loggers

object ConsoleApp extends App:
  val logger = Loggers.init(getClass())

  def run(args: List[String]) = 
    for
      _    <- Consoles.println("*** What is your name?")
      name <- Consoles.readln
      _    <- Consoles.println(s"*** Greetings, $name!")
      time <- Clocks.now
      _    <- Consoles.println(s"*** The current date and time is: $time")
      _    <- logger.info(s"*** Greetings, $name!")
      _    <- logger.info(s"*** The current date and time is: $time")
    yield ()