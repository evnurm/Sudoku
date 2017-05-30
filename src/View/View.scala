package View

import scala.swing.{Dimension, GridPanel, MainFrame, SimpleSwingApplication}

/**
  * Created by evnurm.
  *
  * The main window of the program.
  */
object View extends SimpleSwingApplication {

  override def top = new MainFrame{
    title = "Sudoku"
    contents = grid
    preferredSize = new Dimension(500,500)
  }

  val grid = new GridPanel(3,3) {

/*     // place the cells inside the grid
      for (i <- 1 to 81) {
       contents += new GridCell(i)
      }
*/

    for(i <- 1 to 9){
      contents += new Square(i)
    }

  }

}