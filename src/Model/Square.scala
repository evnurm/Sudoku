package Model

import Model.Entry._

/**
  * Created by evnurm on 30.5.2017.
  */
class Square(val index: Int){

  val contents = Array.ofDim[Entry](9)

  for(i <- contents.indices) contents(i) = empty

  def changeEntry(i: Int, e: Entry) = {
    contents(i) = e
  }
  /** Returns the entry at the given position. */
  def getEntryAt(idx: Int) = contents(idx)

  /** Checks whether or not this square contains the given entry. */
  def contains(e: Entry) = contents.contains(e)
}
