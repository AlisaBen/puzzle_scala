package org.arya.puzzles.chapter4

trait A{
  val foo:Int//缺省初始值，Boolean缺省初始值是false，Unit缺省初始值是（），
  val bar = 10//附初值的变量后面只能用override
  val foo1 = 23

  println(s"In A:foo=$foo,foo1=$foo1,bar=$bar")//0,0,0
}

class B extends A{
  val foo:Int = 25
  override val foo1: Int = 36
  println(s"In B:foo=$foo,foo1=$foo1,bar=$bar")//25,36,0

}
class C extends B{
  override val foo: Int = 56//当一个val被重载的时候，只能初始化一次
  override val bar: Int = 99
  println(s"In C:foo=$foo,foo1=$foo1,bar=$bar")//25,36,99

}

