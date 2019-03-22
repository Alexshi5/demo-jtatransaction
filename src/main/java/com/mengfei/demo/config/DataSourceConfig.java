package com.mengfei.demo.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Value("${druid.initialSize}")
    private Integer initialSize;

    @Value("${druid.minIdle}")
    private Integer minIdle;

    @Value("${druid.maxActive}")
    private Integer maxActive;

    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    @Primary
    @Bean(name = "demo5DataSource")
    @Qualifier("demo5DataSource")
    public DataSource demo5DataSource(){
        return getDruidXADataSource(
                this.env.getProperty("datasource.demo5.username"),
                this.env.getProperty("datasource.demo5.password"),
                this.env.getProperty("datasource.demo5.url"),
                this.env.getProperty("datasource.demo5.datasourceName"));
    }

    @Bean(name = "demo6DataSource")
    @Qualifier("demo6DataSource")
    public DataSource demo6DataSource(){
        return getDruidXADataSource(
                this.env.getProperty("datasource.demo6.username"),
                this.env.getProperty("datasource.demo6.password"),
                this.env.getProperty("datasource.demo6.url"),
                this.env.getProperty("datasource.demo6.datasourceName"));
    }

    /**
     * 获取Atomikos管理的Druid连接池
     * @param username
     * @param password
     * @param url
     * @param datasourceName
     * @return
     */
    private DataSource getDruidXADataSource(String username, String password, String url, String datasourceName){
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(url);
        druidXADataSource.setUsername(username);
        druidXADataSource.setPassword(password);
        druidXADataSource.setMinIdle(minIdle);
        druidXADataSource.setMaxActive(maxActive);
        druidXADataSource.setInitialSize(initialSize);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setUniqueResourceName(datasourceName);
        xaDataSource.setXaDataSource(druidXADataSource);

        return xaDataSource;
    }
}
