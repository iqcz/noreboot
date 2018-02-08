# noreboot
Based on Nashorn engine to run javascript, and no need to restart application.

About more infomation, please refer to http://openjdk.java.net/projects/nashorn/.

This project uses the feature that Java call javascript function by Nashorn enginee，
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

> Note
>
> Not use Spring to load js file.
