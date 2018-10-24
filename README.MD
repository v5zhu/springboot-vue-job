### 项目介绍
quartz集群实现的定时任务（可由具体业务替代），任务执行监控日志由rocketmq集群来异步解耦，
配合vue实现的前端可控定时任务以及日志监控查询，同时兼并druid监控；任务执行监控日志主键由
分布式全局唯一id策略实现，id自增序列缓存到redis集群
### 技术架构
quartz集群
rocketmq集群
redis集群
spring boot 框架
vue前端

### 里程碑

#### 1.2018年10月24日
完成quartz集群和redis集群的搭建和运行访问

