package View

import scala.swing.{BoxPanel, Button, Dimension, GridPanel, MainFrame, Orientation, SimpleSwingApplication}

/**
  * Created by evnurm.
  *
  * The main window of the program.
  */
object View extends SimpleSwingApplication {

  override def top = new MainFrame{
    title = "Sudoku"
    contents = container
   // preferredSize = new Dimension(500,500)
  }

  val container = new BoxPanel(Orientation.Vertical)

  val options = new BoxPanel(Orientation.Horizontal){
    val newGame = new Button("New game")
    contents += newGame
  }



  val grid = new GridPanel(3,3) {

  for(i <- 1 to 9){
     contents += new Square(i)
   }
  }

  container.contents += options
  container.contents += grid



}