package com.wojewodka.luckene.index;

import com.wojewodka.luckene.commons.FieldAnalyzer;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.wojewodka.luckene.commons.FieldAnalyzer.fieldAnalyzer;

@Builder
public class LuckeneDirectoryConfiguration {

	private final String code;

	@Singular
	private final Collection<FieldAnalyzer> analyzers;

	@Getter
	private Directory directory;

	@Getter
	private IndexWriter indexWriter;

	private boolean isInitialized;

	public LuckeneDirectoryConfiguration init() throws IOException {
		if (this.isInitialized) return this;
		this.isInitialized = true;

		this.directory = new SimpleFSDirectory(Paths.get(new File("./luckene/" + this.code).getPath()));
		this.indexWriter = new IndexWriter(directory, new IndexWriterConfig(buildAnalyzer()));

		return this;
	}

	private Analyzer buildAnalyzer() {
		Map<String, Analyzer> _analyzers = new HashMap<>();
		analyzers.forEach(analyzer -> _analyzers.put(analyzer.getFieldName(), analyzer.getAnalyzer()));
		return new PerFieldAnalyzerWrapper(new StandardAnalyzer(), _analyzers);
	}

	public static class LuckeneDirectoryConfigurationBuilder {

		public LuckeneDirectoryConfigurationBuilder addAnalyzer(String field, Analyzer analyzer) {
			this.analyzer(fieldAnalyzer(field, analyzer));
			return this;
		}

	}

}
