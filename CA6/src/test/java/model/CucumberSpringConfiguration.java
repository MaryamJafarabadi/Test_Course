package model;

import application.BalootApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = BalootApplication.class)
public class CucumberSpringConfiguration {

}