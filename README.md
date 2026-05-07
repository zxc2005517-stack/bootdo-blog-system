# BootDo Blog System

基于 BootDo 开源项目进行二次开发的个人博客系统，用于软件工程敏捷开发与 DevOps 实践。

## 技术栈

- Spring Boot
- MyBatis
- MySQL
- Thymeleaf
- Maven

## 项目功能

- 用户登录
- 文章发布
- 文章管理
- 用户管理
- 系统日志

## 敏捷开发工作项

```mermaid
graph TD
    A[博客系统] --> B[博客管理]
    A --> C[系统管理]
    A --> D[系统监控]

    B --> E[发布文章]
    B --> F[文章列表]

    C --> G[用户管理]
    C --> H[角色管理]

    D --> I[在线用户]
    D --> J[系统日志]
