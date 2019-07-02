package com.ai.sin.rm.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Integration test for testing controller
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITRelationshipManagerController {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetRecommendationsByClientId() {
        String expectedJson = "{\"id\":1,\"name\":\"Allen\",\"risk\":3,\"recommendations\":[{\"id\":1,\"name\":\"SG BONDS\",\"risk\":2,\"investment\":2},{\"id\":2,\"name\":\"CHINA BONDS\",\"risk\":3,\"investment\":4}]}";
        String body = this.testRestTemplate.getForEntity("/clients/1/recommendations", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

    @Test
    public void testGetRecommendations() {
        String expectedJson = "[{\"id\":3,\"name\":\"HUA\",\"risk\":5,\"recommendations\":[{\"id\":1,\"name\":\"SG BONDS\",\"risk\":2,\"investment\":2},{\"id\":2,\"name\":\"CHINA BONDS\",\"risk\":3,\"investment\":4},{\"id\":3,\"name\":\"THAILAND BONDS\",\"risk\":5,\"investment\":4}]},{\"id\":1,\"name\":\"Allen\",\"risk\":3,\"recommendations\":[{\"id\":1,\"name\":\"SG BONDS\",\"risk\":2,\"investment\":2},{\"id\":2,\"name\":\"CHINA BONDS\",\"risk\":3,\"investment\":4}]},{\"id\":2,\"name\":\"AI\",\"risk\":4,\"recommendations\":[{\"id\":1,\"name\":\"SG BONDS\",\"risk\":2,\"investment\":2},{\"id\":2,\"name\":\"CHINA BONDS\",\"risk\":3,\"investment\":4}]}]";
        String body = this.testRestTemplate.getForEntity("/clients/recommendations", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

    @Test
    public void testGetRecommendationsWithError() {
        String expectedJson = "{\"header\":{\"success\":false,\"authToken\":\"\"},\"payload\":null,\"error\":{\"errorCode\":\"500\",\"errorType\":\"BUSINESS_EXCEPTION\",\"errorMessage\":\"Client id 10 does not exist.\"}}";
        String body = this.testRestTemplate.getForEntity("/clients/10/recommendations", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

}
