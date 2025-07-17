package objektwerks

import kyo.*

object RandomApp extends KyoApp:
  run:
    for
      a <- Random.nextInt(11)
      _ <- Console.print(s"*** Random number: $a")
      b <- Random.nextInt(11)
      _ <- Console.print(s"*** Random number: $b")
      c <- Random.nextInt(11)
      _ <- Console.print(s"*** Random number: $c")
      t = a + b + c
      _ <- Console.print(s"*** Random numbers ($a + $b + $c) total: $t!")
      _ <- Log.info(s"*** Random numbers ($a + $b + $c) total: $t!")
    yield ()