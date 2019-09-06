package org.arya.puzzles

import scala.collection.mutable

object Main {
  def main(args: Array[String]): Unit = {
    Chapter1.testPlaceHolder()
    Chapter2.Test()
    Chapter3.Test()
    Chapter4.Test()
    Chapter4_1.Test()
    Chapter4_2.Test()
    Chapter5.Test()
    Chapter7.Test()
    Chapter8.Test()
    Chapter10.Test()
    Chapter11.Test()
    Chapter12.Test()
    Chapter14.Test()
    Chapter15.Test()
    Chapter16.Test()

//    val map = mutable.HashMap[Int,mutable.HashMap[Int,String]]()
//    map.put(2,mutable.HashMap(3 -> "fff"))
//    map.put(0,mutable.HashMap(4 -> ""))
//    map.put(1,mutable.HashMap(5->"ffwe"))
//    map.toList.sortBy(_._1).flatMap(_._2).map(_._2).foreach(println)
  }

}
