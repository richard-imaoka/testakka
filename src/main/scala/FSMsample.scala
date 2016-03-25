import akka.actor.{FSM, LoggingFSM, Actor}
import akka.testkit.TestFSMRef

object FSMMAIN {

//  def main(args: Array[String]) = {
//    val fsm = TestFSMRef(new Actor with LoggingFSM[Int, Null]) {
//      override def logDepth = 12
//      startWith(1, null)
//      when(1) {
//        case Ev("hello") => goto(2)
//      }
//      when(2) {
//        case Ev("world") => goto(1)
//      }
//    }
//      assert (fsm.stateName == 1)
//      fsm ! "hallo"
//      assert (fsm.stateName == 2)
//      assert (fsm.underlyingActor.getLog == IndexedSeq(FSMLogEntry(1, null, "hallo")))
//  }
}
