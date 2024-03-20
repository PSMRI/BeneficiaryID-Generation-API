package com.iemr.common.bengen.utils.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.common.bengen.utils.exception.IEMRException;
import com.iemr.common.bengen.utils.redis.RedisSessionException;
import com.iemr.common.bengen.utils.sessionobject.SessionObject;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {Validator.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ValidatorDiffblueTest {
    @Mock
    private SessionObject sessionObject;

    @Autowired
    private Validator validator;

    /**
     * Method under test: {@link Validator#setSessionObject(SessionObject)}
     */
    @Test
    void testSetSessionObject() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     Validator.enableIPValidation
        //     Validator.logger
        //     Validator.session

        // Arrange
        Validator validator = new Validator();

        // Act
        validator.setSessionObject(new SessionObject());
    }

    /**
     * Method under test:
     * {@link Validator#updateCacheObj(JSONObject, String, String)}
     */
    @Test
    void testUpdateCacheObj() throws RedisSessionException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");
        when(sessionObject.setSessionObject(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Session Object");
        JSONObject responseObj = new JSONObject();

        // Act
        JSONObject actualUpdateCacheObjResult = validator.updateCacheObj(responseObj, "Key", "Ip Key");

        // Assert
        verify(sessionObject).getSessionObject(eq("Key"));
        verify(sessionObject).setSessionObject(eq("Key"), eq("{\"sessionStatus\":\"session creation failed\"}"));
        assertSame(responseObj, actualUpdateCacheObjResult);
    }

    /**
     * Method under test:
     * {@link Validator#updateCacheObj(JSONObject, String, String)}
     */
    @Test
    void testUpdateCacheObj2() throws RedisSessionException, JSONException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");
        when(sessionObject.setSessionObject(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Session Object");
        JSONObject responseObj = HTTP.toJSONObject("https://example.org/example");

        // Act
        JSONObject actualUpdateCacheObjResult = validator.updateCacheObj(responseObj, "Key", "Ip Key");

        // Assert
        verify(sessionObject).getSessionObject(eq("Key"));
        verify(sessionObject).setSessionObject(eq("Key"), eq(
                "{\"sessionStatus\":\"session creation failed\",\"HTTP-Version\":\"https:\\/\\/example.org\\/example\",\"Status-Code\":\"\",\"Reason-Phrase\":\"\"}"));
        assertSame(responseObj, actualUpdateCacheObjResult);
    }

    /**
     * Method under test:
     * {@link Validator#updateCacheObj(JSONObject, String, String)}
     */
    @Test
    void testUpdateCacheObj3() throws RedisSessionException, JSONException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");
        when(sessionObject.setSessionObject(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Session Object");
        JSONObject responseObj = mock(JSONObject.class);
        when(responseObj.put(Mockito.<String>any(), Mockito.<Object>any())).thenReturn(new JSONObject());

        // Act
        JSONObject actualUpdateCacheObjResult = validator.updateCacheObj(responseObj, "Key", "Ip Key");

        // Assert
        verify(sessionObject).getSessionObject(eq("Key"));
        verify(sessionObject).setSessionObject(eq("Key"), eq("Mock for JSONObject, hashCode: 1027220847"));
        verify(responseObj, atLeast(1)).put(Mockito.<String>any(), Mockito.<Object>any());
        assertSame(responseObj, actualUpdateCacheObjResult);
    }

    /**
     * Method under test:
     * {@link Validator#updateCacheObj(JSONObject, String, String)}
     */
    @Test
    void testUpdateCacheObj4() throws JSONException {
        // Arrange
        JSONObject responseObj = mock(JSONObject.class);
        when(responseObj.put(Mockito.<String>any(), Mockito.<Object>any())).thenThrow(new JSONException("login failed"));

        // Act
        JSONObject actualUpdateCacheObjResult = validator.updateCacheObj(responseObj, "Key", "Ip Key");

        // Assert
        verify(responseObj).put(eq("sessionStatus"), Mockito.<Object>any());
        assertSame(responseObj, actualUpdateCacheObjResult);
    }

    /**
     * Method under test: {@link Validator#getSessionObject(String)}
     */
    @Test
    void testGetSessionObject() throws RedisSessionException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");

        // Act
        String actualSessionObject = validator.getSessionObject("Key");

        // Assert
        verify(sessionObject).getSessionObject(eq("Key"));
        assertEquals("Session Object", actualSessionObject);
    }

    /**
     * Method under test: {@link Validator#getSessionObject(String)}
     */
    @Test
    void testGetSessionObject2() throws RedisSessionException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> validator.getSessionObject("Key"));
        verify(sessionObject).getSessionObject(eq("Key"));
    }

    /**
     * Method under test: {@link Validator#checkKeyExists(String, String)}
     */
    @Test
    void testCheckKeyExists() throws IEMRException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");

        // Act and Assert
        assertThrows(IEMRException.class, () -> validator.checkKeyExists("Login Key", "42 Main St"));
        verify(sessionObject).getSessionObject(eq("Login Key"));
    }

    /**
     * Method under test: {@link Validator#checkKeyExists(String, String)}
     */
    @Test
    void testCheckKeyExists2() throws IEMRException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("foo");

        // Act and Assert
        assertThrows(IEMRException.class, () -> validator.checkKeyExists("Login Key", "42 Main St"));
        verify(sessionObject).getSessionObject(eq("Login Key"));
    }

    /**
     * Method under test: {@link Validator#checkKeyExists(String, String)}
     */
    @Test
    void testCheckKeyExists3() throws IEMRException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("42");

        // Act and Assert
        assertThrows(IEMRException.class, () -> validator.checkKeyExists("Login Key", "42 Main St"));
        verify(sessionObject).getSessionObject(eq("Login Key"));
    }

    /**
     * Method under test: {@link Validator#checkKeyExists(String, String)}
     */
    @Test
    void testCheckKeyExists4() throws IEMRException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("");

        // Act and Assert
        assertThrows(IEMRException.class, () -> validator.checkKeyExists("Login Key", "42 Main St"));
        verify(sessionObject).getSessionObject(eq("Login Key"));
    }

    /**
     * Method under test: {@link Validator#checkKeyExists(String, String)}
     */
    @Test
    void testCheckKeyExists5() throws IEMRException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(IEMRException.class, () -> validator.checkKeyExists("Login Key", "42 Main St"));
        verify(sessionObject).getSessionObject(eq("Login Key"));
    }
}
