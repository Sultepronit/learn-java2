package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProfileTest {
	@Test
	public void testLoadDbConfig() {
		var props = Profile.getProperties("db");
		assertNotNull("cannot load db properties", props);
		
		var dbName = props.getProperty("database");
		assertEquals("dbName incorrect", "swingdb", dbName);
	}
}

/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ProfileTest {
	@Test
	public void testLoadDbConfig() {
		var props = Profile.getProperties("db");
		//assertNotNull("cannot load db properties", props);
		assertNotNull(props, "cannot load db properties");
		
		var dbName = props.getProperty("database");
		assertEquals("swingdb", dbName, "dbName incorrect");
	}
}
*/