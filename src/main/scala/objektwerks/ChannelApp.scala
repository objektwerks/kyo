package objektwerks

import kyo.*

object ChannelApp extends App:
  def run(args: List[String]) =
    for
      channel   <- Channel.init[Int](capacity = 1)
      _         <- Console.println("*** Enter a number:")
      candidate <- Console.readln
      number    =  candidate.toIntOption.getOrElse(1)
      _         <- channel.put(number)
      _         <- Console.println(s"*** Put number: $number")
      _         <- Log.info(s"*** Put number: $number")
      take      <- channel.take
      _         <- Console.println(s"*** Take number: $take")
      _         <- Log.info(s"*** Take number: $take")
    yield ()
