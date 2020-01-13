**项目说明** 
- charityShop公益项目
- 支持MySQL数据库
- 访问地址：http://localhost:8080/charityShop/swagger-ui.html 
- api接口代码，在org.greencode.modules.app这个目录下写，已经写了一个分店管理的例子

<br>
 


**项目结构** 
```
charityShop
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─app API接口模块(APP调用)
│  └─sys 登录权限模块
│ 
├─charityShopApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源
│  └─templates 后台管理系统页面

```
<br> 

**技术选型：** 
- 核心框架：Spring Boot 2.1
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.0
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x 
<br> 


 **后端部署**
- 通过git下载源码
- idea、eclipse需安装lombok插件，不然会提示找不到entity的get set方法
- 创建数据库charityShop，数据库编码为UTF-8
- 执行db/bbalt.sql文件，初始化数据
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行charityShopApplication.java，则可启动项目
- Swagger文档路径：http://localhost:8080/charityShop/swagger/index.html
- Swagger注解路径：http://localhost:8080/charityShop/swagger-ui.html ,项目访问此路径
<br> 

 **后台管理系统部署**
- 执行db/bbalt.sql文件，初始化数据，里面包含后台管理员信息、页面目录等
- 修改application-dev.yml，更新MySQL账号和密码
- Eclipse、IDEA运行charityShopApplication.java，则可启动项目
- 后台管理系统访问路径：hhttp://localhost:8080/charityShop/
- 登录账号密码：admin/admin
- 后台管理系统的页面统一目录：\resources\templates\modules\app\
<br> 


 **项目演示**
- 演示地址：http://localhost:8080/charityShop/swagger-ui.html 
- 账号密码：15612345678/admin
<br> 
