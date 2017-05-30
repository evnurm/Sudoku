package Controller
import Model.Entry._
import Model.Grid
/**
  * Created by evnurm.
  *
  * Handles the input given by the user.
  */
class InputHandler(private val grid: Grid) {

  private val labels = Vector('0','1','2','3','4','5','6','7','8','9')
                      .zip(Vector(empty,one,two,three,four,five,six,seven,eight,nine))
                      .toMap


  def validateInput(in: (Char, (Int,Int))) = {

    val in1 = in._1
    val in21 = in._2._1
    val in22 = in._2._2

    in1 >= '0' && in1 <= '9' &&
    in21 <= 9 && in21 >= 1 &&
    in22 <= 9 && in22 >= 1
  }


  def processInput(in: (Char,(Int, Int))) = {

      val square = in._2._1
      val cell = in._2._2

    if(validateInput(in._1, (square, cell))){
      val entry = labels(in._1)

      /* Now we have to compute the x,y -coordinates
         of the cell in order to shove it into the Model.*/





    } else throw new Exception()
  }

}