package de.slag.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConsoleUtils {

	private static final Log LOG = LogFactory.getLog(ConsoleUtils.class);

	public static String determineUserHome() {
		return System.getProperty("user.home");
	}

	public static String runConsoleCommand(final String command) {
		try {
			return runInternal(command);
		} catch (final IOException e) {
			throw new RuntimeException("error execute command: " + command, e);
		}
	}

	// TODO: error bzw. return handling. bisher geht alles auf error.
	private static String runInternal(final String command) throws IOException {
		final Process exec = Runtime.getRuntime().exec(command);
		while (exec.isAlive()) {
			LOG.debug("process alive, sleep.");
			SleepUtils.sleepFor(50);
		}
		final String readInput = readInput(exec.getInputStream());

		final String error = readInput(exec.getErrorStream());
		
		exec.destroy();
		final int exitValue = exec.exitValue();
		if(exitValue != 0) {
			throw new IOException(error);
		}
		return error;
	}

	private static String readInput(final InputStream is) throws IOException {
		final StringBuilder errorBuffer = new StringBuilder();
		while (true) {
			final int read = is.read();
			if (read == -1) {
				break;
			}
			final char c = (char) read;
			errorBuffer.append(c);
		}
		return errorBuffer.toString();
	}

}
