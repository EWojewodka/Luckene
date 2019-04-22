package com.wojewodka.luckene.index;

import com.wojewodka.luckene.exception.LuckeneMappingException;

import java.io.IOException;

public interface LuckeneWriter<T> {

	void write(T obj) throws IOException, LuckeneMappingException;

}
