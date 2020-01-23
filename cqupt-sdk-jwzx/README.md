# 重庆邮电大学-教务在线-爬虫库

---

## 1. 简介

这是一个爬取重庆邮电大学**教务在线**数据的工具库，这个库可以支撑你开发属于你自己的校园应用，不再让项目终止于**无法获取校园数据**这个问题上。

本工具基于Jsoup实现爬虫逻辑，主要有如下特色点：

- **重试机制**

  基于教务在线偶尔不稳定（并发达到100以上就会500）以及你所使用的内网外入代理不稳定的原因，该爬虫引入了http请求重试机制，一次http请求失败（大多数是连接超时），爬虫会在达到最大重试次数之前自动重新连接（重试次数可自定义配置）

- **缓存机制**

  在抓取某些数据时，需要先用用户的账号密码登录教务在线，此时如果没有保存用户已登录后的cookie，会在每次抓取这些数据之前都执行一次登录操作，造成不必要的耗时。所以该爬虫使用Ehcahce来将用户已经登录后的cookie保存起来，供下次抓取用户数据的时候使用，默认失效时间30分钟。
## 2. 快速入门

1. Maven引入依赖

      ```xml
    <dependency>
        <groupId>com.juzi.cqupt</groupId>
        <artifactId>cqupt-sdk-jwzx</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

2. 普通项目使用Demo

   ```java
   // 创建配置对象
   // 构造参数为true，则用内网访问教务在线，此时该代码必须运行于内网环境
   // 构造参数为false，则用内网访问教务在线，此时该代码必须运行于外网环境
   JwzxSimpleConfig jwzxSimpleConfig = new JwzxSimpleConfig(false);
   jwzxSimpleConfig
   	// 设置一个内置账号密码，用于抓取学生照片
       .setUsername("你的账号")
       .setPassword("你的密码")
       // 设置外网访问教务在线地址
       .setDomainOut("http://jwzx.cqupt.juzibiji.top");
   
   // 基础服务
   JwzxService jwzxService = new JwzxServiceImpl(jwzxConfig);
   
   // 根据你想要的功能创建不同Service
   // 这里以抓取课表为例
   JwzxCourseTableService jwzxCourseTableService = new JwzxCourseTableServiceImpl(jwzxService);
   
   // 抓取课表
   List<JwzxCourseTable> studentCourseTable = jwzxCourseTableService.getStudentCourseTable("目标学号");
   ```
   
3. Springboot/Spring 使用 Demo

   ```java
   ```java
      ```java
         ```java
            package com.juzi.cyzs.config;
            
            import com.github.juzi214032.cqupt.sdk.jwzx.config.JwzxSimpleConfig;
            import org.springframework.context.annotation.Bean;
            import org.springframework.context.annotation.Configuration;
            
            /**
             * 爬虫配置
             *
             * @author Juzi - https://juzibiji.top
             * @since 2019/9/21 18:54
             */
            @Configuration
            public class CquptSdkConfig {
                
                /**
                 * @return 基础服务
                 */
                @Bean
                public JwzxService jwzxService() {
                    JwzxSimpleConfig jwzxSimpleConfig = new JwzxSimpleConfig(false);
                    jwzxSimpleConfig.setDomainOut("http://jwzx.cqu.pt");
                    return new JwzxServiceImpl(jwzxSimpleConfig);
                }
                
                /**
                 * 课表
                 *
                 * @return
                 */
                @Bean
                public JwzxTimetableService jwzxCourseTableService() {
                    return new JwzxCourseTableServiceImpl(this.jwzxService());
                }
                
                /**
                 * @return 学生信息
                 */
                @Bean
                public JwzxStudentInfoService jwzxStudentInfoService() {
                    return new JwzxStudentInfoServiceImpl(this.jwzxService());
                }
                
                /**
                 * @return 日期
                 */
                @Bean
                public JwzxDateService jwzxDateService() {
                    return new JwzxDateServiceImpl(this.jwzxService());
                }
                
                /**
                 * @return 教师信息
                 */
                @Bean
                public JwzxTeacherInfoService jwzxTeacherInfoService() {
                    return new JwzxTeacherInfoServiceImpl(this.jwzxService());
                }
                
                /**
                 * @return 成绩服务
                 */
                @Bean
                public JwzxGradeService jwzxGradeService() {
                    return new JwzxGradeServiceImpl(this.jwzxService());
                }
            }
            ```
         ```
      ```

## 3. 已完成接口

| 模块名称 | Service名称 | 数据接口 |
| :-------- | :----------- | :-------- |
| 基础服务 | JwzxService | 1. 登录 |
| 教室信息 | JwzxClassroomInfoService | 1. 空教室<br />2. 教室列表 |
| 学院信息 | JwzxCollegeInfoService | 1. 学院列表 |
| 教材信息 | JwzxCourseBookService | 1. 教材列表 |
| 课表信息 | JwzxCourseTableService | 1. 学生课表<br />2.教师课表 |
| 日期信息 | JwzxDateService | 1. 获取当前周次<br />2. 计算指定日期是第几周周几<br />3. 获取第一周周一的年月日<br />4. 获取每周每天对应的日期 |
| 成绩信息 | JwzxGradeService | 1. 获取成绩总表<br />2. 获取平时成绩<br />3. 获取期末成绩<br />4. 获取补考成绩<br />5. 获取GPA |
| 教务在线新闻 | JwzxNewsService | 1. 获取新闻列表<br />2. 获取新闻详情 |
| 学生信息 | JwzxStudentInfoService | 1. 学生基本信息<br />2. 学生扩展信息<br />3. 教学班学生列表<br />4. 学生照片 |
| 教师信息 | JwzxTeacherInfoService | 1. 获取教师信息 |

