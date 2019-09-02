package org.arya.puzzles

/**
  * created by benyafang on 2019.9.2 17:20
  * 测试占位符与箭头函数的区别
  * */
object Chapter1 {
  def testPlaceHolder():Unit = {
    println(List(2,3).map{
      println("ff")
      _+1
    })
    println("---------------------")
    val ll = List(2,3).map{r =>
      println("ff")
      r + 1
    }
    println(ll)
  }

}
