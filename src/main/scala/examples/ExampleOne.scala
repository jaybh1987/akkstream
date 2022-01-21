package examples

import akka.stream.scaladsl.{Flow, Sink, Source}

import java.io.File

class ExampleOne {


  val stream1 = Source(
    Seq(
      "/home/laitmatus/Desktop/blogs/akka_stream.text",
      "/home/laitmatus/Desktop/blogs/blog.text",
      "/home/laitmatus/Desktop/blogs/Hadoop_blog.text"))
    .map(r =>  new File(r))
    .filter(_.exists())
    .filter(_.length() != 0 )
    .to(Sink.foreach( f => println(s"Absolute path ${f.getAbsolutePath}")))


  val s = Source(
    Seq(
      "/home/laitmatus/Desktop/blogs/akka_stream.text",
      "/home/laitmatus/Desktop/blogs/blog.text",
      "/home/laitmatus/Desktop/blogs/Hadoop_blog.text"))

  val mapper = Flow[String].map( str => new File(str))

  val existFilter = Flow[File].filter( f => f.exists())

  val lengthZeroFilter = Flow[File].filter(f => f.length() != 0)

  val sink = Sink.foreach[File]( f => println(s"path = $f"))

  val stream2 = s
    .via(mapper)
    .via(existFilter)
    .via(lengthZeroFilter)
    .to(sink)

}
