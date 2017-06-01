package Controller

/**
  * Created by evnurm on 1.6.2017.
  */
abstract class SudokuException(msg: String) extends Exception(msg)

case class InvalidLocationException(msg: String) extends SudokuException(msg)
case class InvalidEntryException(msg: String) extends SudokuException(msg)
