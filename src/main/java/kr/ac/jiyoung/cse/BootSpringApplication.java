package kr.ac.jiyoung.cse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class BootSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootSpringApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
		return filter;
	}

}
