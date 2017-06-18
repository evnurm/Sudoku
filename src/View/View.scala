package View

import Controller.InputHandler

import scala.swing.event.ButtonClicked
import scala.swing.{BoxPanel, Button, GridPanel, MainFrame, Orientation, Panel, SimpleSwingApplication}

/**
  * Created by evnurm.
  *
  * The main window of the program.
  */
object View extends SimpleSwingApplication {

  override def top = new MainFrame{
    title = "Sudoku"
    contents = container
  }

  val container = new BoxPanel(Orientation.Vertical)

  val options = new BoxPanel(Orientation.Horizontal){
    val newGame = new Button("New game")
    contents += newGame

    listenTo(newGame)

    reactions += {
      case ButtonClicked(`newGame`) => {
        // Initialize a new grid and empty the grid in the UI.
        InputHandler.initGrid()
        grid.contents.map(_.asInstanceOf[Square]).foreach(_.contents.map(_.asInstanceOf[GridCell]).foreach(_.changeEntry('0')))
      }
    }
  }

  var grid: Panel = new GridPanel(3,3) {

  for(i <- 1 to 9){
     contents += new Square(i)
   }
  }

  container.contents += options
  container.contents += grid



}