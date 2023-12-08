package objektwerks

import kyo.*
import kyo.concurrent.channels.Channels
import kyo.consoles.Consoles
import kyo.logs.Logs

object ChannelApp extends App:
  def run(args: List[String]) =
    for
      channel   <- Channels.init[Int](capacity = 1)
      _         <- Consoles.println("*** Enter a number:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      _         <- channel.put(number)
      _         <- Consoles.println(s"*** Put number: $number")
      _         <- Logs.info(s"*** Put number: $number")
      take      <- channel.take
      _         <- Consoles.println(s"*** Take number: $take")
      _         <- Logs.info(s"*** Take number: $take")
    yield ()