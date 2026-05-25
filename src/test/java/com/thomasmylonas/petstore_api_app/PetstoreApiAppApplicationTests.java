package com.thomasmylonas.petstore_api_app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
class PetstoreApiAppApplicationTests {

    @Test
    void contextLoads() {
    }
}
