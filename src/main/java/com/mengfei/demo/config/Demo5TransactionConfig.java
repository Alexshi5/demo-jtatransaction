package com.mengfei.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        //basePackageClasses = 设置Repository所在的类
        basePackages = "com.mengfei.demo.repository.demo5", //设置Repository所在的包
        entityManagerFactoryRef = "demo5EntityManagerFactory", //设置实体管理工厂
        transactionManagerRef = "demo5TransactionManager" //设置事务管理器
)
public class Demo5TransactionConfig {

    /**
     * 统一配置多数据源之后，自动注入数据源
     */
    @Autowired
    @Qualifier("demo5DataSource")
    private DataSource demo5DataSource;

    /**
     * 自动注入jpa配置
     */
    @Resource
    private JpaProperties jpaProperties;


    /**
     * 将数据源、连接池、以及其他配置策略进行封装返回给事务管理器
     * @param builder
     * @return
     */
    @Primary //自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
    @Bean(name = "demo5EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDemo(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(demo5DataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.mengfei.demo.pojo.demo5") //设置实体类所在位置：类或包
                .persistenceUnit("demo5PersistenceUnit") //持久化单元名称
                .build();
    }

    /**
     * 返回数据源的事务管理器
     * @param builder
     * @return
     */
    @Primary
    @Bean(name = "demo5TransactionManager")
    public PlatformTransactionManager transactionManagerDemo(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactoryDemo(builder).getObject());
    }
}
