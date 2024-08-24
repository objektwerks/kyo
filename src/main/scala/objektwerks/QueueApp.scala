package objektwerks

import kyo.*

object QueueApp extends App:
  def run(args: List[String]) =
    for
      queue     <- Queue.init[Int](capacity = 1)
      _         <- Console.println("*** Enter a number:")
      candidate <- Console.readln
      number    =  candidate.toIntOption.getOrElse(1)
      offer     <- queue.offer(number)
      _         <- Console.println(s"*** Offer of $number succeeded: $offer")
      _         <- Log.info(s"*** Offer of $number succeeded: $offer")
      poll      <- queue.poll
      _         <- Console.println(s"*** Polled: ${poll.getOrElse(-1)}")
      _         <- Log.info(s"*** Polled: ${poll.getOrElse(-1)}")
    yield ()
