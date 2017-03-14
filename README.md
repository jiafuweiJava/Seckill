# Seckill
redis 实现商品秒杀
-------------------
在这个项目里主要实现了以下技术：
* spring-session-data-redis 使用spring session + redis实现session的共享
* redis 存储商品数量进行商品的秒杀 获取redis的连接 使用redis存储序列化对象
* 持久层使用的mybatis
-------------------

在redis中设置一个键为mykey，值为1000的变量，通俗点讲，watch命令就是标记一个键，如果标记了一个键，在提交事务前如果该键被别人修改过，那事务就会失败，这种情况通常可以在程序中重新再尝试一次。参考Main.java

###博客地址 ：http://www.cnblogs.com/jiafuwei/p/6425604.html
