package com.wojewodka.luckene;

import com.wojewodka.luckene.annotation.LuckeneConfiguration;
import com.wojewodka.luckene.commons.LuckeneCommons;
import com.wojewodka.luckene.config.LuckeneConfigurer;
import com.wojewodka.luckene.index.LuckeneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class LuckeneConfigurator {

	private final LuckeneConfigurer configurer;
	private final ApplicationContext applicationContext;

	private File rootDirectory;
	@Value("${luckene.root.location}")
	private String rootLocation;
	private List<LuckeneRepository<?>> repositories;

	@PostConstruct
	public void setup() {
		LuckeneConfiguration configuration = getConfiguration();
		this.rootDirectory = createRootDirectory(configuration);
		this.repositories = getRepositories(configuration);
	}

	private LuckeneConfiguration getConfiguration() {
		Class<? extends LuckeneConfigurer> clazz = configurer.getClass();
		return clazz.getAnnotation(LuckeneConfiguration.class);
	}

	private File createRootDirectory(LuckeneConfiguration luckeneConfiguration) {
		return LuckeneCommons.createRootDirectory(rootLocation, false);
	}

	private List<LuckeneRepository<?>> getRepositories(LuckeneConfiguration luckeneConfiguration) {
		List<LuckeneRepository<?>> result = new ArrayList<>();
		stream(luckeneConfiguration.repositories())
			.map(applicationContext::getBean)
			.forEach(result::add);
		return result;
	}

}
