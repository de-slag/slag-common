package de.slag.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import de.slag.common.base.BaseException;

public class FileHashUtils {

	public static String md5(String absoluteFileName) {
		return checksum(absoluteFileName, getMessageDigest("md5"));
	}
	
	public static String sha256(String absoluteFileName) {
		
		return checksum(absoluteFileName, getMessageDigest("SHA-256"));
			
	}

	private static String checksum(String absoluteFileName, final MessageDigest md5) {
		final String requireNonNull = Objects.requireNonNull(absoluteFileName, "filename is null");
		final File file = new File(requireNonNull);
		if (!file.exists()) {
			throw new BaseException("File does not exist:" + absoluteFileName);
		}
		if (file.isDirectory()) {
			throw new BaseException("file is a directory");
		}
		return getChecksum(file, md5);
	}

	private static String getChecksum(final File file, final MessageDigest messageDigest) {
		try {
			return getFileChecksum(messageDigest, file);
		} catch (IOException e) {
			throw new BaseException(e);
		}
	}

	private static MessageDigest getMessageDigest(final String string) {
		try {
			return MessageDigest.getInstance(string);
		} catch (NoSuchAlgorithmException e) {
			throw new BaseException(e);
		}
	}

	private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
		// Get file input stream for reading the file content
		FileInputStream fis = new FileInputStream(file);

		// Create byte array to read data in chunks
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Read file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		;

		// close the stream; We don't need it now.
		fis.close();

		// Get the hash's bytes
		byte[] bytes = digest.digest();

		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// return complete hash
		return sb.toString();
	}

}
