# Cloud University Platform / 云课堂平台

[English](#english) | [中文](#chinese)

# English

## Introduction
This project is a microservices architecture practice project based on SpringBoot and SpringCloud. It can also serve as a foundation framework for new microservices projects. The business scenario is an online classroom demo version, focusing on deploying SpringCloud applications in different environments, including virtual machines (like AWS EC2), Docker, K8s (AWS EKS), etc. It also introduces how to deploy KubeSphere for k8s cluster management to reduce service governance and cluster maintenance costs.

## Technical Architecture
![Technical Architecture](https://github.com/VerRan/photo/blob/master/%E7%B3%BB%E7%BB%9F%E6%8A%80%E6%9C%AF%E6%9E%B6%E6%9E%84%E5%9B%BE.png)

## Deployment Architecture
Below is the architecture diagram when deployed on AWS EKS:
![AWS EKS Deployment](https://github.com/VerRan/photo/blob/master/springCloud%20on%20EKS.png)

## Services Overview

### Core Services
- **User Service**: User management and authentication
- **Teacher Service**: Teacher profile and course management
- **Course Service**: Course content and delivery
- **System Service**: System configurations
- **Offer Service**: Course offerings and scheduling
- **Promotion Service**: Marketing and promotions

### Infrastructure Services
- **Discovery Service**: Service registration (Eureka)
- **Gateway**: API routing and load balancing
- **Config Server**: Centralized configuration
- **Monitor**: System monitoring
- **Hystrix**: Circuit breaker
- **Zipkin**: Distributed tracing

### Media & Search Services
- **Media Service AWS**: AWS-based media handling
- **OBS Service**: Object storage
- **All Search Service**: Unified search
- **Big Data**: Analytics processing

## Getting Started

### Source Code Setup
1. Clone repository: \`git clone\`
2. Remove modules from root pom.xml and install parent pom
3. Build: \`mvn clean package -Dskiptests\`
4. Start services in order:
   ```bash
   # Start infrastructure services first
   cd server/discovery/target && java -jar discovery-0.1-SNAPSHOT.jar
   cd server/hystrix/target && java -jar hystrix-0.1-SNAPSHOT.jar
   cd server/zipkin/target && java -jar zipkin-0.1-SNAPSHOT.jar
   cd server/monitor/target && java -jar monitor-0.1-SNAPSHOT.jar
   cd server/gateway/target && java -jar gateway-0.1-SNAPSHOT.jar
   cd server/config/target && java -jar config-0.1-SNAPSHOT.jar
   
   # Then start business services
   cd user-service/target && java -jar user-0.1-SNAPSHOT.jar
   # Start other services as needed
   ```

### Docker Compose Setup
- Images are managed through ECR
- Dockerfiles are provided for custom builds
- Use docker-compose.yml in project root for orchestration

### Kubernetes Setup (AWS EKS)
1. Containerize microservices using provided Dockerfiles
2. Use provided k8s manifests (deployment.yaml, service.yaml)
3. Deploy using kubectl
4. Manage using KubeSphere

### KubeSphere Setup
```bash
# Install KubeSphere
kubectl apply -f https://github.com/kubesphere/ks-installer/releases/download/v3.2.0/kubesphere-installer.yaml 
kubectl apply -f https://github.com/kubesphere/ks-installer/releases/download/v3.2.0/cluster-configuration.yaml

# Check installation
kubectl logs -n kubesphere-system $(kubectl get pod -n kubesphere-system -l app=ks-install -o jsonpath='{.items[0].metadata.name}') -f

# Configure LoadBalancer
kubectl edit svc ks-console -n kubesphere-system

# Access via ELB DNS
# Default credentials: admin/P@88w0rd
```

## Monitoring & Management

### Service Monitoring
![SpringBoot Admin](https://github.com/VerRan/photo/blob/master/%E5%BE%AE%E6%9C%8D%E5%8A%A1%E7%9B%91%E6%8E%A7-springbootadmin.png)

### Distributed Tracing
![Zipkin Tracing](https://github.com/VerRan/photo/blob/master/%E5%BE%AE%E6%9C%8D%E5%8A%A1-%E8%B0%83%E7%94%A8%E9%93%BE%E7%9B%91%E6%8E%A7.png)

### Circuit Breaking
![Hystrix Dashboard](https://github.com/VerRan/photo/blob/master/%E6%9C%8D%E5%8A%A1%E7%86%94%E6%96%AD%E7%9B%91%E6%8E%A7%E4%B8%8E%E5%A4%84%E7%90%86.png)

### KubeSphere Management
![KubeSphere](https://github.com/VerRan/photo/blob/master/kubesphere.png)

# Chinese

## 项目介绍
本项目是基于Springboot 和 SpringCloud为基础的微服务架构实践项目。同时也可以作为基础框架作为新项目的微服务基础架构。本项目的业务场景是已网上课堂为样例制作的一个demo版本，重点介绍了将基于springcloud的应用部署在不同的环境中，包括虚拟机（如 AWS EC2），Docker，K8s（AWS EKS）等，同时介绍了如何部署Kubesphere用于k8s集群管理，降低服务治理和集群运维成本。

[Original Chinese content preserved...]
