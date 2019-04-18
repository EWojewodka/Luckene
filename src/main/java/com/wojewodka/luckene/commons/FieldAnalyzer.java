package com.wojewodka.luckene.commons;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;

/**
 * Simple POJO for storing custom field analyzer.
 *
 * @see PerFieldAnalyzerWrapper
 * @see #fieldAnalyzer(String, Analyzer)
 */
@Data
@RequiredArgsConstructor
public class FieldAnalyzer {

	private final String fieldName;
	private final Analyzer analyzer;

	/**
	 * Simplest way for creating {@link FieldAnalyzer}
	 */
	public static FieldAnalyzer fieldAnalyzer(String fieldName, Analyzer analyzer) {
		return new FieldAnalyzer(fieldName, analyzer);
	}

}
