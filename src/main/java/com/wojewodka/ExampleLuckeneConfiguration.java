package com.wojewodka;

import com.wojewodka.example.ExampleLuckeneRepository;
import com.wojewodka.example.ExamplePojo;
import com.wojewodka.luckene.exception.LuckeneMappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@PropertySource("classpath:luckene.properties")
@Service
@RequiredArgsConstructor
public class ExampleLuckeneConfiguration {

	private final ExampleLuckeneRepository repository;

	public void generateAndWrite() throws IOException, LuckeneMappingException {
		ExamplePojo pojo = new ExamplePojo();
		pojo.setContent("Jakiś tam content kurwo.");
		pojo.setVersion(1.0);
		repository.getWriter().write(pojo);
	}

	public static void main(String[] args) throws IOException, LuckeneMappingException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan(ExampleLuckeneConfiguration.class.getPackage().getName());
		ctx.refresh();

		ctx.getBean(ExampleLuckeneConfiguration.class);

	}

}
