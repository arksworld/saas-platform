package org.arksworld.saasPlatform.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.TimeZone;

@SpringBootApplication(
        scanBasePackages = {"org.arksworld.saasPlatform.tenant",
                "org.arksworld.saasPlatform.order"
        }
)
public class OrderServiceApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}