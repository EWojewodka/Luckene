package com.wojewodka.luckene;

import com.wojewodka.example.ExampleLuckeneRepository;
import com.wojewodka.example.ExamplePojo;
import com.wojewodka.luckene.index.LuckeneWriter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
public class LuckeneRepositoryTest extends LuckeneTestContext {
	
	@Value("${luckene.root.location}")
	private String rootLocation;
	
	@Autowired
	private ExampleLuckeneRepository exampleLuckeneRepository;
	
	@Test
	public void testTest() throws IOException {
		LuckeneWriter<ExamplePojo> writer = exampleLuckeneRepository.getWriter();
		writer.write(null);
	}
	
}
