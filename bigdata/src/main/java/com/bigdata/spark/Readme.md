## 启动环境
* 启动spark 进入spark ./sbin/start-all.sh
* 启动hadoop 进入hadoop ./sbin/start-all.sh

# 提交spark任务
pyspark代码相对要好提交不需要构建assembly/uber jar ，如果是java或者scala需要创建对应项目并添加依赖，然后构建assembly 包
/Users/lht/bigdata/spark-2.4.3-bin-hadoop2.7/bin/spark-submit --master local[2] PySparkPi.py
