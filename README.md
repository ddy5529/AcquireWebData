# AcquireWebData
### 本项目是基于JAVA，以获取新浪财经的股票数据作为股票交易的依据 为目的的项目。

本项目使用了以下技术：
* 1，websocket
* 2, mysql
* 3, spring jpa 
* 4, spring
* 5, fastjson
* 6, 自定义注解
* 7，AOP
* 8，swagger
* 9，maven
* 10，springboot


### 运行需要修改服务器配置：
由于我没有研究出如何从另外一个配置文件读取内容的写法，所以大家运行程序的时候需要修改配置文件 application.yml



### 问题集：
* 1，controller中的接口返回字符串时，没有找到对应的html页面，报javax.servlet.ServletException: Could not resolve view with name 错误
解决方案是导入spring-boot-starter-thymeleaf依赖。

* 2，使用相对路径引入静态文件时，springboot没有对应的映射，添加映射后没有成功？
解决方案是添加类StaticPathConfig继承WebMvcConfigurationSupport，然后重写addResourceHandlers ，然后用super.addResourceHandlers方法添加资源句柄。