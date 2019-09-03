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

### 第三章 继承 成员声明的位置

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

以下规则控制 `val`的初始化和重载行为：
1. 超类会在子类之前初始化
2. 按照声明的顺序对成员初始化
3. 当一个 `val`被重载，只能初始化一次
4. 与抽象 `val`类似，重载的 `val`在超类构造期间会有一个缺省的初始值

上面的例子中，由于类 `C`中重载了 `bar`，特质A构造的时候，`bar`的初始值分配的是0而不是10，因为特质A中对 `bar`赋值10是完全不可见的

#### 预初始化字段

```Scala
trait A{
  val foo:Int//缺省初始值，Boolean缺省初始值是false，Unit缺省初始值是（），
  def bar:Int = 10//附初值的变量后面只能用override

  println(s"In A:foo=$foo,bar=$bar")//0,0,0
}

class B extends A{
  val foo:Int = 25
  println(s"In B:foo=$foo,bar=$bar")//25,36,0

}
class C extends B{
  override val foo: Int = 46//当一个val被重载的时候，只能初始化一次
  override def bar: Int = 100
  println(s"In C:foo=$foo,bar=$bar")//25,36,99

}
```
上面的这段例子将原来的 `bar`初始化的方式改为了 `def`
因为 `def`方法体不属于主构造器，不参与类初始化，因为类C中重载了 `bar`
多态会特别选择使用这个重载的定义

#### lazy val

```Scala
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
```
在这个解决方案中，使用 `lazy val`声明变量，意味着在特质构造器件就将它初始化成了100，
`lazy val`的特点是将高成本的初始化过程尽可能推迟到最后时刻

`lazy val`的缺点：

1. 底层发生同步，引起轻微的性能成本
2. 不能声明抽象 `lazy val`
3. 使用 `lazy val`容易产生循环引用，导致首次访问时发生栈溢出错误，甚至可能发生思索
4. 如果在对象间做了声明而 `lazy val`间的循环依赖却不存是，就能发生思索

上面这段话我也不是特别明白，等到用到的时候再来思考

### 第5章 集合操作

猜猜下面的输出结果是多少

```Scala

    def sumSizes(collections:Iterable[Iterable[_]]):Int = {
//      println(s"collections:$collections")
//      println(collections.map(_.size))
      collections.map(_.size).sum
    }
    println(sumSizes(List(Set(1,2),List(3,4))))

    println(sumSizes(Set(List(1,2),Set(3,4))))
//    println(sumSizes(Array(List(1,2),Set(3,4),List(5,6))))
  
```
Scala集合库的另外一个特征就是操作符一般会保持输入的集合类型不变
也就是说，第一个输出的集合是 `List`类型，`map`之后返回的也就是 `List`类型，允许重复值存在
而第二个输入的是 `Set`类型，`map`之后返回的也是 `Set`类型，不允许重复值存在集合中，所以返回的集合实际上只有一个2，所以最后的求和也就是2

解决办法:
1. 修改外部集合的类型
```Scala
    def sumSizes1(collections:Iterable[Iterable[_]]):Int = {
      collections.toSeq.map(_.size).sum
    }
```
这个很好理解，不管是什么集合类型，都将其转换成`Seq`

2. 利用 `fold`方法来实现

```Scala
    def sumSizes2(collections:Iterable[Iterable[_]]):Int = {
      collections.foldLeft(0){
        (sumOfSizes,collection) => sumOfSizes + collection.size
      }
    }
```
下面看一下 `foldLeft`的源码

```

  def foldLeft[B](z: B)(op: (B, A) => B): B = {
    var result = z
    this foreach (x => result = op(result, x))
    result
  }
```

`foldLeft`函数接收两个参数，第一个参数是最后返回结果的初始值，该例子中要计算的是外集合里面所有集合的长度和，所以初始值是0，
第二个参数是要执行的函数，函数的输入参数是 `(sumOfSizes,collection)`
第一个参数是最后要返回的结果， `collection`是集合内部的每个单元

还是挺喜欢 `foldLeft`函数的，看起来比较高级
但是使用起来还不是很熟练














