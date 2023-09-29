package objektwerks

import kyo.*
import kyo.consoles.Consoles

object GreeterApp extends App:
  def run(args: List[String]) = 
    for
      _    <- Consoles.println("What is your name?")
      name <- Consoles.readln
      _    <- Consoles.println(s"Greetings: $name")
    yield ()