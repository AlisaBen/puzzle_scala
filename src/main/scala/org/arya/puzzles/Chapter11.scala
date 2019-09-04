package org.arya.puzzles

/**
  * created by benyafang on 2019.9.4 14:20
  * lazy val
  * */
object Chapter11 {
  def Test():Unit = {
    println("---------------------Chapter11--------------------------")
    var x = 0
    lazy val y = 1 / x
    try {
      println(y)
    }catch {
      case _ : Exception =>
        x = 1
        println(y)
    }
  }

}
