package org.arya.puzzles

/**
  * created by benyafang on 2019.9.6 17:35
  * 克里化
  *
  * */
object Chapter16 {
  def Test():Unit = {
    println("------------------Chapter16------------------")
    def invert(v3:Int)(v2:Int = 2,v1:Int = 1): Unit ={
      println(v1 + "," + v2 + ", " + v3)
    }

    val invert3 = invert(3) _//定义缺省参数能够引用以前的参数列表的参数
    println(invert3.toString())
//    invert3(v2 = 2)
    invert3(v2 = 4, v1 = 6)//尽管这里声明了v1=4，但是

  }

}
