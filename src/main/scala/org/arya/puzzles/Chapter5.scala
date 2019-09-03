package org.arya.puzzles

/**
  * created by benyafang on 2019.9.3 16:15
  * 集合操作
  * Array,List,Set
  *
  * */
object Chapter5 {
  def Test():Unit = {
    println("----------------Chapter5-----------------")
    def sumSizes(collections:Iterable[Iterable[_]]):Int = {
      collections.map(_.size).sum
    }
    println(sumSizes(List(Set(1,2),List(3,4))))
    println(sumSizes(Set(List(1,2),Set(3,4))))
  }

}
