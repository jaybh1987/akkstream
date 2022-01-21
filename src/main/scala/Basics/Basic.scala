package Basics


import akka.actor.Cancellable
import akka.stream.FlowShape
import akka.stream.scaladsl.{Flow, GraphDSL, Keep, Sink, Source, ZipWith}

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

object Basic {

  val k = Source(1 to 6).via(Flow[Int].map(_ * 3)).to(Sink.foreach(println(_)))

  val j = Source(1 to 6).map(_ * 2).to(Sink.foreach(println(_)))

  val b = Flow[Int].map(_ * 3).to(Sink.foreach( println(_) ))

  val x = Source(1 to 6).to(b)

  val asyncExample = Source(List(1,2,4))
    .map(_ + 1)
    .async
    .map(_ * 2)
    .to(Sink.ignore)

  val throttler = Flow.fromGraph(GraphDSL.create(Source.tick(1.second, 1.second, "test")) {
    implicit builder => tickSource =>
      import GraphDSL.Implicits._
      val zip = builder.add(ZipWith[String, Int, Int](Keep.right))
      tickSource ~> zip.in0
      FlowShape(zip.in1, zip.out)
  })


  val so = Source.maybe[Int]

  val sn = Sink.head[Int]

  val fw = throttler

  val r1 = so.via(fw).to(sn)

  val r2 = so.viaMat(fw)(Keep.right).to(sn)


}
