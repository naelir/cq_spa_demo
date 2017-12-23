package cq_spa_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class Application {
	public static void main(final String[] args) {
		final SpringApplication application = new SpringApplication(Application.class);
		application.setWebEnvironment(true);
		application.run(args);
	}

	@Bean
	public WebMvcAutoConfigurationAdapter getWebMvcAutoConfigurationAdapter() {
		return new WebMvcAutoConfigurationAdapter() {
			@Override
			public void addResourceHandlers(final ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/**").addResourceLocations(new String[] { "classpath:/dist/" });
			}

			@Override
			public void addViewControllers(final ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("forward:/index.html");
			}
		};
	}
}
