本地数据库密码一致性的解决方案：

1. 命令行或者可视化工具，使用root身份登录到mysql

2. 执行以下两条sql语句
```sql
create user 'sa'@'localhost' identified by '123';

grant all on *.* to 'sa'@'localhost' with grant option;
```
