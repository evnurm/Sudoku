package View

import java.awt.Color
import javax.swing.border.LineBorder

import scala.swing.GridPanel

/**
  * Created by evnurm.
  *
  * Models a n x n square in the sudoku grid.
  */
class Square(val index: Int) extends GridPanel(3,3) {

  border = new LineBorder(Color.BLACK)

  for(i <- 1 to 9){
    contents += new GridCell(index,i)
  }


}