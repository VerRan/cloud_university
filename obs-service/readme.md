# Object Storage Service (OBS) / 对象存储服务

[English](#english) | [中文](#chinese)

# English

## Introduction
The Object Storage Service (OBS) is responsible for all document management operations in the Cloud University platform. It handles the upload, management, and download of various document formats including videos, images, presentations, word documents, and PDFs.

## Development Phases

### Phase 1: Traditional File Storage
- Traditional file upload/download service
- Local file system storage
- Basic file management capabilities

### Phase 2: Huawei OBS Integration
- Migration to Huawei Object Storage Service
- Enhanced media processing capabilities
- Advanced encoding/decoding services

## Features
- Multi-format file support
  - Videos
  - Images
  - PowerPoint presentations
  - Word documents
  - PDFs
- File upload and download
- Document management
- File search capabilities
- Version control
- Access control
- Media processing
- Thumbnail generation
- Format conversion

## API Endpoints

### File Operations
```
POST   /api/v1/files/upload          # Upload file
GET    /api/v1/files/{id}/download   # Download file
DELETE /api/v1/files/{id}            # Delete file
GET    /api/v1/files/{id}            # Get file metadata
GET    /api/v1/files                 # List files
```

### File Management
```
PUT    /api/v1/files/{id}/metadata   # Update file metadata
POST   /api/v1/files/{id}/copy       # Copy file
POST   /api/v1/files/{id}/move       # Move file
PUT    /api/v1/files/{id}/access     # Update access control
```

### Media Processing
```
POST   /api/v1/files/{id}/transcode  # Transcode media
POST   /api/v1/files/{id}/thumbnail  # Generate thumbnail
GET    /api/v1/files/{id}/preview    # Get file preview
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
    name: obs-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server
  servlet:
    multipart:
      max-file-size: 500MB
      max-request-size: 500MB

# Phase 1: Local Storage Configuration
storage:
  local:
    base-path: /data/files
    temp-path: /data/temp

# Phase 2: Huawei OBS Configuration
huawei:
  obs:
    endpoint: your-obs-endpoint
    access-key: your-access-key
    secret-key: your-secret-key
    bucket-name: your-bucket-name
```

## Development Setup

1. Prerequisites:
   - JDK 8+
   - Maven
   - Spring Cloud Config Server
   - Eureka Server
   - (Phase 2) Huawei OBS Account

2. Build:
   ```bash
   mvn clean package
   ```

3. Run:
   ```bash
   java -jar target/obs-service-0.1-SNAPSHOT.jar
   ```

4. Development Mode:
   ```bash
   java -jar target/obs-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## Integration Points

- **Course Service**: Course material storage
- **Teacher Service**: Teaching material management
- **Media Service**: Media file processing
- **All Search Service**: File indexing and search

# Chinese

## 简介
对象存储服务（OBS）负责云课堂平台的所有文档管理操作。包括课件信息的上传、管理、下载，支持视频、图片、PPT、Word、PDF等不同格式的文档信息。

## 开发阶段

### 第一阶段：传统文件存储
- 传统的文件上传下载服务方式
- 本地文件系统存储
- 基础文件管理功能

### 第二阶段：华为OBS集成
- 迁移至华为对象存储服务
- 增强的媒体处理能力
- 高级编解码服务

## 功能特性
- 多格式文件支持
  - 视频文件
  - 图片文件
  - PPT演示文稿
  - Word文档
  - PDF文档
- 文件上传下载
- 文档管理
- 文件检索
- 版本控制
- 访问控制
- 媒体处理
- 缩略图生成
- 格式转换

## API接口

### 文件操作
```
POST   /api/v1/files/upload          # 上传文件
GET    /api/v1/files/{id}/download   # 下载文件
DELETE /api/v1/files/{id}            # 删除文件
GET    /api/v1/files/{id}            # 获取文件元数据
GET    /api/v1/files                 # 获取文件列表
```

### 文件管理
```
PUT    /api/v1/files/{id}/metadata   # 更新文件元数据
POST   /api/v1/files/{id}/copy       # 复制文件
POST   /api/v1/files/{id}/move       # 移动文件
PUT    /api/v1/files/{id}/access     # 更新访问控制
```

### 媒体处理
```
POST   /api/v1/files/{id}/transcode  # 转码媒体
POST   /api/v1/files/{id}/thumbnail  # 生成缩略图
GET    /api/v1/files/{id}/preview    # 获取文件预览
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
    name: obs-service
  cloud:
    config:
      discovery:
        enabled: true
        serviceId: config-server
  servlet:
    multipart:
      max-file-size: 500MB
      max-request-size: 500MB

# 第一阶段：本地存储配置
storage:
  local:
    base-path: /data/files
    temp-path: /data/temp

# 第二阶段：华为OBS配置
huawei:
  obs:
    endpoint: your-obs-endpoint
    access-key: your-access-key
    secret-key: your-secret-key
    bucket-name: your-bucket-name
```

## 开发设置

1. 环境要求：
   - JDK 8+
   - Maven
   - Spring Cloud Config Server
   - Eureka Server
   - （第二阶段）华为OBS账号

2. 构建：
   ```bash
   mvn clean package
   ```

3. 运行：
   ```bash
   java -jar target/obs-service-0.1-SNAPSHOT.jar
   ```

4. 开发模式：
   ```bash
   java -jar target/obs-service-0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

## 服务集成

- **课程服务**: 课程资料存储
- **教师服务**: 教学资料管理
- **媒体服务**: 媒体文件处理
- **统一搜索服务**: 文件索引和搜索
