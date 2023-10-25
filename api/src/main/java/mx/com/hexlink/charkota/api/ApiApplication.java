package mx.com.hexlink.charkota.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EntityScan("mx.com.hexlink.charkota")
@ComponentScan("mx.com.hexlink.charkota")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
