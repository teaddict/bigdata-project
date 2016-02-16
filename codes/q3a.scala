package random

import org.apache.spark.{
SparkContext,
SparkConf
}
import SparkContext._
import org.apache.log4j.{Level, Logger}

object UserLocationsFinal {


  def main(args: Array[String]): Unit = {

    /*
    if (args.length < 1) {
      //System.err.println(getUsage())
      System.exit(1)
    }
    */

    /* bir userin check in yaptığı yerde en çok checkin yapan user
      mehmet dönerci -> dönerci ali
  */
    val conf = new SparkConf()
      .setAppName("Location Count")
      .setMaster("local")
      .set("spark.logConf","false")
      .set("spark.executor.memory","1g")
    val sc = new SparkContext(conf)
   // val rootLogger = Logger.getRootLogger();
   // rootLogger.setLevel(Level.ERROR);

    val textFile = sc.textFile("random100user.txt")
    //[ userId, LocationId ] oluşturacağız
    val pairs = textFile.map(line => line.split("\\t+"))
      .map(word => (word(4),word(0))).distinct()

    println("test1")
    pairs.foreach(println)

    //[userID,LocationId,MostCheckedUserId] oluşturuyoruz
    val dbFile = sc.textFile("totalCheckins.txt")
    val counts = dbFile.map(line => line.split("\\t+"))
      .map(word => (word(4), word(0))).map(w =>(w,1))
      .reduceByKey(_+_)
    val reduceByVal = counts.map(x => x.swap)
    val sortedVal = reduceByVal.sortByKey(false)
    val changedVal = sortedVal.map{case (k,(x,y))=>(x,(y,k))}
    //val reduced = changedVal.reduceByKey((x, y) => if (y._2 > x._2) y else x)
    println("test2")

    //sortedVal.foreach(println)
    print("\nsıralı hali\n")
    //changedVal.foreach(println)
    val result = pairs.join(changedVal)
    var mappedResult = result.map{case (x,(y,(z,k)))=>(y,(x,(z,k)))}
 
    println("test3")

    mappedResult.foreach(println)

    /*val rdd1:RDD[(T,U)]
      val rdd2:RDD[((T,W), V)]
    */

    //sortedVal.foreach(println)
    //sortedVal.saveAsTextFile("file:///home/teaddict/Desktop/fatih/output2")

  }
}
//map bütün olarak alıyor