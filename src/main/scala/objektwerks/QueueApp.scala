package objektwerks

import kyo.*
import kyo.concurrent.queues.Queues
import kyo.consoles.Consoles
import kyo.loggers.Loggers

object QueueApp extends App:
  val logger = Loggers.init(getClass())
  
  def run(args: List[String]) =
    for
      queue     <- Queues.bounded[Int](capacity = 1)
      _         <- Consoles.println("*** Enter a number:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      offer     <- queue.offer(number)
      _         <- Consoles.println(s"*** Offer of $number succeeded: $offer")
      _         <- logger.info(s"*** Offer of $number succeeded: $offer")
      poll      <- queue.poll
      _         <- Consoles.println(s"*** Polled: ${poll.getOrElse(-1)}")
      _         <- logger.info(s"*** Polled: ${poll.getOrElse(-1)}")
    yield ()