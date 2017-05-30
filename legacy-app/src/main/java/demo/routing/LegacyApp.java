package demo.routing;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePrometheusEndpoint
public class LegacyApp {

	public static void main(String[] args) {
		SpringApplication.run(LegacyApp.class, args);
	}
}
