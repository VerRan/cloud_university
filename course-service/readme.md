# Course Management Service / 课程管理微服务

[English](#english) | [中文](#chinese)

# English

## Introduction
The Course Management Service is a core microservice responsible for all course-related operations in the Cloud University platform. It handles course creation, management, content organization, and delivery.

## Features
- Course category management
- Course CRUD operations
- Chapter/section management
- Course material management
- Course tagging system
- Course metadata handling

## API Endpoints

### Course Management
```
GET    /api/v1/courses         # List courses
POST   /api/v1/courses         # Create course
GET    /api/v1/courses/{id}    # Get course details
PUT    /api/v1/courses/{id}    # Update course
DELETE /api/v1/courses/{id}    # Delete course
```

### Category Management
```
GET    /api/v1/categories      # List categories
POST   /api/v1/categories      # Create category
PUT    /api/v1/categories/{id} # Update category
DELETE /api/v1/categories/{id} # Delete category
```

### Chapter Management
```
GET    /api/v1/courses/{courseId}/chapters      # List chapters
POST   /api/v1/courses/{courseId}/chapters      # Create chapter
PUT    /api/v1/courses/{courseId}/chapters/{id} # Update chapter
DELETE /api/v1/courses/{courseId}/chapters/{id} # Delete chapter
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
    name: course-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Development Setup

1. Prerequisites:
   - JDK 8+
   - Maven
   - Spring Cloud Config Server
   - Eureka Server

2. Build:
   ```bash
   mvn clean package
   ```

3. Run:
   ```bash
   java -jar target/course-service-0.1-SNAPSHOT.jar
   ```

4. Development Mode:
   ```bash
   java -jar target/course-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

# Chinese

## 简介
课程管理微服务是云课堂平台的核心微服务之一，负责所有与课程相关的操作。提供包括课程分类管理，课程管理，章节管理，课件管理等功能，即包含课程本身同时也包含以课程为中心的相关功能，如课程标签等。

## 功能特性
- 课程分类管理
- 课程增删改查
- 章节内容管理
- 课件资料管理
- 课程标签系统
- 课程元数据处理

## API接口

### 课程管理
```
GET    /api/v1/courses         # 获取课程列表
POST   /api/v1/courses         # 创建新课程
GET    /api/v1/courses/{id}    # 获取课程详情
PUT    /api/v1/courses/{id}    # 更新课程信息
DELETE /api/v1/courses/{id}    # 删除课程
```

### 分类管理
```
GET    /api/v1/categories      # 获取分类列表
POST   /api/v1/categories      # 创建新分类
PUT    /api/v1/categories/{id} # 更新分类
DELETE /api/v1/categories/{id} # 删除分类
```

### 章节管理
```
GET    /api/v1/courses/{courseId}/chapters      # 获取章节列表
POST   /api/v1/courses/{courseId}/chapters      # 创建新章节
PUT    /api/v1/courses/{courseId}/chapters/{id} # 更新章节
DELETE /api/v1/courses/{courseId}/chapters/{id} # 删除章节
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
    name: course-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## 开发设置

1. 环境要求：
   - JDK 8+
   - Maven
   - Spring Cloud Config Server
   - Eureka Server

2. 构建：
   ```bash
   mvn clean package
   ```

3. 运行：
   ```bash
   java -jar target/course-service-0.1-SNAPSHOT.jar
   ```

4. 开发模式：
   ```bash
   java -jar target/course-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
