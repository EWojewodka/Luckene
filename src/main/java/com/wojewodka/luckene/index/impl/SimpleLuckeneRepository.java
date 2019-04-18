package com.wojewodka.luckene.index.impl;

import com.wojewodka.luckene.index.LuckeneDirectoryConfiguration;
import com.wojewodka.luckene.index.LuckeneRepository;
import com.wojewodka.luckene.index.LuckeneSearcher;
import com.wojewodka.luckene.index.LuckeneWriter;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Log4j2
@Getter
public abstract class SimpleLuckeneRepository<T> implements LuckeneRepository<T> {

	@Autowired
	protected LuckeneWriter<T> writer;

	@Autowired(required = false)
	protected LuckeneSearcher<T> searcher;

	private LuckeneDirectoryConfiguration configuration;

	@Override
	public final LuckeneDirectoryConfiguration getConfiguration() {
		if (configuration == null) {
			try {
				this.configuration = config().build().init();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return this.configuration;
	}

	protected abstract LuckeneDirectoryConfiguration.LuckeneDirectoryConfigurationBuilder config();

}
