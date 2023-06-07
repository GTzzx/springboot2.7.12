package com.example.spring

import java.io.{BufferedReader, File, InputStreamReader, OutputStreamWriter, PrintWriter}
import java.net.{ServerSocket, Socket}
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source


object scalaTest {
  /**
   * TODO 1.变量
   * var 可变 （初始化后值可以改变 类型不能改变）
   * val 不可变（初始化后无法改变）
   * 可以不写类型 让他自己根据值推断
   * 变量不可以不初始化 （声明时没有值）
   */
  var username="zhangsan";
  var username1="李四";
  val password:String="000000"

  /**
   * TODO 集合
   * 可变集合   （scala.collection.mutable）
   * 不可变集合 （scala.collection.immutable）
   * *  三大类： 序列Seq、集Set、映射Map
   */
  //TODO 数组
  val arr=new Array[Int](4)
  val arr1=Array(1,2,3,4)
  // 创建指定范围的数组
  val arr7: Array[Int] = Array.range(0,2)
  arr7.foreach(println)
  // 创建并填充指定数量的数组
  val arr8:Array[Int] = Array.fill[Int](5)(-1)
  arr8.foreach(println)
  arr(1)=1//将1位置赋值为1
  arr.update(0,1)//利用方法赋值

  //TODO 可变数组（长度可变）
  val buffer = new ArrayBuffer[Int]
  // 增加数据
  buffer.append(1,2,3,4)
  // 修改数据
  buffer.update(0,5)
  buffer(1) = 6
  // 删除数据
  val i: Int = buffer.remove(2)
  buffer.remove(2,2)
  // 查询数据
  println(buffer(3))
  // 循环集合
  for ( i <- buffer ) {
    println(i)
  }


  val buffer1 = ArrayBuffer(1,2,3,4)
  val array = Array(4,5,6,7)

  // 将不可变数组转换为可变数组
  val buffer2: mutable.Buffer[Int] = array.toBuffer
  // 将可变数组转换为不可变数组
  val array1: Array[Int] = buffer1.toArray

//TODO se1 集合
// Seq集合
val list = List(1,2,3,4)

  // 增加数据
  val list1: List[Int] = list :+ 1
  println(list1 eq list)
  list1.foreach(println)
  val list2: List[Int] = 1 +: list
  list2.foreach(println)
  println("*****************")
  val list3: List[Int] = list.updated(1,5)
  println(list eq list3)
  list3.foreach(println(_))

  // 可变集合
  val buffer3 = new ListBuffer[Int]()
  // 增加数据
  buffer3.append(1,2,3,4)
  // 修改数据
  buffer3.update(1,3)
  // 删除数据
  buffer3.remove(2)
  buffer3.remove(2,2)
  // 获取数据
  println(buffer3(1))
  // 遍历集合
  buffer3.foreach(println)



  val buffer4 = ListBuffer(1,2,3,4)
  val list4 = List(5,6,7,8)
  // 可变集合转变为不可变集合
  val list5: List[Int] = buffer4.toList
  // 不可变集合转变为可变集合
  val buffer5: mutable.Buffer[Int] = list4.toBuffer
\

  //TODO set集合
  val set1 = Set(1,2,3,4)
  val set2 = Set(5,6,7,8)

  // 增加数据
  val set3: Set[Int] = set1 + 5 + 6
  val set4: Set[Int] = set1.+(6,7,8)
  println( set1 eq set3 ) // false
  println( set1 eq set4 ) // false
  set4.foreach(println)
  // 删除数据
  val set5: Set[Int] = set1 - 2 - 3
  set5.foreach(println)

  val set6: Set[Int] = set1 ++ set2
  set6.foreach(println)
  println("********")
  val set7: Set[Int] = set2 ++: set1
  set7.foreach(println)
  println(set6 eq set7)

  val set8 = mutable.Set(1,2,3,4)
  val set9 = mutable.Set(5,6,7,8)

  // 增加数据
  set8.add(5)
  // 添加数据
  set8.update(6,true)
  println(set1.mkString(","))
  // 删除数据
  set8.update(3,false)
  println(set1.mkString(","))

  // 删除数据
  set8.remove(2)
  println(set1.mkString(","))

  // 遍历数据
  set8.foreach(println)

  //TODO Map集合
  val map1 = Map( "a" -> 1, "b" -> 2, "c" -> 3 )
  val map2 = Map( "d" -> 4, "e" -> 5, "f" -> 6 )

  // 添加数据
  val map3 = map1 + ("d" -> 4)
  println(map1 eq map3) // false

  // 删除数据
  val map4 = map3 - "d"
  println(map4.mkString(","))

  val map5: Map[String, Int] = map1 ++ map2
  println(map5 eq map1)
  println(map5.mkString(","))

  val map6: Map[String, Int] = map1 ++: map2
  println(map6 eq map1)
  println(map6.mkString(","))

  // 修改数据
  val map7: Map[String, Int] = map1.updated("b", 5)
  println(map7.mkString(","))

  // 遍历数据
  map1.foreach(println)

  val map8 = mutable.Map( "a" -> 1, "b" -> 2, "c" -> 3 )
  val map9 = mutable.Map( "d" -> 4, "e" -> 5, "f" -> 6 )

  // 添加数据
  map8.put("d", 4)
  val map10: mutable.Map[String, Int] = map8 + ("e" -> 4)
  println(map1 eq map3)
  val map11: mutable.Map[String, Int] = map8 += ("e" -> 5)
  println(map1 eq map4)

  // 修改数据
  map8.update("e",8)
  map8("e") = 8

