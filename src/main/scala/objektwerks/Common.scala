package objektwerks

import scala.annotation.tailrec

@tailrec
def factorial(n: Int, acc: Int = 1): Int = n match
  case i if i < 1 => acc
  case _ => factorial(n - 1, acc * n)