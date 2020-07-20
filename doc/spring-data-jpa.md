# spring-data-jpa使用总结

## 1.pom.xml配置

```
<dependency>    <groupId>org.springframework.boot</groupId>    <artifactId>spring-boot-starter-data-jpa</artifactId></dependency>
```

## 2.application.yml配置

```
spring:
  application:
    name: test
  datasource:
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: root
  jpa:
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
```

## 3.实体定义

```
@Entity
@Table(name="tb_user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "IdUtil")
    @GenericGenerator(name = "IdUtil", strategy = "com.jia.test.jpa.beans.common.IdUtil")
    private String id;
    private String username;
    private String email;
    private String phone;
    private Date birthday;
    private Date create_time;
    private Date update_time;
}
```

### 3.1 表主键生成策略自定义

```
public class IdUtil implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return getId() + "";
    }

    private Serializable getId() {
        return 1;
    }

}
```



## 4.mapper引用

```
@Repository
public interface TestUserMapper extends JpaRepository<User,Long>, JpaSpecificationExecutor<Long> {
}
```

## 5.使用方式

```
@SpringBootTest(classes = TestJpaApplication.class)
@RunWith(SpringRunner.class)
public class Test1111 {
    @Autowired
    private TestUserMapper testUserMapper;

    @Test
    public void test() {
        User user = new User();
        user.setId("1");
        user.setUsername("张三");
        user.setBirthday(new Date());
        user.setEmail("");
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        User save = testUserMapper.save(user);
        if(save == null) {
            System.out.println("error");
        }
        System.out.println(save);
    }
}
```

