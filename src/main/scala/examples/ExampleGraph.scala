package examples
import java.io.File
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.stream.scaladsl.{Flow, GraphDSL, RunnableGraph, Sink, Source}

class ExampleGraph {


  val graph = RunnableGraph.fromGraph(GraphDSL.create() {
    implicit b =>
      import GraphDSL.Implicits._
      val s = Source(
        Seq(
          "/home/laitmatus/Desktop/blogs/akka_stream.text",
          "/home/laitmatus/Desktop/blogs/blog.text",
          "/home/laitmatus/Desktop/blogs/Hadoop_blog.text"))

      val mapper = Flow[String].map( str => new File(str))

      val existFilter = Flow[File].filter( f => f.exists())

      val lengthZeroFilter = Flow[File].filter(f => f.length() != 0)

      val sink = Sink.foreach[File]( f => println(s"path = $f"))

      s ~> mapper ~> existFilter ~> lengthZeroFilter ~> sink

      ClosedShape
  })

}
