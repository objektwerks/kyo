package objektwerks

import kyo.*
import kyo.concurrent.channels.Channels
import kyo.consoles.Consoles
import kyo.loggers.Loggers

object ChannelApp extends App:
  val logger = Loggers.init(getClass())
  
  def run(args: List[String]) =
    for
      channel   <- Channels.init[Int](capacity = 3)
      _         <- Consoles.println("*** Enter a number:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      _         <- channel.put(number)
      _         <- Consoles.println(s"*** Put number: $number")
      take      <- channel.take
      _         <- Consoles.println(s"*** Take number: $take")
      _         <- logger.info(s"*** Take: $take")
    yield ()