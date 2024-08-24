Kyo
---
>Kyo feature tests using Scala 3.

Todo
----
1. Replace Scalatest with MUnit.

Notes
-----
* **Tries** were removed in version *0.8.7*.
* **Seqs** were removed in version *0.9.2*.
* Version *0.11.0* removed most plurals and destroyed EffectTest.

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
 [1] objektwerks.AsyncApp
 [2] objektwerks.ChannelApp
 [3] objektwerks.ConsoleApp
 [4] objektwerks.QueueApp
 [5] objektwerks.RandomApp

Enter number:
```

Resources
---------
* [Kyo Github](https://github.com/getkyo/kyo#)
* [Kyo Docs](https://getkyo.io/#/)
* [Algebraic Effects from Scratch by Kit Langton](https://www.youtube.com/watch?v=qPvPdRbTF-E)
