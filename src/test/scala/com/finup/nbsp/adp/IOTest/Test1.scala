package com.finup.nbsp.adp.IOTest

import java.io.File

import myIO.IO
import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

/**
  * Created by john_liu on 2017/12/20.
  */
class Test1 extends FlatSpec with Matchers {

  //准备方法
  def ReadLine: IO[String] = IO {
    readLine()
  }

  def PrintLine(msg: String): IO[Unit] = IO {
    println(msg)
  }

  def ReadFile(path: String): IO[Iterator[String]] = IO {
    if (new File(path).isFile) {
      Source.fromFile(path).getLines()

    } else {
     Iterator("")
    }
  }

  "TestA" should "print something out of console" in {
    for {
      _ <- PrintLine("fuck you,you motherfucking cat!Enter some numbers ")

    } yield ()
  }

  "TestB" should "read file and print out the content" in {

    val path = "/Users/john_liu/Finupprojects/ailurophile/src/main/resources/test.txt"
    ReadFile(path).map {
      iter =>
        iter
          .foreach(PrintLine(_))
    }.run

  }


}
