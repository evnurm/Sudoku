package View

import java.awt.Color
import javax.swing.border.LineBorder

import scala.swing.{Alignment, Dimension, Font, Label, Panel}
import Controller.InputHandler

import scala.swing.event.{Key, KeyReleased, KeyTyped, MouseClicked}

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

  private val label = new Label("")
  label.font = new Font("Verdana",0,25)
  label.horizontalAlignment = Alignment.Center
  label.verticalAlignment = Alignment.Center
  _contents += label

  listenTo(mouse.clicks)

  reactions += {
    case e: MouseClicked if e.peer.getButton == 1 => {
      if (!active) {
        activate()
      } else deactivate()
    }

 /*   // For key-based navigation
    case KeyReleased(_, c, _, _)
      if c == Key.Up || c == Key.Down || c == Key.Right || c == Key.Left => switchCell(c)
*/

    case KeyTyped(_, c,_,_) => changeEntry(c)

  }

  /** Activates this cell. */
  def activate(): Unit = {

    if(View.activeCell != null) View.activeCell.deactivate()
    View.activeCell = this
    active = true
    border = new LineBorder(Color.BLACK)
    requestFocus()
    listenTo(keys)
  }

  /** Deactivates this cell. */
  def deactivate(): Unit = {
    active = false
    border = new LineBorder(Color.LIGHT_GRAY)
    deafTo(keys)
  }

  /** Changes the entry in this cell. */
  def changeEntry(c: Char): Unit = {

    val processed = InputHandler.processInput((c, (square, i)))
    if (!processed._2) {
      if (processed._1 != '_') {
        label.text = processed._1.toString
      }
      revalidate
      repaint
    } else {
    javax.swing.JOptionPane.showMessageDialog(null, "Congrats! You solved the sudoku!")
    }
  }

  /** Switches the active cell according to the given key. */
  private def switchCell(c: Key.Value): Unit ={

    val map = Map(Key.Up -> -3, Key.Down -> 3, Key.Right -> 1, Key.Left -> -1)

    val newIndex = i + map(c)
    if(newIndex < 0)

    deactivate()


  }

}