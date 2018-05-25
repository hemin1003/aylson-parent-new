
# 简洁版后台系统源码

## 开发框架操作说明

项目db脚本aylson_2018-04-08.sql.gz导入后默认的用户名和密码分别为superadmin/123456

1. 下载代码

2. 装好maven ,jdk工具

3. 复制aylson-manage/settings.xml到用户目录.m2下（利用国内阿里镜像，加速下载依赖包）

4. 进入项目目录aylson-manage，运行mvn jetty:run命令即可启动项目（默认访问地址：http://localhost:8080/manage/login.jsp）
（也可导入开发工具，然后进行相关jetty配置）


## [后台管理系统源码](https://github.com/hemin1003/aylson-parent)

基于SpringMVC4+EasyUI开发的[后台管理系统](https://github.com/hemin1003/aylson-parent)，已投入生产线上使用

体验系统地址：http://wuyangshiye.cn:8280/manage/login.jsp

体验账号/密码，test1001/a12345678

## 新增爬虫项目统计信息

爬虫信息框架，已实现抓取今日头条数据，测试数据每日均抓一万条左右

基于[JAVA爬虫框架实战](https://github.com/hemin1003/java-spider)实现

## 配套的前端API实现项目实战

基于SpringBoot实现的接口API系统，集成了拦截器，日志处理，mysql，mybatis, oauth2.0, spring secutity等功能，已投入生产线上使用

接口API系统源码：https://github.com/hemin1003/yfax-parent

## [关于我](http://heminit.com/about/)

欢迎交流问题，可加QQ469580884，一起探讨交流问题

[我的博客地址](http://blog.csdn.net/hemin1003)

[个人域名](http://heminit.com)
