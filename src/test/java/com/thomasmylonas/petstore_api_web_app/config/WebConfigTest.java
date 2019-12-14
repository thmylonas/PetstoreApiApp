package com.thomasmylonas.petstore_api_web_app.config;


import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;

class WebConfigTest {

    private WebConfig webConfig;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        webConfig = new WebConfig();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void dataSource() {
        DataSource actual = webConfig.dataSource();
//        Assert.assertNull(actual);
        Assertions.assertNotNull(actual);
    }
}