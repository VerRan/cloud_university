# Teacher Management Service / 讲师管理微服务

[English](#english) | [中文](#chinese)

# English

## Introduction
The Teacher Management Service is a core microservice that handles all teacher-related operations in the Cloud University platform. It provides teacher profile management and teacher-centric functionalities including qualification management, course material handling, Q&A sessions, exam management, and live streaming capabilities.

## Features
- Teacher profile management
- Teacher qualification application and verification
- Course material upload/download
- Student Q&A management
- Exam creation and management
- Live streaming session management
- Teaching schedule management
- Performance analytics

## API Endpoints

### Teacher Management
```
GET    /api/v1/teachers           # List teachers
POST   /api/v1/teachers           # Register teacher
GET    /api/v1/teachers/{id}      # Get teacher details
PUT    /api/v1/teachers/{id}      # Update teacher profile
DELETE /api/v1/teachers/{id}      # Delete teacher
```

### Qualification Management
```
POST   /api/v1/teachers/{id}/qualifications      # Submit qualification
GET    /api/v1/teachers/{id}/qualifications      # Get qualification status
PUT    /api/v1/teachers/{id}/qualifications/{id} # Update qualification
```

### Course Materials
```
POST   /api/v1/materials                    # Upload material
GET    /api/v1/materials                    # List materials
GET    /api/v1/materials/{id}               # Download material
DELETE /api/v1/materials/{id}               # Delete material
GET    /api/v1/teachers/{id}/materials      # List teacher's materials
```

### Q&A Management
```
GET    /api/v1/teachers/{id}/questions      # List questions
POST   /api/v1/questions/{id}/answer        # Answer question
PUT    /api/v1/answers/{id}                 # Update answer
```

### Exam Management
```
POST   /api/v1/exams                  # Create exam
GET    /api/v1/teachers/{id}/exams    # List teacher's exams
PUT    /api/v1/exams/{id}             # Update exam
DELETE /api/v1/exams/{id}             # Delete exam
```

### Live Streaming
```
POST   /api/v1/streams               # Create stream
GET    /api/v1/teachers/{id}/streams # List scheduled streams
PUT    /api/v1/streams/{id}          # Update stream
DELETE /api/v1/streams/{id}          # Cancel stream
```

## Configuration

### Application Properties
The service supports different profiles (dev/test/product) with corresponding configuration files:
- application-dev.yml
- application-test.yml
- application-product.yml

Key configurations:
```yaml
spring:
  application:
    name: teacher-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server
  datasource:
    url: jdbc:mysql://localhost:3306/cloud_university
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Development Setup

1. Prerequisites:
   - JDK 8+
   - Maven
   - MySQL
   - Spring Cloud Config Server
   - Eureka Server

2. Build:
   ```bash
   mvn clean package
   ```

3. Run:
   ```bash
   java -jar target/teacher-service-0.1-SNAPSHOT.jar
   ```

4. Development Mode:
   ```bash
   java -jar target/teacher-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## Integration Points

- **Course Service**: For course management
- **User Service**: For basic user information
- **Media Service**: For handling course materials and recordings
- **OBS Service**: For live streaming integration
- **System Service**: For system-wide configurations

# Chinese

## 简介
讲师管理微服务是云课堂平台的核心微服务之一，提供教师信息管理以及以教师为视角的相关服务。包括讲师资格申请、课件管理、课程答疑、考试管理、直播管理等扩展功能。

## 功能特性
- 教师信息管理
- 教师资格申请与认证
- 课件上传下载
- 学生答疑管理
- 考试创建与管理
- 直播课程管理
- 教学日程管理
- 教学效果分析

## API接口

### 教师管理
```
GET    /api/v1/teachers           # 获取教师列表
POST   /api/v1/teachers           # 注册教师
GET    /api/v1/teachers/{id}      # 获取教师详情
PUT    /api/v1/teachers/{id}      # 更新教师信息
DELETE /api/v1/teachers/{id}      # 删除教师
```

### 资格管理
```
POST   /api/v1/teachers/{id}/qualifications      # 提交资格申请
GET    /api/v1/teachers/{id}/qualifications      # 获取资格状态
PUT    /api/v1/teachers/{id}/qualifications/{id} # 更新资格信息
```

### 课件管理
```
POST   /api/v1/materials                    # 上传课件
GET    /api/v1/materials                    # 获取课件列表
GET    /api/v1/materials/{id}               # 下载课件
DELETE /api/v1/materials/{id}               # 删除课件
GET    /api/v1/teachers/{id}/materials      # 获取教师课件
```

### 答疑管理
```
GET    /api/v1/teachers/{id}/questions      # 获取问题列表
POST   /api/v1/questions/{id}/answer        # 回答问题
PUT    /api/v1/answers/{id}                 # 更新答案
```

### 考试管理
```
POST   /api/v1/exams                  # 创建考试
GET    /api/v1/teachers/{id}/exams    # 获取教师考试
PUT    /api/v1/exams/{id}             # 更新考试
DELETE /api/v1/exams/{id}             # 删除考试
```

### 直播管理
```
POST   /api/v1/streams               # 创建直播
GET    /api/v1/teachers/{id}/streams # 获取直播计划
PUT    /api/v1/streams/{id}          # 更新直播
DELETE /api/v1/streams/{id}          # 取消直播
```

## 配置说明

### 应用配置
服务支持不同环境配置（开发/测试/生产），对应配置文件：
- application-dev.yml
- application-test.yml
- application-product.yml

主要配置项：
```yaml
spring:
  application:
    name: teacher-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server
  datasource:
    url: jdbc:mysql://localhost:3306/cloud_university
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## 开发设置

1. 环境要求：
   - JDK 8+
   - Maven
   - MySQL
   - Spring Cloud Config Server
   - Eureka Server

2. 构建：
   ```bash
   mvn clean package
   ```

3. 运行：
   ```bash
   java -jar target/teacher-service-0.1-SNAPSHOT.jar
   ```

4. 开发模式：
   ```bash
   java -jar target/teacher-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## 服务集成

- **课程服务**: 课程管理
- **用户服务**: 基础用户信息
- **媒体服务**: 课件和录像处理
- **OBS服务**: 直播集成
- **系统服务**: 系统配置
