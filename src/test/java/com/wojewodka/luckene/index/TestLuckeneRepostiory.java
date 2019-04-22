package com.wojewodka.luckene.index;

import com.wojewodka.luckene.domain.SimpleMessagePojo;
import com.wojewodka.luckene.index.impl.SimpleLuckeneRepository;
import org.springframework.stereotype.Service;

@Service
public class TestLuckeneRepostiory extends SimpleLuckeneRepository<SimpleMessagePojo> {
	
	@Override
	protected LuckeneDirectoryConfiguration.LuckeneDirectoryConfigurationBuilder config() {
		return LuckeneDirectoryConfiguration.builder("simple_message");
	}
	
}
