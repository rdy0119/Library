项目设计：
	1. 把数据保存到文件中(Properties-->key/value),数据就是文件。
	   1.1 分表：其实就是分文件夹,不同的文件夹中存放不同的文件及内容
	   1.2 学生和书包的关系:one-to-many
	
	2. 使用对应的service
	
	3. 控制层
	
	4. 页面
	   4.1 登录功能
	   4.2 主页面:tree树形
	   
----------------------------------------------------	   
功能设计：
  1. 添加
  
  2. 修改
  
  3. 删除
  
  4. 显示:
----------------------------------------------------
包和对应的类:
  1. vo表示javaBean文件：就是数据的载体
     1.1 学生:Student.java
	      学号id、姓名username、性别sex、年龄age、书包List
	 1.2 图书:
	      编号id、图书名称bookname、作者auth、单价price

   2. dao: 忽略
   
   3. service
      3.1 StudentService
	  
	  3.2 BookService

   4. action
      4.1 StudentAction:依赖于对应的StudentService

      4.2 BookAction：依赖于对应的BookService  	

   5. page：
      5.1 LonginPage.java
      5.2 MainPage.java
      5.3 BookPage.java
      5.4 StudentPage.java 	  

   6. 工具类
      6.1 返回id的值
	  6.2 获取当前的日期	   
	   
	   
	   
	   
	   
	   