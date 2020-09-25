package de.slag.common.core.flattener;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

public class FlattenerTest {

	@Test
	public void test() {
		FlattenerConsumerTestEntity entity = new FlattenerConsumerTestEntity();
		entity.setName("Adalbert Smith");
		entity.setNumber(47);
		entity.setValid(true);
		Map<String, String> attributeValueMap = new HashMap<>();
		new Flattener().accept(entity, attributeValueMap);
		assertTrue(!attributeValueMap.isEmpty());
	}
	
	public class FlattenerConsumerTestEntity {
		private Long id;
		
		private String name;
		
		private Integer number;
		
		private Boolean valid;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public boolean isValid() {
			return BooleanUtils.isTrue(valid);
		}

		public void setValid(Boolean valid) {
			this.valid = valid;
		}

		public Long getId() {
			return id;
		}
	}

}
