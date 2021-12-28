# 项目介绍
  本项目是基于Springboot 和 SpringCloud为基础的微服务架构实践项目。
  同时也可以作为基础框架作为新项目的微服务基础架构。本项目的业务场景是已
  网上课堂为样例制作的一个demo版本，重点介绍了将基于springcloud的应用部署在不同的环境中，包括虚拟机（如 AWS EC2），Docker，K8s（AWS EKS）等，同时介绍了如何部署Kubesphere用于k8
  s集群管理，降低服务治理和集群运维成本。
# 技术架构图
![](https://github.com/VerRan/photo/blob/master/%E7%B3%BB%E7%BB%9F%E6%8A%80%E6%9C%AF%E6%9E%B6%E6%9E%84%E5%9B%BE.png)
# 部署架构图
下面是应用部署在AWS EKS上的架构图
![](https://github.com/VerRan/photo/blob/master/springCloud%20on%20EKS.png)
# 如何开始
## 源代码运行
* 下载代码 ``git clone ``
* 删除根目录pom.xml 中的模块 mvn install parent pom
* 进入代码根目录执行 ``mvn clean package -Dskiptests`` 编译打包代码
* 进入server/discovery/target 目录执行 ``java -jar discovery-0.1-SNAPSHOT.jar`` 启动eureka服务
* 进入server/hystrix/target 目录执行 ``java -jar hystrix-0.1-SNAPSHOT.jar`` 启动hystrix 服务
* 进入server/zipkin/target 目录执行 ``java -jar zipkin-0.1-SNAPSHOT.jar`` zipkin 服务
* 进入server/monitor/target 目录执行 ``java -jar monitor-0.1-SNAPSHOT.jar`` springbootadmin 服务
* 进入server/gateway/target 目录执行 ``java -jar gateway-0.1-SNAPSHOT.jar`` API网关 服务
* 进入server/config/target 目录执行 ``java -jar config-0.1-SNAPSHOT.jar`` 统一配置 服务
启动业务代码
* 进入user-service/target 目录执行 ``java -jar user-0.1-SNAPSHOT.jar`` 启动用户微服务
## 容器运行方式
* 当前docker镜像通过ECR进行管理
* 当前项目已编写了docker file ，可以通过docker自行构建镜像部署
* docker-compose 进行docker编排方式 ，docker-compose 使用可自行查询，docker-compose.yml在项目根目录下
## k8s运行(托管的AWS EKS)
这里我们采用AWS托管的k8s集群（EKS）部署我们的应用，当您将自己的微服务运行到k8s时需要以下步骤：
1. 微服务容器化，编写 docker file 对应微服务中的Dockerfile
2. 编写容器编排文件，这里主要是针对k8s的编排文件编写，用于指导将容器融合编排并运行在K8s中，对应 Deployment.xml ,service.xml等
3. 通过kubctl 部署微服务
4. 通过kubesphere进行eks集群和服务的管理
## 使用kubesphere管理集群
* 执行如下yaml安装kubesphere
```
kubectl apply -f https://github.com/kubesphere/ks-installer/releases/download/v3.2.0/kubesphere-installer.yaml 
kubectl apply -f https://github.com/kubesphere/ks-installer/releases/download/v3.2.0/cluster-configuration.yaml
```
* 检查日志：
```
kubectl logs -n kubesphere-system $(kubectl get pod -n kubesphere-system -l app=ks-install -o jsonpath='{.items[0].metadata.name}') -f
```
* 修改服务类型为loadbalancer便于访问
```
kubectl get svc -n kubesphere-system
kubectl edit svc ks-console -n kubesphere-system  --将nodeType修改为LoadBalancer
kubectl get svc -n kubesphere-system --查看elb地址
使用elb的dns访问kuberspehere web console
```
* 登陆web
默认用户名密码 admin/P@88w0rd</br>

# 效果展示与说明
## 微服务监控与管理
![](https://github.com/VerRan/photo/blob/master/%E5%BE%AE%E6%9C%8D%E5%8A%A1%E7%9B%91%E6%8E%A7-springbootadmin.png)
## 微服务调用链监控
![](https://github.com/VerRan/photo/blob/master/%E5%BE%AE%E6%9C%8D%E5%8A%A1-%E8%B0%83%E7%94%A8%E9%93%BE%E7%9B%91%E6%8E%A7.png)
## 微服务熔断与处理
![](https://github.com/VerRan/photo/blob/master/%E6%9C%8D%E5%8A%A1%E7%86%94%E6%96%AD%E7%9B%91%E6%8E%A7%E4%B8%8E%E5%A4%84%E7%90%86.png)
# 系统模块说明
![总体目录](https://github.com/VerRan/photo/blob/master/%E6%A8%A1%E5%9D%97%E8%AF%B4%E6%98%8E.png)
![微服务目录](https://github.com/VerRan/photo/blob/master/%E6%A8%A1%E5%9D%97%E8%AF%B4%E6%98%8E2.png)
## Kubesphere管理集群
![](https://github.com/VerRan/photo/blob/master/kubesphere.png)
