package mx.com.hexlink.charkota.data;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@Configuration
@AutoConfiguration
@EnableJpaRepositories
@EntityScan("mx.com.hexlink.charkota")
@ComponentScan("mx.com.hexlink.charkota")
public class DataAutoconfiguration {
}
