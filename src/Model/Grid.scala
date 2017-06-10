package Model
import Controller.InvalidLocationException
import Model.Entry._

/**
  * Represents the sudoku grid.
  *
  * @author evnurm
  *
  */
class Grid {

  private val grid = Array.ofDim[Square](9)

  // fill the grid with the squares.
  for(i <- 1 to 9){
    grid(i-1) = new Square(i)
  }

  private var numEmpty = (for(s <- grid) yield s.elements.filter(_ == empty)).flatten.length
  private var complete = numEmpty == 0

  /** Returns the completion status of this grid. */
  def getCompletion = complete


  /** Adds an entry into the given cell in the given square, if possible. */
  def addEntry(squareNo: Int, cellNo: Int, entry: Entry) = {
    val columnNo = (squareNo - 1)%3 * 3 + (cellNo-1)%3 + 1

    val rowNo = (squareNo-1)/3 * 3 + (cellNo - 1) / 3 + 1

    val column = getColumn(columnNo)
    val row = getRow(rowNo)
    val square = grid(squareNo-1)

    if((column.contains(entry) || row.contains(entry) || square.contains(entry)) && entry != empty){
      throw new InvalidLocationException("Illegal position for given entry.")
    } else {
      if(square.getEntryAt(cellNo-1) == empty){
        numEmpty -= 1
      }
      square.changeEntry(cellNo-1, entry)

      if(numEmpty == 0) complete = true // the grid is complete.

    }
  }


  /** Returns the column at the given index. */
  private def getColumn(idx: Int): Array[Entry] = {
    val topSquareIdx = (idx - 1)/3
    val squares = Array(grid(topSquareIdx), grid(topSquareIdx + 3), grid(topSquareIdx + 6))

    var mod = idx%3
    if(mod == 0) mod = 3

    val indices  = Array(mod - 1, mod + 2, mod + 5)

    (for(s <-  squares) yield {
      for(i <- indices) yield s.getEntryAt(i)
      }).flatten
    }

  /** Returns the row at the given index. */
  private def getRow(idx: Int): Array[Entry] = {
    val mapping = Map[Int, Int](0 -> 0, 1 -> 3, 2 -> 6)
    val mapping2 = Map[Int,Int](1 -> 0, 2 -> 3, 0 -> 6)
    val leftIdx = mapping((idx - 1 )/3)

    val squares = Array(grid(leftIdx), grid(leftIdx+1), grid(leftIdx+2))

    val firstElemIdx = mapping2(idx % 3)

    val indices = Array(firstElemIdx, firstElemIdx + 1, firstElemIdx + 2)

    (for(s <- squares) yield {
      for(i <- indices) yield s.getEntryAt(i)
    }).flatten
  }



}