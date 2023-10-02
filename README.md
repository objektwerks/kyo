Kyo
---
>Kyo feature tests using Scala 3.

Note
----
>Kyo is the latest competitor to Cats and Zio.

>The simplest of all 3, Kyo *still* feels cumbersome compared to standard Scala.

>Kyo effects are *relatively* straightforward to work with and test; while concurrent effects are not.

>Fibers, Queues and Channels, for instance, often fail to evaluate in a unit test. But all work in a Kyo App.

>Kyo contains an innovative *defer / await* construct; yet *for comprehensions* are never far way.

>Kyo is still a work-in-progress. See the **Resources** section below for details.

Build
-----
1. sbt clean compile

Test
----
1. sbt clean test

Run
---
1. sbt clean run
```
Multiple main classes detected. Select one to run:
 [1] objektwerks.ChannelApp
 [2] objektwerks.ConsoleApp
 [3] objektwerks.FiberApp
 [4] objektwerks.QueueApp

Enter number:
```

Resources
---------
* [Kyo Github](https://github.com/getkyo/kyo#)
