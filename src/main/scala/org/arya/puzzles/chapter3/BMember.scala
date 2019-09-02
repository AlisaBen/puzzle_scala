package org.arya.puzzles.chapter3

trait A{
  val audience:String
  println("Hello " + audience)
}
class BMember (a:String = "World") extends A{
  override val audience: String = a
  println("I repeat:Hello " + audience)
}

class BConstructor(val audience:String = "World") extends A{
  //该种方法声明的变量不会存在null的情况
  println("I repeat:Hello " + audience)
}

object BMember{
  def Test():Unit = {
    println("--------------------Chapter3------------------")
    new BMember("reader")
    new BConstructor("reader")
//    Hello null
//    I repeat:Hello reader
//    Hello reader
//    I repeat:Hello reader
  }
}
