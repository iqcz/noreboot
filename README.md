# noreboot
Based on Nashorn engine to run javascript, and no need to restart application.

About more infomation, please refer to http://openjdk.java.net/projects/nashorn/.

Also you can use javascript to run Java code in Nashorn running environment.

Here are some details.

[Java Nashorn--Part 1](http://www.cnblogs.com/IcanFixIt/p/6387759.html)

[Java Nashorn--Part 2](http://www.cnblogs.com/IcanFixIt/p/6390130.html)

[Java Nashorn--Part 3](http://www.cnblogs.com/IcanFixIt/p/6391905.html)

[Java Nashorn--Part 4](http://www.cnblogs.com/IcanFixIt/p/6403110.html)

[Java Nashorn--Part 5](http://www.cnblogs.com/IcanFixIt/p/6407008.html)

[Java Nashorn--Part 6](http://www.cnblogs.com/IcanFixIt/p/6408194.html)


This project uses the feature that Java call javascript function by Nashorn enginee,
when changes the function in javascript, do not need to restart the java application, 
or Java Web Server such as Tomcat, Jetty.

This project is not demonstrated in Java Web environment. just use Scanner class to input data, 
keep Java application running.

Here some usecase when use this feature.
* hardcode some info.
* append sql conditon statement.
* set date for some event.
* read file content.
* Java reflection.
* Formula calculation.

In all, now just use the return string info in function in javascript file, do kind of things.
For more use case, I have been keeping investigating, and update this document later.

The javascript file can be saved in the project, but when deployment set this file in a separate file path. 
when every time changes, not forget to modify the file in the project and commit to github or gitlab.

Use this feature in suitable scenario.

> Note
>
> DO NOT use below classpath file as parmeter for getFile method in Spring to load js.
>
> `File cfgFile = ResourceUtils.getFile("classpath:config/model.js");`
>
> After deployement, the file is copied to the dir in the server or the dir is configured by server.
>
> so just put it in a normal dir in a separate file path you can remember and records this in wiki for your project.
> 

