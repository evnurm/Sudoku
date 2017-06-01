package View

import java.awt.Color
import javax.swing.border.LineBorder
import scala.swing.{Alignment, Dimension, Label, Panel, Font}
import Model.Entry._
import Controller.InputHandler
import scala.swing.event.{KeyTyped, MouseClicked}

/**
  * Created by evnurm.
  *
  * Represents a cell in the sudoku grid.
  */
class GridCell(val square: Int, i: Int) extends Panel {

  preferredSize = new Dimension(50,50)
  border = new LineBorder(Color.LIGHT_GRAY)
  background = Color.WHITE
  focusable = true

  private var active = false

  private var entry = empty

  private val label = new Label("")
  label.font = new Font("Verdana",0,25)
  label.horizontalAlignment = Alignment.Center
  label.verticalAlignment = Alignment.Center
  _contents += label

  listenTo(mouse.clicks)

  reactions += {
    case e: MouseClicked if e.peer.getButton == 1 => {
      if (!active) {
        activate
      } else deactivate
    }
    case KeyTyped(_, c,_,_) => changeEntry(c)
  }

  /** Activates this cell. */
  def activate = {
    View.grid.contents.map(_.asInstanceOf[Square]).foreach(_.contents.map(_.asInstanceOf[GridCell]).foreach(_.deactivate))
    active = true
    border = new LineBorder(Color.BLACK)
    requestFocus()
    listenTo(keys)
  }

  /** Deactivates this cell. */
  def deactivate = {
    active = false
    border = new LineBorder(Color.LIGHT_GRAY)
    deafTo(keys)
  }

  /** Changes the entry in this cell. */
  def changeEntry(c: Char) = {

    val processed = InputHandler.processInput((c,(square, i)))
    if(!processed._2) {
      if (processed._1 != '_'){
        label.text = processed._1.toString
      }
    this.revalidate
    this.repaint
    }
  }
}