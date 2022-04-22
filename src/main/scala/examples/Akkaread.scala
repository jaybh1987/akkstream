package examples

import akka.stream.scaladsl.{FileIO, Framing}
import akka.util.ByteString

import java.nio.file.Paths
import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success}

class Akkaread {

  //implicit materializer needed which is in main file.


//  val k = FileIO.fromPath(Paths.get("/home/laitmatus/Desktop/blogs/blog.text"))
//    .via(Framing.delimiter(ByteString("\n"), 1000, true))
//    .map(_.utf8String)
//    .runForeach(println)
//
//  k.onComplete{
//    case Success(value) => println(value)
//    case Failure(exception) => exception.printStackTrace()
//  }

  val kvprdd = List((1,2), (1, 3), (1, 5), (2, 5), (2, 9), (3, 4), (3, 10))
  kvprdd.filter {
    case( _ , v) => v % 2 == 0
  }


  kvprdd.flatMap { case (k, v) => (1 to v).map( r => (k, r)) }

  val akvrdd = List((1, 1), (1, 2), (2,4), (3, 4), (4, 5))

    val k_join = Array(
      (1,(2,1)),
      (1,(2,2)),
      (1,(3,1)),
      (1,(3,2)),
      (1,(5,1)),
      (1,(5,2)),
      (2,(5,4)),
      (2,(9,4)),
      (3,(4,4)),
      (3,(10,4)))


  val rdd = List(
    (1, 2),
    (1, 4),
    (1, 8),
    (2, 3),
    (2, 7)
  )

  rdd
    .groupBy( r => r._1)
    .map( r => (r._1, r._2.reduce{ case (_ , b) => b._1 + b._1} ) )








}
























