#设置镜像基础
FROM 10.3.192.39:8777/projectimages/jre:1.8
#设置镜像对外暴露端口
EXPOSE 8080
#将当前 target 目录下的 jar 放置在根目录下，命名为 xxx.jar，推荐使用绝对路径。
ADD target/multi-source.jar /multi-source.jar
#执行启动命令
ENTRYPOINT ["java", "-jar","/multi-source.jar"]

