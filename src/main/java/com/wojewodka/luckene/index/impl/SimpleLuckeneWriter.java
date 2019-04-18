package com.wojewodka.luckene.index.impl;

import com.wojewodka.luckene.index.LuckeneRepository;
import com.wojewodka.luckene.index.LuckeneWriter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

public class SimpleLuckeneWriter<T> implements LuckeneWriter<T> {

	@Autowired
	@Lazy
	private LuckeneRepository<T> repository;

	@Override
	public void write(T obj) throws IOException {
		IndexWriter indexWriter = repository.getConfiguration().getIndexWriter();
		indexWriter.commit();
		Document doc = new Document();
		doc.add(new TextField("name", "Emil", Field.Store.YES));
		indexWriter.addDocument(doc);
		indexWriter.commit();
	}

}
