# User Management Service / 用户中心微服务

[English](#english) | [中文](#chinese)

# English

## Introduction
The User Management Service is a core microservice responsible for managing user-related operations in the Cloud University platform. It handles user profiles, authentication, and user-related activities such as comments, course enrollments, and Q&A interactions.

## Features
- User profile management
- User authentication and authorization
- User comments management
- Course enrollment tracking
- Q&A system participation
- User activity history
- User preferences and settings

## API Endpoints

### User Management
```
GET    /api/v1/users           # List users
POST   /api/v1/users           # Create user
GET    /api/v1/users/{id}      # Get user details
PUT    /api/v1/users/{id}      # Update user
DELETE /api/v1/users/{id}      # Delete user
```

### User Profile
```
GET    /api/v1/users/{id}/profile      # Get user profile
PUT    /api/v1/users/{id}/profile      # Update profile
PUT    /api/v1/users/{id}/password     # Change password
PUT    /api/v1/users/{id}/avatar       # Update avatar
```

### User Activities
```
GET    /api/v1/users/{id}/courses      # List enrolled courses
GET    /api/v1/users/{id}/comments     # List user comments
GET    /api/v1/users/{id}/questions    # List user questions
GET    /api/v1/users/{id}/answers      # List user answers
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
    name: user-service
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

2. Database Setup:
   ```sql
   CREATE DATABASE cloud_university;
   USE cloud_university;
   -- Tables will be created automatically by JPA
   ```

3. Build:
   ```bash
   mvn clean package
   ```

4. Run:
   ```bash
   java -jar target/user-service-0.1-SNAPSHOT.jar
   ```

5. Development Mode:
   ```bash
   java -jar target/user-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## Security

- Password hashing using BCrypt
- JWT-based authentication
- Role-based access control (RBAC)
- Session management
- API rate limiting

# Chinese

## 简介
用户中心微服务是云课堂平台的核心微服务之一，主要负责用户信息的管理和用户相关功能的提供。包括用户基本信息管理、用户评论、课程学习记录、问答互动等用户相关的服务。

## 功能特性
- 用户信息管理
- 用户认证和授权
- 用户评论管理
- 课程学习记录
- 问答系统参与
- 用户活动历史
- 用户偏好设置

## API接口

### 用户管理
```
GET    /api/v1/users           # 获取用户列表
POST   /api/v1/users           # 创建新用户
GET    /api/v1/users/{id}      # 获取用户详情
PUT    /api/v1/users/{id}      # 更新用户信息
DELETE /api/v1/users/{id}      # 删除用户
```

### 用户档案
```
GET    /api/v1/users/{id}/profile      # 获取用户档案
PUT    /api/v1/users/{id}/profile      # 更新档案
PUT    /api/v1/users/{id}/password     # 修改密码
PUT    /api/v1/users/{id}/avatar       # 更新头像
```

### 用户活动
```
GET    /api/v1/users/{id}/courses      # 获取已报名课程
GET    /api/v1/users/{id}/comments     # 获取用户评论
GET    /api/v1/users/{id}/questions    # 获取用户提问
GET    /api/v1/users/{id}/answers      # 获取用户回答
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
    name: user-service
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

2. 数据库设置：
   ```sql
   CREATE DATABASE cloud_university;
   USE cloud_university;
   -- 表结构由JPA自动创建
   ```

3. 构建：
   ```bash
   mvn clean package
   ```

4. 运行：
   ```bash
   java -jar target/user-service-0.1-SNAPSHOT.jar
   ```

5. 开发模式：
   ```bash
   java -jar target/user-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## 安全机制

- 使用BCrypt进行密码加密
- 基于JWT的身份认证
- 基于角色的访问控制（RBAC）
- 会话管理
- API访问频率限制
