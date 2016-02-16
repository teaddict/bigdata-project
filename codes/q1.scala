package random

import org.apache.spark.{
SparkContext,
SparkConf
}
import SparkContext._

object UserCount {


  def main(args: Array[String]): Unit = {

    /*
    if (args.length < 1) {
      //System.err.println(getUsage())
      System.exit(1)
    }
    */


    val conf = new SparkConf()
      .setAppName("Location Count")
    .setMaster("local")
    .set("spark.logConf","false")
    .set("spark.executor.memory","1g")
    val sc = new SparkContext(conf)

    val textFile = sc.textFile("file:///home/teaddict/Desktop/fatih/totalCheckins.txt")
    val counts = textFile.flatMap(line => line.split("\\t+").take(1))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    val reduceByVal = counts.map(x => x.swap)
    val sortedVal = reduceByVal.sortByKey(false).take(100)
    sortedVal.foreach(println)
    //sortedVal.saveAsTextFile("file:///home/teaddict/Desktop/fatih/output")

  }
}
//flatMap collection olarak görüyor
//bu yüzden ayrı ayrı alıyor char şeklinde
