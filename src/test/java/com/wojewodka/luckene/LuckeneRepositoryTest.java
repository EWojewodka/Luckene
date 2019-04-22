package com.wojewodka.luckene;

import com.wojewodka.example.ExampleLuckeneRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class LuckeneRepositoryTest extends LuckeneTestContext {
	
	@Value("${luckene.root.location}")
	private String rootLocation;
	
	@Autowired
	private ExampleLuckeneRepository exampleLuckeneRepository;
	
}
