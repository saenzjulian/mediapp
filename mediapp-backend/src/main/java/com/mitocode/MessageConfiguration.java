package com.mitocode;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MessageConfiguration {
	
	// Carga los properties
			@Bean
			public MessageSource messageSource() {
				ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
				messageSource.setBasename("classpath:messages");
				messageSource.setDefaultEncoding("UTF-8");
				return messageSource;
			}

			/*
			 * Establece por default un locale
			 * ROOT es el archivo que no tiene _ en el nombre messages.properties 
			 */
			@Bean
			public LocaleResolver localeResolver() {
				SessionLocaleResolver slr = new SessionLocaleResolver();
				//slr.setDefaultLocale(Locale.ENGLISH);
				slr.setDefaultLocale(Locale.ROOT);
				return slr;
			}

			// Para que Spring pueda reconocer los keywords que puse en los archivos .properties
			@Bean
			public LocalValidatorFactoryBean getValidator() {
				LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
				bean.setValidationMessageSource(messageSource());
				return bean;
			}
	
}
