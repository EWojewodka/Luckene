package com.wojewodka.example;

import com.wojewodka.luckene.index.LuckeneDirectoryConfiguration;
import com.wojewodka.luckene.index.impl.SimpleLuckeneRepository;
import org.springframework.stereotype.Service;

@Service
public class ExampleLuckeneRepository extends SimpleLuckeneRepository<ExamplePojo> {

	@Override
	protected LuckeneDirectoryConfiguration.LuckeneDirectoryConfigurationBuilder config() {
		return LuckeneDirectoryConfiguration.builder("example_pojo");
	}

}
