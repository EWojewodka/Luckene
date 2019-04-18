package com.wojewodka.luckene.index;

/**
 * Representation of directory which is handled by lucene.
 */
public interface LuckeneRepository<T> {

	LuckeneDirectoryConfiguration getConfiguration();

	LuckeneWriter<T> getWriter();

	LuckeneSearcher<T> getSearcher();

}
