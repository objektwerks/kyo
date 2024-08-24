package objektwerks

import kyo.*

object ConsoleApp extends App:
  def run(args: List[String]) =
    for
      _        <- Console.println("*** What is your name?")
      name     <- Console.readln
      _        <- Console.println(s"*** Greetings, $name!")
      _        <- Log.info(s"*** Greetings, $name!")
      datetime <- Clock.now
      _        <- Console.println(s"*** The current date and time is: $datetime")
      _        <- Log.info(s"*** The current date and time is: $datetime")
    yield ()
