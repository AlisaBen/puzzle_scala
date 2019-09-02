package org.arya.puzzles

/**
  * created by benyafang on 2019.9.2 17:22
  * 测试静态标识符
  * */
object Chapter2 {
  def Test():Unit = {
    println("-----------------------Chapter2---------------")
    var MONTH = 12
    var DAY = 24
    //多变量赋值是基于模式匹配的，模式匹配中以大写字母开头的变量是静态标识符，模式匹配时不会给静态标识符赋值
    //  var (HOUR,MINUTE,SECOND) = (12,0,0)
    //小写变量放在单引号内时也能当做静态标识符使用，需要当做常量，用val定义
    val theAnswer = 42
    def checkGuess(guess:Int) = guess match{
      case `theAnswer` =>"correct"
      case _ =>"try again"
    }
    println(checkGuess(theAnswer))
    //var 用小写，val用大写
  }



}
