# All Search Service / 统一搜索服务

[English](#english) | [中文](#chinese)

# English

## Introduction
The All Search Service is a unified search microservice that provides centralized search capabilities across the entire Cloud University platform. It aggregates and indexes content from various services to enable efficient and comprehensive search functionality.

## Features
- Full-text search across all platform content
- Multi-entity search (courses, teachers, materials, etc.)
- Advanced filtering and faceted search
- Search result highlighting
- Search suggestions and autocomplete
- Search analytics and trending queries
- Customizable relevance scoring

## Search Domains

### Course Search
- Course titles and descriptions
- Course content and materials
- Course tags and categories
- Course reviews and ratings

### Teacher Search
- Teacher profiles
- Teaching areas and expertise
- Course offerings
- Qualifications and certifications

### Content Search
- Course materials
- Documents (PDF, PPT, Word)
- Video transcripts
- Q&A content
- Discussion forums

## API Endpoints

### Universal Search
```
GET    /api/v1/search              # Universal search across all domains
GET    /api/v1/search/suggest      # Get search suggestions
```

### Domain-Specific Search
```
GET    /api/v1/search/courses      # Search courses
GET    /api/v1/search/teachers     # Search teachers
GET    /api/v1/search/materials    # Search course materials
GET    /api/v1/search/qa           # Search Q&A content
```

### Search Management
```
POST   /api/v1/search/index        # Trigger content indexing
GET    /api/v1/search/stats        # Get search statistics
PUT    /api/v1/search/config       # Update search configuration
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
    name: all-search-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server
  elasticsearch:
    rest:
      uris: http://localhost:9200

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Development Setup

1. Prerequisites:
   - JDK 8+
   - Maven
   - Elasticsearch
   - Spring Cloud Config Server
   - Eureka Server

2. Elasticsearch Setup:
   ```bash
   # Start Elasticsearch
   ./elasticsearch
   
   # Verify it's running
   curl http://localhost:9200
   ```

3. Build:
   ```bash
   mvn clean package
   ```

4. Run:
   ```bash
   java -jar target/all-search-service-0.1-SNAPSHOT.jar
   ```

5. Development Mode:
   ```bash
   java -jar target/all-search-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## Integration Points

- **Course Service**: Course content indexing
- **Teacher Service**: Teacher profile indexing
- **User Service**: User-generated content indexing
- **Media Service**: Media metadata indexing
- **System Service**: System configuration

# Chinese

## 简介
统一搜索服务是云课堂平台的核心微服务之一，提供跨平台的集中式搜索功能。它聚合和索引来自各个服务的内容，实现高效和全面的搜索能力。

## 功能特性
- 全平台内容全文搜索
- 多实体搜索（课程、教师、资料等）
- 高级过滤和分面搜索
- 搜索结果高亮
- 搜索建议和自动完成
- 搜索分析和热门查询
- 可自定义相关性评分

## 搜索范围

### 课程搜索
- 课程标题和描述
- 课程内容和资料
- 课程标签和分类
- 课程评价和评分

### 教师搜索
- 教师档案
- 教学领域和专长
- 开设课程
- 资质认证

### 内容搜索
- 课程资料
- 文档（PDF、PPT、Word）
- 视频字幕
- 问答内容
- 讨论论坛

## API接口

### 通用搜索
```
GET    /api/v1/search              # 全域搜索
GET    /api/v1/search/suggest      # 获取搜索建议
```

### 领域搜索
```
GET    /api/v1/search/courses      # 搜索课程
GET    /api/v1/search/teachers     # 搜索教师
GET    /api/v1/search/materials    # 搜索课程资料
GET    /api/v1/search/qa           # 搜索问答内容
```

### 搜索管理
```
POST   /api/v1/search/index        # 触发内容索引
GET    /api/v1/search/stats        # 获取搜索统计
PUT    /api/v1/search/config       # 更新搜索配置
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
    name: all-search-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server
  elasticsearch:
    rest:
      uris: http://localhost:9200

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## 开发设置

1. 环境要求：
   - JDK 8+
   - Maven
   - Elasticsearch
   - Spring Cloud Config Server
   - Eureka Server

2. Elasticsearch设置：
   ```bash
   # 启动Elasticsearch
   ./elasticsearch
   
   # 验证运行状态
   curl http://localhost:9200
   ```

3. 构建：
   ```bash
   mvn clean package
   ```

4. 运行：
   ```bash
   java -jar target/all-search-service-0.1-SNAPSHOT.jar
   ```

5. 开发模式：
   ```bash
   java -jar target/all-search-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## 服务集成

- **课程服务**: 课程内容索引
- **教师服务**: 教师档案索引
- **用户服务**: 用户生成内容索引
- **媒体服务**: 媒体元数据索引
- **系统服务**: 系统配置
