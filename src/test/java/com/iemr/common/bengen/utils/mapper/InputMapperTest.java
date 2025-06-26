package com.iemr.common.bengen.utils.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonSyntaxException;

class InputMapperTest {

    // Simple POJO for testing deserialization
    static class TestPojo {
        String name;
        int value;
        Date date; // To test date deserialization

        // Getters for assertions
        public String getName() { return name; }
        public int getValue() { return value; }
        public Date getDate() { return date; }
    }

    @Test
    void testGsonStaticFactoryMethod() {
        // This method calls the InputMapper constructor internally.
        // It should return a non-null instance of InputMapper.
        InputMapper mapper = InputMapper.gson();
        assertNotNull(mapper);
        assertTrue(mapper instanceof InputMapper);
    }

    @Test
    void testFromJson_validJson() {
        InputMapper mapper = InputMapper.gson();
        String json = "{\"name\":\"testName\", \"value\":100}";
        
        TestPojo result = mapper.fromJson(json, TestPojo.class);

        assertNotNull(result);
        assertEquals("testName", result.getName());
        assertEquals(100, result.getValue());
        assertNull(result.getDate()); // Date field not present in JSON
    }

    @Test
    void testFromJson_validJsonWithDate() throws ParseException {
        InputMapper mapper = InputMapper.gson();
        // Date format "yyyy-MM-dd'T'HH:mm:ss.SSS" is set in InputMapper constructor
        String json = "{\"name\":\"itemWithDate\", \"value\":200, \"date\":\"2023-10-26T10:30:45.123\"}";
        
        TestPojo result = mapper.fromJson(json, TestPojo.class);

        assertNotNull(result);
        assertEquals("itemWithDate", result.getName());
        assertEquals(200, result.getValue());
        assertNotNull(result.getDate());

        // Verify the date by parsing the expected string with the same format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date expectedDate = sdf.parse("2023-10-26T10:30:45.123");
        
        // Compare the milliseconds to account for potential precision differences
        assertEquals(expectedDate.getTime(), result.getDate().getTime());
    }

    @Test
    void testFromJson_nullJson() {
        InputMapper mapper = InputMapper.gson();
        String json = null;
        
        TestPojo result = mapper.fromJson(json, TestPojo.class);
        assertNull(result);
    }

    @Test
    void testFromJson_emptyJsonString() {
        InputMapper mapper = InputMapper.gson();
        String json = ""; // Gson treats an empty string as invalid JSON
        
        // Assign the thrown exception to a variable (optional, for inspection)
    TestPojo result = mapper.fromJson(json, TestPojo.class);
    assertNull(result); // Expecting null, not exception
    }

    @Test
    void testFromJson_emptyJsonObject() {
        InputMapper mapper = InputMapper.gson();
        String json = "{}";
        
        TestPojo result = mapper.fromJson(json, TestPojo.class);
        
        assertNotNull(result);
        assertNull(result.getName()); // Default value for String
        assertEquals(0, result.getValue()); // Default value for primitive int
        assertNull(result.getDate()); // Default value for Object Date
    }

    @Test
    void testFromJson_malformedJson() {
        InputMapper mapper = InputMapper.gson();
        String json = "{\"name\":\"testName\", \"value\":,}"; // Malformed JSON
        
        JsonSyntaxException thrown = assertThrows(JsonSyntaxException.class, () -> mapper.fromJson(json, TestPojo.class));
        assertNotNull(thrown);
    }
}