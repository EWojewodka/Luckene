package com.wojewodka.luckene;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@Log4j2
@NoArgsConstructor(access = PRIVATE)
public class LuckeneCommons {
	
	public static final String LUCKENE_PROPERTIES_LOCATION = "classpath:luckene.properties";
	
	private static final String CANNOT_CREATE_DIR_TEMPLATE = "Cannot create directory under [%s] path.";
	private static final String FAILED_DELETING_FILE_TEMPLATE = "Failed deleting file under [%s] path";
	
	/**
	 * Create root directory for luckene indexes.
	 *
	 * @param path  {@code nonnull} string which indicate location for root directory
	 * @param forceDelete if true then currently directory <b>will be deleted</b> (if exists)
	 */
	public static File createRootDirectory(@NonNull String path, boolean forceDelete) {
		File file = new File(path);
		boolean exists = file.exists() && file.isDirectory();
		try {
			if (exists && !forceDelete) return file; // if exists, is directory and we won't force creating new then return file.
			if (exists) logOnFalse(file.delete(), format(FAILED_DELETING_FILE_TEMPLATE, file.getCanonicalPath()));
			
			return logOnFalse(file.mkdir(), format(CANNOT_CREATE_DIR_TEMPLATE, file.getCanonicalPath()))
				? file.getCanonicalFile()
				: null;
			
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * Log message if {@code value} is false
	 */
	public static boolean logOnFalse(boolean value, String message) {
		if (!value) log.warn(message);
		return value;
	}
	
}
