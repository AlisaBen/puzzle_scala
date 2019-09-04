package org.arya.puzzles

/**
  * created by benyafang on 2019.9.4 15:48
  *
  * */
object Chapter15 {
  def Test():Unit = {
    println("-----------------Chapter15------------------")
    var x = 0
    def counter() = {x += 1;x}
    def add(a:Int)(b:Int) = a + b
    val adder1 = add(counter) (_)
    val adder2 = add(counter) _
    println("x = " + x)//1
    println(adder1(10))//12
    println("x = " + x)//2
    println(adder2(10))//11
    println("x = " + x)//2
  }

}
