package de.slag.common.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.slag.common.base.BaseException;

public final class XmlUtils {
	
	private XmlUtils() {
		
	}

	public static void out(Object o, Consumer<String> consumer) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		marshal(o, o.getClass(), baos);
		try {
			consumer.accept(baos.toString(StandardCharsets.UTF_8.name()));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T in(Class<T> type, Supplier<String> supplier) {
		byte[] buf = supplier.get().getBytes(StandardCharsets.UTF_8);
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);
		return type.cast(unmarshall(type, bais));

	}

	private static <T> Object unmarshall(Class<T> type, ByteArrayInputStream bais) {
		try {
			return createUnmarshaller(type).unmarshal(bais);
		} catch (JAXBException e) {
			throw new BaseException(e);
		}
	}

	private static void marshal(Object o, Class<? extends Object> class1, ByteArrayOutputStream baos) {
		try {
			createMarshaller(class1).marshal(o, baos);
		} catch (JAXBException e) {
			throw new BaseException(e);
		}
	}

	private static Marshaller createMarshaller(Class<? extends Object> class1) {
		try {
			return newInstance(class1).createMarshaller();
		} catch (JAXBException e) {
			throw new BaseException(e);
		}
	}

	private static Unmarshaller createUnmarshaller(Class<? extends Object> class1) {
		try {
			return newInstance(class1).createUnmarshaller();
		} catch (JAXBException e) {
			throw new BaseException(e);
		}
	}

	private static JAXBContext newInstance(Class<? extends Object> class1) {
		try {
			return JAXBContext.newInstance(class1);
		} catch (JAXBException e) {
			throw new BaseException(e);
		}
	}
}
