package com.thomasmylonas.petstore_api_web_app.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages = {"com.thomasmylonas.petstore_api_web_app"})
public class DataAccessConfig {

    @Bean(name = "entityManagerFactoryBean")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//        emfb.setPersistenceUnitName("Petstore_PU");
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
//        DataSource ds = null;
//        try {
//            ds = (DataSource)(new InitialContext()).lookup("java:comp/env/jdbc/petstoredb");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//        emfb.setDataSource(ds);
        emfb.setPackagesToScan("com.thomasmylonas.petstore_api_web_app");
        return emfb;
    }

    @Bean(name = "dataSource")
    DataSource dataSource() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/petstoredb");
        jndiObjectFactoryBean.setResourceRef(false);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
//        jndiObjectFactoryBean.setLookupOnStartup(false);
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabase("ORACLE");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.Oracle12cDialect");
        return adapter;
    }
}
