package com.wojewodka;

import com.wojewodka.example.components.ExampleLuckeneRepository;
import com.wojewodka.luckene.LuckeneConfigurator;
import com.wojewodka.luckene.annotation.LuckeneConfiguration;
import com.wojewodka.luckene.config.LuckeneConfigurer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@LuckeneConfiguration(
	repositories = {
		ExampleLuckeneRepository.class
	}
)
@PropertySource("classpath:luckene.properties")
@Service
@RequiredArgsConstructor
public class ExampleLuckeneConfiguration implements LuckeneConfigurer {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan(ExampleLuckeneConfiguration.class.getPackage().getName());
		ctx.refresh();
		ctx.getBean(LuckeneConfigurator.class);
		ExampleLuckeneRepository bean = ctx.getBean(ExampleLuckeneRepository.class);
		bean.getWriter().write(null);
	}

}
