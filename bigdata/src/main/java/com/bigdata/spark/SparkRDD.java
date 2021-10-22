package com.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.List;

public class SparkRDD {

    static class ContainsFunction implements Function<String,Boolean> {

        private static final long serialVersionUID = 1L;

        private String query;

        public ContainsFunction(String query){
            this.query = query;
        }


        @Override
        public Boolean call(String s) {
            return s.contains(query);
        }
    }

        public static  void main(String args[]){
            String logFile = "/Users/lht/workspace/cloud_university/README.md";
            SparkConf sparkConf = new SparkConf()
                    .setAppName("Spark_Word_Count")
                    .setSparkHome("/Users/lht/bigdata/spark-2.4.3-bin-hadoop2.7")
                    .setMaster("local[2]");
            JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
            JavaRDD<String> rdd = sparkContext.textFile(logFile);
            JavaRDD<String> serverStringRDD = rdd
                    .filter(new ContainsFunction("server"));
            System.out.println("current file have "+serverStringRDD.count()+" server string");

            JavaRDD<String> catalogStringRDD = rdd.filter(s->s.contains("目录"));
            System.out.println("current file have "+catalogStringRDD.count()+" 目录 string");

            JavaRDD<String> unionRdd = serverStringRDD.union(catalogStringRDD);

            List<String> list = unionRdd.collect();//获取RDD中的数据并返回列表
            list.forEach(s->System.out.println(s));//使用lambda 打印
            /**讲RDD数据写入HDFS中**/
            unionRdd.saveAsTextFile("hdfs://localhost:9000/user/spark/wordcount");
            sparkContext.stop();
          }

    }
