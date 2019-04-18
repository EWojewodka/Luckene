package com.wojewodka.luckene.config;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

@Log4j2
public class LuckeneConfigTest {

	private static final String LUCKENE_ROOT_DIRECTORY = "./lucene";

	@AfterEach
	void afterEachTest() {
		deleteDirectory();
	}

	@BeforeEach()
	void beforeEachTest() {
		deleteDirectory();
	}

	private void deleteDirectory() {
		new File(LUCKENE_ROOT_DIRECTORY).delete();
	}


}
