package objektwerks

import kyo.*

object RandomApp extends App:
  def run(args: List[String]) =
    for
      a        <- Randoms.nextInt(11)
      _        <- Consoles.println(s"*** Random number: $a")
      b        <- Randoms.nextInt(11)
      _        <- Consoles.println(s"*** Random number: $b")
      c        <- Randoms.nextInt(11)
      _        <- Consoles.println(s"*** Random number: $c")
      t        = a + b + c
      _        <- Consoles.println(s"*** Random numbers ($a + $b + $c) total: $t!")
      _        <- Logs.info(s"*** Random numbers ($a + $b + $c) total: $t!")
    yield ()