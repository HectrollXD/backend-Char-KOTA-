package mx.com.hexlink.charkota.api.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class OpenapiConfiguration {
	@Value("${openapi.title}")
	String title;

	@Value("${openapi.description}")
	String description;

	@Value("${openapi.version}")
	String version;



	@Bean
	public OpenAPI openAPI(){
		return new OpenAPI()
			.info(new Info()
				.title(title)
				.description(description)
				.version(version)
			);
	}
}
