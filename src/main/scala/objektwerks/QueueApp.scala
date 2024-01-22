package objektwerks

import kyo.*

object QueueApp extends App:
  def run(args: List[String]) =
    for
      queue     <- Queues.init[Int](capacity = 1)
      _         <- Consoles.println("*** Enter a number:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      offer     <- queue.offer(number)
      _         <- Consoles.println(s"*** Offer of $number succeeded: $offer")
      _         <- Logs.info(s"*** Offer of $number succeeded: $offer")
      poll      <- queue.poll
      _         <- Consoles.println(s"*** Polled: ${poll.getOrElse(-1)}")
      _         <- Logs.info(s"*** Polled: ${poll.getOrElse(-1)}")
    yield ()