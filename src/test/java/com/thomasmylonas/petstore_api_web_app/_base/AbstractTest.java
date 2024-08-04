package com.thomasmylonas.petstore_api_web_app._base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTest {

    protected final static Logger LOGGER = LoggerFactory.getLogger(Class.class.getSimpleName());

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}
