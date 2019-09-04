package org.arya.puzzles

/**
  * created by benyafang on 2019.9.4 15:06
  * 集合的迭代顺序
  * */
object Chapter12 {
  def Test():Unit ={
    println("-------------------Chapter12----------------------")
    case class RomanNumeral(symbol:String,value:Int)
    implicit object RomanOrdering extends Ordering[RomanNumeral]{
      override def compare(x: RomanNumeral, y: RomanNumeral): Int = {
        x.value compare y.value
      }
    }

    import collection.immutable.SortedSet
    val numerals = SortedSet(
      RomanNumeral("M",1000),
      RomanNumeral("C",100),
      RomanNumeral("X",10),
      RomanNumeral("I",1),
      RomanNumeral("D",500),
      RomanNumeral("L",50),
      RomanNumeral("V",5)
    )
    for (num <- numerals;sym <- num.symbol){print(s"$sym")}
    println("")
    numerals.map{_.symbol}foreach print
    println("")
    numerals foreach {tuple => print(tuple.symbol)}
    println("")
    numerals.view map{_.symbol} foreach print

    println("")
    numerals.toSeq map{_.symbol} foreach print


  }

}
