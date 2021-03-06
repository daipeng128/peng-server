# ITRAT

开发语言：JAVA

数据库：MYSQL

JAVA开发框架：Spring MVC+Spring+Mybatis

前台前端开发框架：ZUI+JQuery+Bootstrap

前台模板引擎：Freemarker

## 功能模块
- 私信功能
1. 界面模仿PC版微信
2. 可以查看私聊过的联系人
3. 聊天界面自动刷新

- 个人主页
1. 关注会员
2. 私信会员
3. 查看动态
4. 查看粉丝、关注、微博、文章、帖子、群组
5. 财务明细
6. 积分明细
7. 卡密充值
8. VIP会员等级

- 微博模块
1. 支持图片类型的微博
2. 多图画廊展示
3. 支持添加Emoji标签
4. 点赞功能
5. 微博话题

- 群组模块
1. 可以关注群组
2. 支持上传群组logo
3. 支持发帖审核开关
4. 授权管理员
5. 帖子喜欢功能
6. 帖子加精、置顶
7. 付费加入群组

- 文章模块
1. 文章喜欢功能
2. 文章投稿功能开关
3. 文章审核功能开关
4. 文章评论

- 动态模块
1. 洞悉一切

## 环境要求

- JDK7或更高版本
- Tomcat7.0或更高版本
- MySQL5.1或更高版本

## 部署说明

1. 创建数据库。如使用MySQL，字符集选择为`utf8`或者`utf8mb4`（支持更多特殊字符，推荐）。
2. 执行数据库脚本。数据库脚本在`/src/main/webapp/database`目录下。
3. 在eclipse中导入maven项目。点击eclipse菜单`File` - `Import`，选择`Maven` - `Existing Maven Projects`。
4. 设置项目编码为utf-8，选择jdk1.8版本或以上，不要选择jre。
5. 修改数据库连接。打开`/src/main/resources/itrat.propertis`文件，根据实际情况修改`jdbc.url`、`jdbc.user`、`jdbc.password`的值，修改后台路径：`managePath`，如：`managePath=manage`

   
   执行成功后，jeesns-core-1.4.jar会自动添加到本地maven仓库中。
7. 编译项目。在eclipse中，右键点击项目名，选择`Run as` - `Maven build...`，`Goals`填入`clean package`，然后点击`Run`，第一次运行需要下载jar包，请耐心等待。
8. 部署项目。将项目部署到Tomcat7或以上版本，启动Tomcat。
9. 访问系统。前台地址：[http://localhost:8080/](http://localhost:8080/)；用户名：admin，密码：jeesns，登录成功之后，在右上角展开有个'管理'，点击即可进入后台管理。

