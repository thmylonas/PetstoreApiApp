package com.thomasmylonas.petstore_api_web_app.config;

import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;

class DataAccessConfigTest {

    private DataAccessConfig dataAccessConfig;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        dataAccessConfig = new DataAccessConfig();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        dataAccessConfig = null;
    }

    @org.junit.jupiter.api.Test
    void dataSource() {
        DataSource actual = dataAccessConfig.dataSource();
        Assertions.assertNotNull(actual);
//        Assertions.assertNull(actual);
    }
}

/*
javax.naming.NoInitialContextException: Need to specify class name in environment or system property, or as an applet parameter, or in an application resource file:  java.naming.factory.initial
*/