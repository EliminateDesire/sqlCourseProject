# sqlCourseProject
#### 一、设计模式

+ 采用model1模式实现
+ 通过servlet类接受前端提交过来的信息，交给业务逻辑（Dao）层来处理，将结果请求转发给前端jsp页面
+ 业务逻辑层使用jdbc连接数据库，通过工具类DButils来实现



#### 二、目录

```
│  list.txt
│  
├─bean
│      Course.java
│      courseGrade.java
│      User.java
│      
├─Dao
│      operateDao.java
│      queryDao.java
│      userDao.java
│      
├─service
│      loginServlet.java
│      openServlet.java
│      quitServlet.java
│      selectServlet.java
│      submitServlet.java
│      userExitServlet.java
│      
└─utils
        DBUtils.java
```

​       其中bean下为所需要的类，Dao下为业务逻辑处理，service下用来接受表单提交和请求转发，utils用来连接数据库。



#### 三、功能

+ 学生端：登录，选课，退课，查看我的课程，查询成绩

+ 教师端：登录，开课，查看我的课程，查看班上学生的成绩，上传成绩



#### 四、不足之处

+ 因为~~学不动~~不会js和ajax，所以所有的页面都使用了jsp来实现，有很大的冗余成分（期待会js的组员来改进）
+ Dao层中的方法均为静态方法，可能会造成**线程不安全**~~（反正也就在本地跑跑）~~

+ 逻辑判断还缺失了~~很多~~（一些），比如已经打过成绩的课程不能再退课等等（即所有操作的必须是合法的）

项目地址 : https://github.com/EliminateDesire/sqlCourseProject

