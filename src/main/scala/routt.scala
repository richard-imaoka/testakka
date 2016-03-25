import akka.actor.{ActorSystem, Terminated, Props, Actor}
import akka.routing.{ ActorRefRoutee, RoundRobinRoutingLogic, Router }

class Worker extends Actor {

  def receive = {
    case Work => print( "work!" )
  }

}

object Work {

}

class Master extends Actor {
  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Props[Worker])
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  def receive = {
    case Work => {
      print("heehh")
      router.route(Work, sender())
    }
    case Terminated(a) =>
      router = router.removeRoutee(a)
      val r = context.actorOf(Props[Worker])
      context watch r
      router = router.addRoutee(r)
  }
}

object App {
  def main(args: Array[String]): Unit ={
    val system = ActorSystem("mySystem")
    val actor  = system.actorOf(Props[Master])
    actor ! Work
    println("app")
    Thread.sleep(1000)
    system.terminate()
  }
}