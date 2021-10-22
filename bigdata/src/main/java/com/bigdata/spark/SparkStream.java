package com.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;


import java.net.URL;
import java.util.Arrays;

public class SparkStream {
    public static void main(String args[]) {
        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark_Word_Count")
                .setSparkHome("/Users/lht/bigdata/spark-2.4.3-bin-hadoop2.7")
                .setMaster("local[2]");
        SparkSession spark = SparkSession.builder()
                .appName("SparkSQL Test")
                .config(sparkConf)
                .getOrCreate();

        Dataset<Row> socketDF =spark.readStream()
                .format("socket")
                .option("host","localhost")
                .option("port",9999)
                .load();


        // Split the lines into words
        Dataset<String> words = socketDF
                .as(Encoders.STRING())
                .flatMap((FlatMapFunction<String, String>) x -> Arrays.asList(x.split(" ")).iterator(), Encoders.STRING());

       // Generate running word count
        Dataset<Row> wordCounts = words.groupBy("value").count();

        // Start running the query that prints the running counts to the console
        StreamingQuery query = wordCounts.writeStream()
                .outputMode("complete")
                .format("console")
                .start();


//        KinesisInputDStream<byte[]> kinesisStream = KinesisInputDStream.builder
//                .streamingContext(streamingContext)
//                .endpointUrl([endpoint URL])
//             .regionName([region name])
//             .streamName([streamName])
//             .initialPositionInStream([initial position])
//             .checkpointAppName([Kinesis app name])
//             .checkpointInterval([checkpoint interval])
//             .storageLevel(StorageLevel.MEMORY_AND_DISK_2)
//                .buildWithMessageHandler([message handler]);



//        socketDF.isStreaming();    // Returns True for DataFrames that have streaming sources
//
//        socketDF.printSchema();
//        // Read all the csv files written atomically in a directory
//        StructType userSchema = new StructType().add("name", "string").add("age", "integer");
//        Dataset<Row> csvDF = spark
//                .readStream()
//                .option("sep", ";")
//                .schema(userSchema)      // Specify schema of the csv files
//                .csv("/tmp/");    // Equivalent to format("csv").load("/path/to/directory")
//       // csvDF.show();
//        csvDF.javaRDD().saveAsTextFile("hdfs://localhost:9000/user/spark/streamtest");
        try {
            query.awaitTermination();
        } catch (StreamingQueryException e) {
            e.printStackTrace();
        }
    }
}
