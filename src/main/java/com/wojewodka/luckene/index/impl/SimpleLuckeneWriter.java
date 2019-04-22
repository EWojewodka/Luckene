package com.wojewodka.luckene.index.impl;

import com.wojewodka.luckene.exception.LuckeneMappingException;
import com.wojewodka.luckene.index.LuckeneRepository;
import com.wojewodka.luckene.index.LuckeneWriter;
import com.wojewodka.luckene.mapper.LuckeneMapper;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

public class SimpleLuckeneWriter<T> implements LuckeneWriter<T> {

	@Autowired
	@Lazy
	private LuckeneRepository<T> repository;

	@Autowired
	private LuckeneMapper luckeneMapper;

	@Override
	public void write(T obj) throws IOException, LuckeneMappingException {
		IndexWriter indexWriter = repository.getConfiguration().getIndexWriter();
		indexWriter.commit();
		indexWriter.addDocument(luckeneMapper.serialize(obj));
		indexWriter.commit();
	}

}
