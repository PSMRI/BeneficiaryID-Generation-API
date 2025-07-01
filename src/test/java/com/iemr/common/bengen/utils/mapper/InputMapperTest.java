/*
* AMRIT - Accessible Medical Records via Integrated Technologies
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.common.bengen.utils.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonSyntaxException;

class InputMapperTest {

    static class TestPojo {
        String name;
        int value;
        Date date;

        public String getName() { return name; }
        public int getValue() { return value; }
        public Date getDate() { return date; }
    }

    @Test
    void testGsonStaticFactoryMethod() {
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
        assertNull(result.getDate());
    }

    @Test
    void testFromJson_validJsonWithDate() throws ParseException {
        InputMapper mapper = InputMapper.gson();
        String json = "{\"name\":\"itemWithDate\", \"value\":200, \"date\":\"2023-10-26T10:30:45.123\"}";
        
        TestPojo result = mapper.fromJson(json, TestPojo.class);

        assertNotNull(result);
        assertEquals("itemWithDate", result.getName());
        assertEquals(200, result.getValue());
        assertNotNull(result.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date expectedDate = sdf.parse("2023-10-26T10:30:45.123");
        
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
        String json = "";
        
        // InputMapper's fromJson method (likely catching JsonSyntaxException internally)
        // returns null when given an empty string.
        TestPojo result = mapper.fromJson(json, TestPojo.class);
        assertNull(result);
    }

    @Test
    void testFromJson_emptyJsonObject() {
        InputMapper mapper = InputMapper.gson();
        String json = "{}";
        
        TestPojo result = mapper.fromJson(json, TestPojo.class);
        
        assertNotNull(result);
        assertNull(result.getName());
        assertEquals(0, result.getValue());
        assertNull(result.getDate());
    }

    @Test
    void testFromJson_malformedJson() {
        InputMapper mapper = InputMapper.gson();
        String json = "{\"name\":\"testName\", \"value\":,}";
        
        JsonSyntaxException thrown = assertThrows(JsonSyntaxException.class, () -> mapper.fromJson(json, TestPojo.class));
        assertNotNull(thrown);
    }
}
