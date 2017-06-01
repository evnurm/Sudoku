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
  private var complete = false

  for(i <- 1 to 9){
    grid(i-1) = new Square(i)
  }

  /** Returns the completion status of this grid. */
  def getCompletion = complete


  /** Adds an entry into the given cell in the given square, if possible. */
  def addEntry(square: Int, cell: Int, entry: Entry) = {
    val columnNo = (square - 1)%3 * 3 + (cell-1)%3 + 1

    val rowNo = (square-1)/3 * 3 + (cell - 1) / 3 + 1

    val column = getColumn(columnNo)
    val row = getRow(rowNo)

    if((column.contains(entry) || row.contains(entry) || grid(square-1).contains(entry)) && entry != empty){
      throw new InvalidLocationException("Illegal position for given entry.")
    } else grid(square-1).changeEntry(cell-1, entry)
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