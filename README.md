Kyo
---
>Kyo feature tests using Scala 3.

Note
----
>Kyo is the latest competitor to Cats and Zio.

>Easily the simplest of all 3, Kyo still feels cumbersome compared to standard Scala.

>Kyo effects are *relatively* straightforward to work with and test; while concurrent effects are not.

>Fibers, Queues and Channels, for instance, often fail to evaluate in a unit test. But work in a Kyo App.

>Kyo is still a work-in-progress, though. Moreover, I'm no Kyo guru. :)

Build
-----
1. sbt clean compile

Test
----
1. sbt clean test

Run
---
1. sbt clean run

Resources
---------
* [Kyo Github](https://github.com/getkyo/kyo#)