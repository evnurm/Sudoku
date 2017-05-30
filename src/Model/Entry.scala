package Model

/**
  * Created by evnurm.
  *
  * This enum is used to enumerate all possible entries.
  */
object Entry extends Enumeration {

  type Entry = Value
  val empty, one, two, three, four, five, six, seven, eight, nine = Value

  val labels: Map[Entry, String] = Map(empty -> "", one -> "1", two -> "2",
                                        three -> "3", four -> "4", five ->"5",
                                        six -> "6", seven -> "7", eight -> "8", nine -> "9")



}