## 打包
    通过命令`maven claen install`进行打包，将打包好的jar包上传到对应的服务器

## 启动
- 正式
nohup java -jar dicing.jar --spring.profiles.active=prod

- 测试
nohup java -jar dicing.jar --spring.profiles.active=test
