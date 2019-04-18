package com.wojewodka.luckene.index;

import java.io.IOException;

public interface LuckeneWriter<T> {

	void write(T obj) throws IOException;

}
