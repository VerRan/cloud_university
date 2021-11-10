package com.bigdata.spark;

import com.bigdata.spark.vo.Employee;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.*;

import java.util.Collections;

import static org.apache.spark.sql.functions.col;

public class SparkSQL {
    public static void main(String args[]){
        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark_Word_Count")
                .setSparkHome("/Users/lht/bigdata/spark-2.4.3-bin-hadoop2.7")
                .setMaster("local[2]");
        SparkSession sparkSession =SparkSession.builder()
                .appName("SparkSQL Test")
                .config(sparkConf)
                .getOrCreate();
        Dataset<String> users_df= sparkSession.read().textFile("users.txt");
        users_df.show();

        Dataset<Row> employee_df= sparkSession.read().json("bigdata/src/main/resources/employee.json");
        employee_df.show();
//        employee_df.javaRDD().saveAsTextFile("hdfs://localhost:9000/user/spark/employee");
        employee_df.printSchema();
        employee_df.select(col("name"),col("salary").plus(1000)).show();//所有员工工资加1000

        employee_df.filter(col("salary").gt(4000)).show();//找出工资大于4000的员工

        /*Temporary views in Spark SQL are session-scoped and will disappear
         if the session that creates it terminates**/
        employee_df.createOrReplaceTempView("employeeTmp");//根据导入的数据创建临时视图
        Dataset<Row> employeeGt4000 =  sparkSession.sql("select * from employeeTmp where salary>4000");
        employeeGt4000.show();


        employee_df.createOrReplaceGlobalTempView("employee");
        // Global temporary view is tied to a system preserved database `global_temp`
        Dataset<Row> employeeList =  sparkSession.newSession().sql("select * from global_temp.employee where salary>4000");
        employeeList.show();

        Employee employee =new Employee();
        employee.setName("lht");
        employee.setSalary(10000);
        Encoder<Employee> employeeEncoder = Encoders.bean(Employee.class);
        Dataset<Employee> employeeDataset = sparkSession
                .createDataset(Collections.singletonList(employee),employeeEncoder);
        employeeDataset.show();
    }
}
