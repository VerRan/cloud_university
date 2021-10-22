package com.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
/**
 * https://spark.apache.org/docs/latest/sql-getting-started.html
 *
 * **/
public class SparkWithHive {
    public static void main(String args[]){
        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark_Word_Count")
                .setSparkHome("/Users/lht/bigdata/spark-2.4.3-bin-hadoop2.7")
                .setMaster("local[2]");
        SparkSession sparkSession =SparkSession.builder()
                .appName("SparkSQL Test")
                .config(sparkConf)
                .getOrCreate();
        Dataset<String> df= sparkSession.read().textFile("/tmp/users.txt");
        df.show();

    }
}
