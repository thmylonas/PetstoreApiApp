package com.thomasmylonas.petstore_api_web_app.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages = {"com.thomasmylonas.petstore_api_web_app"})
public class DataAccessConfig {

    private static final Logger LOGGER = LogManager.getLogger(DataAccessConfig.class.getName());

    @Bean(name = "entityManagerFactoryBean")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);

        emfb.setPackagesToScan("com.thomasmylonas.petstore_api_web_app");
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.ORACLE);
        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    DataSource dataSource() {

        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/oracledb");
        jndiObjectFactoryBean.setResourceRef(true); // Default value: false
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        // jndiObjectFactoryBean.setLookupOnStartup(false); // Default value: true
        try {
            jndiObjectFactoryBean.afterPropertiesSet();
        } catch (NamingException e) {
            LOGGER.error("NamingException thrown while jndiObjectFactoryBean.afterPropertiesSet");
            e.printStackTrace();
        }
        return (DataSource) jndiObjectFactoryBean.getObject();
    }
}
