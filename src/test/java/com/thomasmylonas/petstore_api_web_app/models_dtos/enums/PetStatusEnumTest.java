package com.thomasmylonas.petstore_api_web_app.models_dtos.enums;

import com.thomasmylonas.petstore_api_web_app._base.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetStatusEnumTest extends AbstractTest {

    @Test
    void testIsPetStatus_available() {
        boolean isPetStatus = PetStatusEnum.isPetStatus("available");
        LOGGER.info(isPetStatus ? "'available' is 'PetStatus'" : "'available' is not 'PetStatus'");
        assertTrue(isPetStatus);
    }

    @Test
    void testIsPetStatus_SOLD() {
        boolean isPetStatus = PetStatusEnum.isPetStatus("SOLD");
        LOGGER.info(isPetStatus ? "'SOLD' is 'PetStatus'" : "'SOLD' is not 'PetStatus'");
        assertTrue(isPetStatus);
    }

    @Test
    void testIsPetStatus_hello() {
        boolean isPetStatus = PetStatusEnum.isPetStatus("hello");
        LOGGER.info(isPetStatus ? "'hello' is 'PetStatus'" : "'hello' is not 'PetStatus'");
        assertFalse(isPetStatus);
    }
}
