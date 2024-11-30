package com.petProject.OrderService;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = MOCK)
public class AbstractContainer {
}
