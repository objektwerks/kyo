package objektwerks

import kyo.*

object ConsoleApp extends App:
  def run(args: List[String]) =
    for
      _        <- Consoles.println("*** What is your name?")
      name     <- Consoles.readln
      _        <- Consoles.println(s"*** Greetings, $name!")
      _        <- Logs.info(s"*** Greetings, $name!")
      datetime <- Clocks.now
      _        <- Consoles.println(s"*** The current date and time is: $datetime")
      _        <- Logs.info(s"*** The current date and time is: $datetime")
    yield ()