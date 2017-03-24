package demo.routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PcfApp {

	public static void main(String[] args) {
		SpringApplication.run(PcfApp.class, args);
	}
}
