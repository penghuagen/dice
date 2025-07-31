## 打包
    通过命令`maven claen install`进行打包，将打包好的jar包上传到对应的服务器

## 启动
- 正式
nohup java -jar dicing.jar --spring.profiles.active=prod\

nohup java -jar dicing.jar --spring.profiles.active=prod > /dev/null 2>&1 &

- 测试
nohup java -jar dicing.jar --spring.profiles.active=test

## 1、连接服务47.98.36.225
## 2、执行初始化脚本(注意博饼时间，真实博饼时间以数据库里的为准)
## 3、重启服务