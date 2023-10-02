package objektwerks

import kyo.*
import kyo.concurrent.queues.{Queue, Queues}
import kyo.consoles.Consoles
import kyo.ios.IOs
import kyo.loggers.Loggers

object QueueApp extends App:
  val logger = Loggers.init(getClass())
  val queue: Queue[Int] > IOs = Queues.bounded(capacity = 3)
  
  def run(args: List[String]) = 
    for
      _         <- Consoles.println("*** Enter a number:")
      candidate <- Consoles.readln
      number    =  candidate.toIntOption.getOrElse(1)
      offer     <- queue.map(_.offer(number))
      _         <- Consoles.println(s"*** Offer of $number succeeded: $offer")
      poll      <- queue.map(_.poll)
      _         <- Consoles.println(s"*** Queued: ${poll.getOrElse(-1)}")
      _         <- logger.info(s"*** Queued: ${poll.getOrElse(-1)}")
    yield ()