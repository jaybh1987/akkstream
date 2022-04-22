
import Basics.Basic.throttler
import akka.stream._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.scaladsl.Tcp.ServerBinding
import akka.util.ByteString

import java.io.File
import scala.concurrent._
import scala.concurrent.duration._
import java.nio.file.Paths
import java.time.LocalTime
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main extends App {
  println("Hello, World!")
  implicit val system: ActorSystem = ActorSystem("QuickStart")


}



























