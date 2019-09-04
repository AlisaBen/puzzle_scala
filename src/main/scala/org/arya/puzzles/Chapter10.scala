package org.arya.puzzles

/**
  * created by benyafang on 2019.9.4
  * case class 和class
  * 书里面的这一章已经把我说糊涂了
  * */

trait TraceHashCode{
  override def hashCode(): Int = {
    println(s"TRACE:In hashCode for ${this}")
    super.hashCode()
  }
}

object Chapter10{
  def Test():Unit = {
    println("---------------------Chapter10---------------------")
    case class Country(isoCode:String)
    def newSwitzInst = new Country("CH") with TraceHashCode
    case class CountryWithTrace(isoCode:String) extends TraceHashCode
    def newSwitzDec1 = CountryWithTrace("CH")
    import collection.immutable.HashSet
    val countriesInst = HashSet(newSwitzInst)
    println(countriesInst)
    println(countriesInst.iterator contains newSwitzInst)
    println(countriesInst contains newSwitzInst)

    val countriesDec1 = HashSet(newSwitzDec1)
    println(countriesDec1)
    println(countriesDec1.iterator contains newSwitzDec1)
    println(countriesDec1 contains newSwitzDec1)
  }


}


