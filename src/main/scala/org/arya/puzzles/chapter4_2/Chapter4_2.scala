package org.arya.puzzles.chapter4_2

/**
  * created by benyafang on 2019.9.3 12:09
  * 解决Chapter4练习中val变量重载问题
  * method-2:lazy val 代替val
  * */

trait A{
  val foo:Int//缺省初始值，Boolean缺省初始值是false，Unit缺省初始值是（），
  lazy val bar:Int = 10//附初值的变量后面只能用override

  println(s"In A:foo=$foo,bar=$bar")//0,0,0
}

class B extends A{
  val foo:Int = 25
  println(s"In B:foo=$foo,bar=$bar")//25,36,0

}
class C extends B{
  override val foo: Int = 46//当一个val被重载的时候，只能初始化一次
  override lazy val bar: Int = 100
  println(s"In C:foo=$foo,bar=$bar")//25,36,99

}
//In A:foo=0,bar=100
//In B:foo=0,bar=100
//In C:foo=46,bar=100

