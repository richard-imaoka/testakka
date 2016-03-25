import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

object MyFirstStream {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("MyActorSystem")
    implicit val materializer = ActorMaterializer()

    val sourceFromRange = Source(1 to 10)

    val sinkPrintingOutElements = Sink.foreach[Int](println(_))

    //sourceFromRange.runWith(sinkPrintingOutElements);

    val factorials = sourceFromRange.scan(BigInt(1))((acc, next) => acc * next)
    factorials.runWith(Sink.foreach[BigInt](println(_)))

    Thread.sleep(1500)
    system.terminate()
  }
}