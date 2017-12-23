package cq_spa_demo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cq_spa_demo.controller.QuestionController;
import cq_spa_demo.factory.DefaultQuestionFactory;
import cq_spa_demo.factory.FileSelectableLoader;
import cq_spa_demo.factory.FileTipLoader;
import cq_spa_demo.factory.FileTranslatableLoader;
import cq_spa_demo.factory.IQuestionFactory;
import cq_spa_demo.model.Selectable;
import cq_spa_demo.model.Tipable;
import cq_spa_demo.model.Translatable;
import cq_spa_demo.questions.LineWriter;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class Application {
	private static final String DEFAULT_ENCODING = "UTF-8";

	private static final String SELECTABLES_FILE = "selectables.txt";

	private static final String TEMP_SELECTABLES_FILE = "temp_selectables.txt";

	private static final String TIPS_FILE = "tips.txt";

	private static final String WAITING_FOR_TRANSLATE_FILE = "waiting_for_translate.txt";

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(final String[] args) {
		final SpringApplication application = new SpringApplication(Application.class);
		application.setWebEnvironment(true);
		application.run(args);
	}

	private final Builder builder;

	public Application() {
		try {
			new File(SELECTABLES_FILE).createNewFile();
			new File(TEMP_SELECTABLES_FILE).createNewFile();
			new File(TIPS_FILE).createNewFile();
			new File(WAITING_FOR_TRANSLATE_FILE).createNewFile();
		} catch (final IOException e) {
			LOG.error("error when creating needed files: ", e);
		}
		final Map<String, Selectable> selectables = new FileSelectableLoader(SELECTABLES_FILE, DEFAULT_ENCODING).load();
		final Map<String, Tipable> tips = new FileTipLoader(TIPS_FILE, DEFAULT_ENCODING).load();
		LOG.info("loaded {} selectables, {} tips", selectables.size(), tips.size());
		final Map<String, Translatable> translatables = new FileTranslatableLoader(WAITING_FOR_TRANSLATE_FILE,
				DEFAULT_ENCODING).load();
		LOG.info("loaded {} translatables", translatables.size());
		final LineWriter selectablesWriter = new LineWriter(SELECTABLES_FILE, DEFAULT_ENCODING);
		final LineWriter translatablesWriter = new LineWriter(WAITING_FOR_TRANSLATE_FILE, DEFAULT_ENCODING);
		final LineWriter translatedWriter = new LineWriter(TEMP_SELECTABLES_FILE, DEFAULT_ENCODING);
		final IQuestionFactory rawQuestionFactory = new DefaultQuestionFactory(tips, selectables, translatables);
		final CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		final FilterRegistrationBean characterEncodingFilterRegistrationBean = new FilterRegistrationBean();
		characterEncodingFilterRegistrationBean.setFilter(filter);
		characterEncodingFilterRegistrationBean.addUrlPatterns("/*");
		//@formatter:off
		final WebMvcConfigurerAdapter webMvcAutoConfigurationAdapter = new WebMvcAutoConfigurationAdapter() {
			@Override
			public void addResourceHandlers(final ResourceHandlerRegistry registry) {
				registry
					.addResourceHandler("/**")
					.addResourceLocations(new String[] { "classpath:/dist/" });
			}

			@Override
			public void addViewControllers(final ViewControllerRegistry registry) {
				registry
					.addViewController("/")
					.setViewName("forward:/index.html");
			}

			@Override
			public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
				final MediaType defaultContentType = new MediaType("application", "json", Charset.forName(DEFAULT_ENCODING));
				configurer.defaultContentType(defaultContentType);
			}
		};
		final WebSecurityConfigurerAdapter webSecurityConfigurerAdapter = new WebSecurityConfigurerAdapter() {
			@Override
			protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
				auth
					.inMemoryAuthentication()
					.withUser("user")
					.password("resu")
					.roles("USER");
			}

			@Override
			protected void configure(final HttpSecurity http) throws Exception {
				http
					.csrf().disable()
					.antMatcher("/**")
					.authorizeRequests().anyRequest().hasAnyRole("USER")
					.and()
					.httpBasic();
			}
		};
		final QuestionController.Builder questionsControllerBuilder = new QuestionController.Builder()
				.setFactory(rawQuestionFactory)
				.setSelectablesWriter(selectablesWriter)
				.setTranslatablesWriter(translatablesWriter)
				.setTempSelectableWriter(translatedWriter)
				.setTranslatables(translatables)
				.setSelectables(selectables);
		this.builder = new Builder()
				.setQuestionsControllerBuilder(questionsControllerBuilder)
				.setCharacterEncodingFilterRegistrationBean(characterEncodingFilterRegistrationBean)
				.setWebMvcConfigurerAdapter(webMvcAutoConfigurationAdapter)
				.setWebSecurityConfigurerAdapter(webSecurityConfigurerAdapter);
		//@formatter:on
	}

	@Bean
	public FilterRegistrationBean getCharacterEncodingFilter() {
		return this.builder.characterEncodingFilterRegistrationBean;
	}

	@Bean
	public QuestionController.Builder getQuestionsControllerBuilder() {
		return this.builder.questionsControllerBuilder;
	}

	@Bean
	public WebMvcConfigurerAdapter getWebMvcAutoConfigurationAdapter() {
		return this.builder.webMvcAutoConfigurationAdapter;
	}

	@Bean
	public WebSecurityConfigurerAdapter getWebSecurityConfigurerAdapter() {
		return this.builder.webSecurityConfigurerAdapter;
	}

	private static final class Builder {
		QuestionController.Builder questionsControllerBuilder;

		FilterRegistrationBean characterEncodingFilterRegistrationBean;

		WebMvcConfigurerAdapter webMvcAutoConfigurationAdapter;

		WebSecurityConfigurerAdapter webSecurityConfigurerAdapter;

		public Builder setCharacterEncodingFilterRegistrationBean(
				final FilterRegistrationBean characterEncodingFilterRegistrationBean) {
			this.characterEncodingFilterRegistrationBean = characterEncodingFilterRegistrationBean;
			return this;
		}

		public Builder setQuestionsControllerBuilder(final QuestionController.Builder questionsControllerBuilder) {
			this.questionsControllerBuilder = questionsControllerBuilder;
			return this;
		}

		public Builder setWebMvcConfigurerAdapter(final WebMvcConfigurerAdapter webMvcAutoConfigurationAdapter) {
			this.webMvcAutoConfigurationAdapter = webMvcAutoConfigurationAdapter;
			return this;
		}

		public Builder setWebSecurityConfigurerAdapter(
				final WebSecurityConfigurerAdapter webSecurityConfigurerAdapter) {
			this.webSecurityConfigurerAdapter = webSecurityConfigurerAdapter;
			return this;
		}
	}
}
