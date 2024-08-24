package objektwerks

import kyo.*

object RandomApp extends App:
  def run(args: List[String]) =
    for
      a        <- Random.nextInt(11)
      _        <- Console.println(s"*** Random number: $a")
      b        <- Random.nextInt(11)
      _        <- Console.println(s"*** Random number: $b")
      c        <- Random.nextInt(11)
      _        <- Console.println(s"*** Random number: $c")
      t        = a + b + c
      _        <- Console.println(s"*** Random numbers ($a + $b + $c) total: $t!")
      _        <- Log.info(s"*** Random numbers ($a + $b + $c) total: $t!")
    yield ()
