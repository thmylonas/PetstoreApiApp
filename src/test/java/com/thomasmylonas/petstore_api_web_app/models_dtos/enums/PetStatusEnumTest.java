package com.thomasmylonas.petstore_api_web_app.models_dtos.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class PetStatusEnumTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testIsPetStatus_available() {
        boolean isPetStatus = PetStatusEnum.isPetStatus("available");
        log.info(isPetStatus ? "'available' is 'PetStatus'" : "'available' is not 'PetStatus'");
        assertTrue(isPetStatus);
    }

    @Test
    void testIsPetStatus_SOLD() {
        boolean isPetStatus = PetStatusEnum.isPetStatus("SOLD");
        log.info(isPetStatus ? "'SOLD' is 'PetStatus'" : "'SOLD' is not 'PetStatus'");
        assertTrue(isPetStatus);
    }

    @Test
    void testIsPetStatus_hello() {
        boolean isPetStatus = PetStatusEnum.isPetStatus("hello");
        log.info(isPetStatus ? "'hello' is 'PetStatus'" : "'hello' is not 'PetStatus'");
        assertFalse(isPetStatus);
    }
}
