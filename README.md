本地数据库密码一致性的解决方案：

1. 命令行或者可视化工具，使用root身份登录到mysql

2. 执行以下两条sql语句
```sql
create user 'sa'@'localhost' identified by '123';

grant all on *.* to 'sa'@'localhost' with grant option;
```

3. 將 com.csc510.smartweather.controller.IndexController 中的以下 API 字串替換成 API Key
```java
    private String GOOGLE_API_KEY = "REPLACE_THIS_WITH_KEY";
```