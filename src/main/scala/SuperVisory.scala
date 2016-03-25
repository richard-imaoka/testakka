import akka.actor.{ActorSystem, Props, Actor}
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import scala.concurrent.duration._

class Supervisor extends Actor {

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }

  def receive = {
    case p: Props => {
      val child = context.actorOf(p)
      println("Supervisor created " + child.path + p)
    }
  }
}

class Child extends Actor {

  throw new Exception("Intentional Exception within Child Actor's constructor")
  println("Child actor initiated")

  def receive = {
    case x: Any => println( "" + x.toString )
  }
}

object Main {
  def main(args: Array[String]): Unit = {

    val system = ActorSystem("main")
    val supervisor = system.actorOf(Props[Supervisor])

    println("main() started")
    supervisor ! Props[Child]

    Thread.sleep(2000)

    system.terminate()
    println("main() ended")
  }
}
