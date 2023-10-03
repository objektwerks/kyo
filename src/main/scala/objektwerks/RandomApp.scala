package objektwerks

import kyo.*
import kyo.consoles.Consoles
import kyo.loggers.Loggers
import kyo.randoms.Randoms

object RandomApp extends App:
  val logger = Loggers.init(getClass())

  def run(args: List[String]) =
    for
      a        <- Randoms.nextInt
      _        <- Consoles.println(s"*** Random number: $a")
      b        <- Randoms.nextInt
      _        <- Consoles.println(s"*** Random number: $b")
      c        <- Randoms.nextInt
      _        <- Consoles.println(s"*** Random number: $c")
      t        = a + b + c
      _        <- Consoles.println(s"*** Random numbers ($a + $b + $c) total: $t!")
      _        <- logger.info(s"*** Random numbers ($a + $b + $c) total: $t!")
    yield ()