package objektwerks

import kyo.*

object ChannelApp extends KyoApp:
  run:
    for
      channel   <- Channel.init[Int](capacity = 1)
      _         <- Console.print("*** Enter a number:")
      candidate <- Console.readLine
      number    =  candidate.toIntOption.getOrElse(1)
      _         <- channel.put(number)
      _         <- Console.print(s"*** Put number: $number")
      _         <- Log.info(s"*** Put number: $number")
      take      <- channel.take
      _         <- Console.print(s"*** Take number: $take")
      _         <- Log.info(s"*** Take number: $take")
    yield ()