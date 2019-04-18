package com.wojewodka.luckene.exception;

/**
 * Simple {@link RuntimeException} which is used in most path of luckene library.
 */
public class LuckeneException extends RuntimeException {
	
	public LuckeneException(String msg) {
		this(msg, null);
	}
	
	public LuckeneException(Throwable t) {
		this(null, t);
	}
	
	public LuckeneException(String message, Throwable t) {
		super(message, t);
	}
	
}