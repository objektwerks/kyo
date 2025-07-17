package objektwerks

import kyo.*

object ConsoleApp extends KyoApp:
  run:
    for
      _        <- Console.print("*** What is your name?")
      name     <- Console.readLine
      _        <- Console.print(s"*** Greetings, $name!")
      _        <- Log.info(s"*** Greetings, $name!")
      datetime <- Clock.now
      _        <- Console.print(s"*** The current date and time is: $datetime")
      _        <- Log.info(s"*** The current date and time is: $datetime")
    yield ()