package sparta.spring.springadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // @WebServlet 어노테이션이 동작하게 함
@SpringBootApplication
public class SpringAdvancedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAdvancedApplication.class, args);
    }
}