  // 删除数据
  map8.remove("e")
  val map12: mutable.Map[String, Int] = map8 - "e"
  println(map1 eq map5)
  val map13: mutable.Map[String, Int] = map8 -= "e"
  println(map8 eq map9)
  // 清除集合
  map8.clear()


   //TODO 元祖 可以放多个类型 但是只能容纳22个
   // 创建元组，使用小括号
   val tuple = (1, "zhangsan", 30)

  // 根据顺序号访问元组的数据
  println(tuple._1)
  println(tuple._2)
  println(tuple._3)
  // 迭代器
  val iterator: Iterator[Any] = tuple.productIterator

  // 根据索引访问元素
  tuple.productElement(0)

  // 如果元组的元素只有两个，那么我们称之为对偶元组，也称之为键值对
  val kv: (String, Int) = ("a", 1)
  val kv1: (String, Int) = "a" -> 1
  println( kv eq kv1 )

//TODO 队列
val que = new mutable.Queue[String]()
  // 添加元素
  que.enqueue("a", "b", "c")
  val que1: mutable.Queue[String] = que += "d"
  println(que eq que1)
  // 获取元素
  println(que.dequeue())
  println(que.dequeue())
  println(que.dequeue())
  //TODO 并行集合 多线程
  val result1 = (0 to 100).map{x => Thread.currentThread.getName}
  val result2 = (0 to 100).par.map{x => Thread.currentThread.getName}

  println(result1)
  println(result2)




  /**
   * TODO 2.函数与方法
   * 函数与方法类似但是是不同的概念（因为格式是一样的）
   * 函数是一个对象 可以赋值
   * 方法是类的一部分
   *
   * 在类中声明的为方法 其他地方为函数
   * 方法有重载和重写 函数没有
   * 函数可以嵌套使用 方法不行
   */
 def stringTest()={
    println("string类型对象控制台输出："+username)
  }
  //可以使用可变参数 不规定参数长度 在存在多个类型参数时 可变参数放参数列表最后
  //参数使用时可以指定参数赋值
  def stringTest1(age:Int,users:String*)={
    println("函数可变参数类型控制台输出："+users)
    println(age)
  }

  /**
   * TODO 3. io流
   */
  def  ioTest(): Unit ={
    //控制台输入
    print("输入一个int值：")
    val inputInt = println(scala.io.StdIn.readInt())


    //文件写入（覆盖写）
    val writer = new PrintWriter(new File("src/test/java/com/example/spring/io.txt" ))
    writer.write("Hello Scala")
    writer.close()
    //文件读取
     scala.io.Source.fromFile("src/test/java/com/example/spring/io.txt").foreach(
      line => {
        print(line)
      }
    )
    //关闭流
    scala.io.Source.fromFile("src/test/java/com/example/spring/io.txt").getLines()
  }


  /**
   * TODO 网络通信
   */
  object TestServer {
    def main(args: Array[String]): Unit = {
      val server = new ServerSocket(9999)
      while ( true ) {
        val socket: Socket = server.accept()
        val reader = new BufferedReader(
          new InputStreamReader(
            socket.getInputStream,
            "UTF-8"
          )
        )
        var s : String = ""
        var flg = true
        while ( flg  ) {
          s = reader.readLine()
          if ( s != null ) {
            println(s)
          } else {
            flg = false
          }
        }
      }
    }
  }
  object TestClient {
    def main(args: Array[String]): Unit = {
      val client = new Socket("localhost", 9999)
      val out = new PrintWriter(
        new OutputStreamWriter(
          client.getOutputStream,
          "UTF-8"
        )
      )
      out.print("hello Scala")
      out.flush()
      out.close()
      client.close()
    }
  }

  /**
   * TODO 高阶函数
   */
    class mainTest{

      //函数作为值
      def a()={111}
      val a1 =a//将方法的返回值做参数
      val a2 =a _//将方法转为函数 将整个函数做参数
      val a3:()=>Unit =a //规定参数类型为函数 方法会自动转为函数
      //函数作为参数
      def fun (i:Int):Int={ i*10 }//方法1 需要参数i传入
      def fun1(f :Int=>Int):Int={f(10)} //方法2 需要函数传入（入参为int，出参为int）给输入的函数赋值
      fun1(fun)

      //函数做返回值
      def fun2() ={fun _}//返回函数fun
      fun2()(10)//第一个参数为自己的参 第二个为返回函数的参

      //匿名函数
      def fun3(f:Int=>Int) ={f(10)}//该函数参数希望为一个int函数
      fun3((x:Int)=>{x*20})//传入一个完全体匿名函数
      fun3(x=>{x*20})//传入一个未定义类型匿名函数 让他自己推断类型
      fun3(_*20)//用_代替参数 的匿名函数（最简单）

      //闭包 让变量能被外部使用（非作用域内）
      def fun4()={val i =20
        def fun5()={ i * 2 }
        fun5 _
      }
      fun4()()
      //函数柯里化
      def fun6(i:Int)(j:Int) = {
        i * j
      }
      //递归函数 自己调用自己
      def fun8(j:Int):Int = {
        if ( j <= 1 ) {
          1
        } else {
          j * fun8(j-1)
        }
      }
       println(fun8(5))
      //惰性函数 当函数返回值被声明为lazy时，函数的执行将被推迟，直到我们首次对此取值，该函数才会执行
      def fun9(): String = {
        println("function...")
        "zhangsan"
      }
      lazy val d = fun9()
      println("----------")
      println(d)


    }


  //主方法入口
  def main(args: Array[String]): Unit = {
    stringTest()
    stringTest1(age = 111,users = username,username1)
    ioTest()
    val test = new mainTest
    test.a()

  }





}
