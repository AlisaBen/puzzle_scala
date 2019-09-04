package org.arya.puzzles

/**
  * created by benyafang on 2019.9.4 11:40
  * Map表达式
  * */
object Chapter8 {
  def Test():Unit = {
    println("------------------Chapter8-------------------------")
    val xs = Seq(Seq("a","b","c"),Seq("d","e","f"),Seq("g","h"),Seq("i","j","k"))
    val ys = for(Seq(x,y,z) <- xs)yield x+y+z
    println(ys)
//    val zs1 = xs map {case Seq(x,y,z) =>x+y+z}
//    println(zs1)
    val zs = xs map {case Seq(x,y,z) =>x+y+z;case Seq(x,y)=>x+y}
    println(zs)
    val zs2 = xs withFilter{
      case Seq(x,y,z) => true
      case _ => false
    } map{case Seq(x,y,z) => x+y+z}
    println(zs2)
  }

}
