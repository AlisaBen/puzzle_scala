package org.arya.puzzles

/**
  * created by benyafang on 2019.9.4 15:23
  * 自引用
  * */
object Chapter14 {
  def sumItUp:Int = {
    //return 命名方法一定要发生在某个封闭的命名方法或者函数程序体内
    def one(x:Int):Int = {return x;1}
    val two = (x:Int) => {return x;2}//封闭的命名方法是sumItUp而不是two
    println(one(2))
    println(two(3))
    1 + one(2) + two(3)
  }

  def Test():Unit = {
    println("----------------Chapter14-----------------")
    println(sumItUp)
//    val s1 :String = s1
//    val s2:String = s2  + s2
//    println(s1,s2)

  }

}
