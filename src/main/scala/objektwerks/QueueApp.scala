package objektwerks

import kyo.*

object QueueApp extends KyoApp:
  run:
    for
      queue     <- Queue.init[Int](capacity = 1)
      _         <- Console.print("*** Enter a number:")
      candidate <- Console.readLine
      number    =  candidate.toIntOption.getOrElse(1)
      offer     <- queue.offer(number)
      _         <- Console.print(s"*** Offer of $number succeeded: $offer")
      _         <- Log.info(s"*** Offer of $number succeeded: $offer")
      poll      <- queue.poll
      _         <- Console.print(s"*** Polled: ${poll.getOrElse(-1)}")
      _         <- Log.info(s"*** Polled: ${poll.getOrElse(-1)}")
    yield ()