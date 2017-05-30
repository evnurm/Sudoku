package Model
import Model.Entry.Entry
import scala.math.sqrt

/**
  * Represents the sudoku grid.
  *
  * @author evnurm
  *
  *  @param n the width & height of this grid.
  */
class Grid(val n: Int = 9){

  require(n > 0)

  private val grid = Array.ofDim[Entry](n,n)
  private var complete = false


  /** Returns the completion status of this grid. */
  def getCompletion = complete

  /** Adds an entry to this grid. */
  def addEntry(e: Entry, x: Int, y: Int): Unit = {
    if (checkRow(e, y) && checkColumn(e, y) && checkBox(e, x, y)) {
      grid(y)(x) = e
    }

    if(!grid.contains(Entry.empty)){
      complete = true
    }
  }

  /** Checks if the given row contains the given entry. */
  private def checkRow(e: Entry, y: Int): Boolean = {
    !grid(y).contains(e) || e == Entry.empty
  }

  /** Checks if the given column conta'ins the given entry. */
  private def checkColumn(e: Entry, x: Int): Boolean = {
    val col = for(i <- 0 until n) yield grid(i)(x)
    !col.contains(e) || e == Entry.empty
  }

  /** Checks whether the given box contains this entry. */
  private def checkBox(e: Entry, x: Int, y: Int): Boolean = {
    val lim = sqrt(n).toInt
    val xLower = (x+1)/lim * lim - 1
    val yLower = (y+1)/lim * lim - 1

    val box = (for(y <- yLower to yLower + lim) yield {
      for(x <- xLower to xLower + lim) yield grid(y)(x)
    }).flatten

    !box.contains(e) || e == Entry.empty
  }
}