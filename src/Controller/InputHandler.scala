package Controller
import Model.Entry._
import Model.Grid


/**
  * Created by evnurm.
  *
  * Handles the input given by the user.
  */
object InputHandler {

  private val grid = new Grid


  private val labels = Vector('0','1','2','3','4','5','6','7','8','9')
                      .zip(Vector(empty,one,two,three,four,five,six,seven,eight,nine))
                      .toMap


  private def validateInput(in: (Char, (Int,Int))) = {

    val in1 = in._1
    val in21 = in._2._1
    val in22 = in._2._2

    in1 >= '0' && in1 <= '9' &&
    in21 <= 9 && in21 >= 1 &&
    in22 <= 9 && in22 >= 1
  }

  /** Processes the input and returns the char for the label of the GUI. */
  def processInput(in: (Char,(Int, Int))): (Char, Boolean) = {

      val square = in._2._1
      val cell = in._2._2
      var success = false
      var char = '_'

      if(validateInput(in._1, (square, cell))){
        val entry = labels(in._1)

        try{
          grid.addEntry(square, cell, entry)
          char = if(in._1 == '0') ' ' else in._1
        } catch {
          case InvalidLocationException(_) =>
        }
      }
    (char, success)
  }

}