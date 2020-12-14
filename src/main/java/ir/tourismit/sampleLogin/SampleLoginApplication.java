package ir.tourismit.sampleLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication(exclude = JmxAutoConfiguration.class)
public class SampleLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleLoginApplication.class, args);
	}

}
