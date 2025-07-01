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
package com.iemr.common.bengen.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class CookieUtilTest {

    @Mock
    HttpServletRequest request;

    @InjectMocks
    @Spy
    CookieUtil cookieUtil;

    @Test
    void getCookieValue_cookieExists() {
        Cookie cookie = mock(Cookie.class);
        doReturn("myCookieName").when(cookie).getName();
        doReturn("myCookieValue").when(cookie).getValue();
        doReturn(new Cookie[]{cookie}).when(request).getCookies();

        Optional<String> result = cookieUtil.getCookieValue(request, "myCookieName");

        assertTrue(result.isPresent());
        assertEquals("myCookieValue", result.get());
    }

    @Test
    void getCookieValue_cookieDoesNotExist() {
        doReturn(new Cookie[0]).when(request).getCookies();
        Optional<String> result = cookieUtil.getCookieValue(request, "myCookieName");
        assertFalse(result.isPresent());
    }

    @Test
    void getCookieValue_cookiesIsNull() {
        doReturn(null).when(request).getCookies();
        Optional<String> result = cookieUtil.getCookieValue(request, "myCookieName");
        assertFalse(result.isPresent());
    }


    @Test
    void getJwtTokenFromCookie_jwtCookieExists() {
        Cookie jwtCookie = mock(Cookie.class);
        doReturn("Jwttoken").when(jwtCookie).getName();
        doReturn("myJwtToken").when(jwtCookie).getValue();
        doReturn(new Cookie[]{jwtCookie}).when(request).getCookies();

        String jwtToken = CookieUtil.getJwtTokenFromCookie(request);

        assertEquals("myJwtToken", jwtToken);
    }

    @Test
    void getJwtTokenFromCookie_jwtCookieDoesNotExist() {
        Cookie otherCookie = mock(Cookie.class);
        doReturn("otherCookie").when(otherCookie).getName();
        // doReturn("otherValue").when(otherCookie).getValue();
        doReturn(new Cookie[]{otherCookie}).when(request).getCookies();

        String jwtToken = CookieUtil.getJwtTokenFromCookie(request);

        assertNull(jwtToken);
    }

    @Test
    void getJwtTokenFromCookie_cookiesIsNull() {
        doReturn(null).when(request).getCookies();
        String jwtToken = CookieUtil.getJwtTokenFromCookie(request);
        assertNull(jwtToken);
    }
}