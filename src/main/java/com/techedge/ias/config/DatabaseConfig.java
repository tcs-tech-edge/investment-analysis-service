package com.techedge.ias.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.techedge.ias.data.repository")
public class DatabaseConfig {

    @Value("${database.driver}")
    private String databaseDriver;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${database.platform}")
    private String databasePlatform;

    @Value("${database.show.sql:false}")
    private boolean showSql;

    @Value("${package.to.scan:com.techedge.ias.data.entity}")
    private String packageToScan;

    @Value("${database.generate.ddl:false}")
    private boolean generateDDL;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setPackagesToScan(packageToScan);
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setJpaProperties(new Properties());
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(showSql);
        jpaVendorAdapter.setDatabasePlatform(databasePlatform);
        jpaVendorAdapter.setGenerateDdl(generateDDL);
        return jpaVendorAdapter;
    }
}
