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
//      println(s"collections:$collections")
//      println(collections.map(_.size))
      collections.map(_.size).sum
    }
    def sumSizes1(collections:Iterable[Iterable[_]]):Int = {
      //      println(s"collections:$collections")
      //      println(collections.map(_.size))
      collections.toSeq.map(_.size).sum
    }

    def sumSizes2(collections:Iterable[Iterable[_]]):Int = {
      //      println(s"collections:$collections")
      //      println(collections.map(_.size))
      collections.foldLeft(0){
        (sumOfSizes,collection) => sumOfSizes + collection.size
      }
    }
    println("--------------sumSizes-------------------")
    println(sumSizes(List(Set(1,2),List(3,4))))

    println(sumSizes(Set(List(1,2),Set(3,4))))
    println("--------------sumSizes1-------------------")

    println(sumSizes1(List(Set(1,2),List(3,4))))

    println(sumSizes1(Set(List(1,2),Set(3,4))))
    println("--------------sumSizes2-------------------")
    println(sumSizes2(List(Set(1,2),List(3,4))))

    println(sumSizes2(Set(List(1,2),Set(3,4))))

    println((1 to 5).foldLeft(3){(res,unit) => res * unit})//3 * 1 * 2*3*4*5

//    println(sumSizes(Array(List(1,2),Set(3,4),List(5,6))))
  }

}
