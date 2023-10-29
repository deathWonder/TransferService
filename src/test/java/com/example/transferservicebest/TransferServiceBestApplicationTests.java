package com.example.transferservicebest;

import com.example.transferservicebest.model.Amount;
import com.example.transferservicebest.model.Result;
import com.example.transferservicebest.model.TransferInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferServiceBestApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> myApp = new GenericContainer<>("myapp")
            .withExposedPorts(8080);

    @Test
    public void testTestRestTemplate() {
        Assertions.assertNotNull(restTemplate);
    }

    @Test
    public void testContainer() {
        Assertions.assertNotNull(myApp);
    }

    @Test
    public void contextLoads() {

        Integer appPort = myApp.getMappedPort(8080);

        Amount amount = new Amount(1000, "RUR");
        TransferInfo request = new TransferInfo("2234567890123456",
                "11/26",
                "353",
                "2234567890123457",
                amount);

        ResponseEntity<Result> response = restTemplate.postForEntity(
                "http://localhost:" + appPort + "/transfer",
                request,
                Result.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
