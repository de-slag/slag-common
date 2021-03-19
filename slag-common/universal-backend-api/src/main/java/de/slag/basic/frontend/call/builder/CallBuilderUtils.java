package de.slag.basic.frontend.call.builder;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

public class CallBuilderUtils {

	static void assertNoError(Response response) {
		final int status = response.getStatus();
		if (status != 200) {
			response.getStatus();
			final StatusType statusInfo = response.getStatusInfo();

			StringBuilder sb = new StringBuilder();
			sb.append(String.format("[%s]", status));
			sb.append(String.format(", %s", statusInfo));

			final String entityText = response.readEntity(String.class);
			boolean noText = entityText == null || "".equals(entityText);
			final String msg = String.format("something went wrong: %s " + (noText ? "(no text)" : entityText),
					sb.toString());
			throw new RuntimeException(msg);
		}
	}

}
