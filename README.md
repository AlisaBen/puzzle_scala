> 发现了一本有意思的书叫做《scala谜题》里面讲了一些特别细节的东西，平时也写了不少了scala的代码，但是有些内容就觉得习以为常了，或者因为idea编辑器做的比较好，在写代码的时候有不对或者不规范的地方，它会直接给出提示，有时候就发现哦，原来这样写是不对的，编译会出错，但是也没有想太多究竟是为什么，这本书做了比较有意思的科普，那就把自己的练习心得记下来，也许以后会考虑用scala刷leetcode

### 第一章 占位符

```Scala
val list1:List[Int] = List(1,2,3).map(_ + 1)
val list2:List[Int] = List(1,2,3).map(r => r + 1)
```
在上面的例子中，两行代码的返回结果其实是一样的，都是 `List(2,3,4)`,而在第一行中的 `_`叫做占位符，可以让我们的代码更加简洁
但是这并不意味着使用 `_` 和箭头函数是同样的结果

```Scala
val list1:List[Int] = List(1,2,3).map{println("ss");_ + 1}
val list2:List[Int] = List(1,2,3).map{r => println("ss");r + 1}
```
在上面的代码中，看起来运行的结果还是一样的，但是事实却不是这样的，当然 `list1` 和 `list2`的返回值是一样的，但是执行的过程中打印的结果却是不一样的，
第一行代码打印了一行 `ss`，第二行代码打印了两行 `ss`

| 为什么会有这样的不同？

我们可以通过 `map`函数的源码理解一下

```
  final override def map[B, That](f: A => B)(implicit bf: CanBuildFrom[List[A], B, That]): That = {
    if (bf eq List.ReusableCBF) {
      if (this eq Nil) Nil.asInstanceOf[That] else {
        val h = new ::[B](f(head), Nil)
        var t: ::[B] = h
        var rest = tail
        while (rest ne Nil) {
          val nx = new ::(f(rest.head), Nil)
          t.tl = nx
          t = nx
          rest = rest.tail
        }
        h.asInstanceOf[That]
      }
    }
    else super.map(f)
  }

```

如果看不懂这段源码没关系，我们只要看下 `map` 函数的参数，是一个从 类型 `A` 到类型 `B` 的映射（函数f），函数的执行体就是我们自己在 `map`中自定义的部分
第一行代码传进去的函数参数其实是 `_+1`,而第二行代码传进去的函数是 `println("ss");r + 1`，箭头表示了后面的代码都是输出函数的执行体

### 第二章 静态标识符

在了解什么是静态标识符之前，先了解一下什么是模式匹配,看一个模式匹配的例子好了

```Scala
val x:String = "Arya"
x match{
  case "Arya" =>"Ben"
  case "Alisa" => "Brand"
  case _ => "other"
}
```

更多的例子可以看一下 [模式匹配的例子](https://docs.scala-lang.org/zh-cn/tour/pattern-matching.html)

```Scala
val MONTH = 12
//val (HOUR,MINUTE,SECOND) = (23,0,0)
val (month,minute,second) = (23,0,0)

```
上面的例子中，第一行是可以编译通过的，而第二行编译器会提示错误，所以被注释掉了,而第三行和第二行的区别就在于变量定义的字母是小写的，
多变量赋值是采取模式匹配的方式，模式匹配中以大写字母开头的变量是静态标识符，模式匹配不会给静态标识符赋值

除此之外，小写变量放在单引号内时，也能当做静态标识符使用，需要当做常量，用 `val`定义

```Scala
    val theAnswer = 42
    def checkGuess(guess:Int) = guess match{
      case `theAnswer` =>"correct"
      case _ =>"try again"
    }
    println(checkGuess(theAnswer))
```

### 第三章 继承

如果没写过继承的例子或者看不懂下面的代码，可以先看一些特质的例子 [特质](https://docs.scala-lang.org/zh-cn/tour/traits.html)

先直接上个例子代码

```Scala
trait A{
  val audience:String
  println("Hello " + audience)
}
class BMember (a:String = "World") extends A{
  override val audience: String = a
  println("I repeat:Hello " + audience)
}

class BConstructor(val audience:String = "World") extends A{
  //该种方法声明的变量不会存在null的情况
  println("I repeat:Hello " + audience)
}
new BMember("reader")
new BConstructor("reader")
```
这个例子真的挺不错的，看到这个例子的时候自己心里预估的答案也是模棱两可的，主要是对继承的类的编译执行顺序不清晰
`BMember`类和 `BConstructor`类都继承了抽象类 `A`,该例子主要判断抽象类中的变量 `audience`是什么时候初始化的？
在看答案之前可以自己先猜一猜结果，最后的答案已经被我注释在了代码里面，可以直接看代码 [Chapter3](https://github.com/AlisaBen/puzzle_scala/blob/master/src/main/scala/org/arya/puzzles/chapter3/BMember.scala)

通过结果我们可以看出 `BMember`类是先执行了抽象类 `A`然后才执行的变量 `audience`赋值操作，而 `BContructor`类的 `audience`变量作为参数传递，在实例化抽象类`A`之前便已经执行，所以抽象类中的 `audience`已经被定义了

注：第一个类实例化的时候之所以输出 `Hello null`还有一个点是类型 `String ` 的缺省初始值是 `null`

### 第四章 缺省初始值与重载

```Scala
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
new C()

```



