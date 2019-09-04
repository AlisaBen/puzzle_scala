package org.arya.puzzles

import collection.mutable.Buffer
/**
  * create
  * */
object Chapter7 {
  def Test():Unit = {
    println("----------------------Chapter7----------------------")
    val accessors1 = Buffer.empty[() => Int]
    val accessors2 = Buffer.empty[() => Int]
    val data = Seq(100,110,120)
    var j = 0
    for(i <- 0 until data.length){
      accessors1 += (() => data(i))
      accessors2 += (() => data(j))
      j += 1
    }
    accessors1.foreach(a1 => println(a1()))//有输出有报错

//    accessors2.foreach(a2 => println(a2()))//有报错
  }

}
