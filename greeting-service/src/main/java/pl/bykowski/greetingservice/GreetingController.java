package pl.bykowski.greetingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/greeting")
    public String greet() {
        return "Hello from instance: " + instanceId;
    }
}