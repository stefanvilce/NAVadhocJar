package no.nav.adhoct.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Input_fileTest {

	
	@Test
	public void input_fileTest()  throws NoSuchFieldException, IllegalAccessException{
		Input_file input = new Input_file();
		input.setTask_uuid("10");
		input.setFile_type("txt");
		assertTrue(input.getFile_type() == "txt");
	}
	
	@Test
	void testGetTask_uuid() {
		Input_file input = new Input_file();
		input.setTask_uuid("10");
		assertTrue(input.getTask_uuid() == "10");
	}
}
