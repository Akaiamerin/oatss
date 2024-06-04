<h1 align="center">网上机票销售系统</h1>

SSM + Thymeleaf

### 初始化操作
1. 修改 [MyBatisConfig.java](./src/main/java/com/oatss/config/MyBatisConfig.java) 文件中的 MySQL 账号和密码。

2. 执行 [oatss.sql](./src/main/resources/sql/oatss.sql) 文件中的 SQL 代码。

获取管理员权限：注册后修改数据库中 `user` 表 `role` 字段为 `admin`。