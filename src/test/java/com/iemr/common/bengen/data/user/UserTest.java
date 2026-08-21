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
package com.iemr.common.bengen.data.user;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("User Test Suite")
class UserTest {

    private User populated() {
        User user = new User();
        user.setUserID(42L);
        user.setUserName("amrit-user");
        user.setDeleted(false);
        return user;
    }

    /** One mutator per field, so every generated equality branch is exercised. */
    private List<Map.Entry<String, Consumer<User>>> differingValues() {
        return List.of(
                Map.entry("userID", (Consumer<User>) user -> user.setUserID(99L)),
                Map.entry("userName", (Consumer<User>) user -> user.setUserName("someone-else")),
                Map.entry("deleted", (Consumer<User>) user -> user.setDeleted(true)));
    }

    private List<Map.Entry<String, Consumer<User>>> nullValues() {
        return List.of(
                Map.entry("userID", (Consumer<User>) user -> user.setUserID(null)),
                Map.entry("userName", (Consumer<User>) user -> user.setUserName(null)),
                Map.entry("deleted", (Consumer<User>) user -> user.setDeleted(null)));
    }

    @Nested
    @DisplayName("Field access")
    class FieldTests {

        @Test
        @DisplayName("a new user should leave every field unset")
        void newUser_shouldLeaveEveryFieldUnset() {
            User user = new User();

            assertNull(user.getUserID());
            assertNull(user.getUserName());
            assertNull(user.getDeleted());
        }

        @Test
        @DisplayName("every field should round-trip through its accessors")
        void everyField_shouldRoundTrip() {
            User user = populated();

            assertEquals(42L, user.getUserID());
            assertEquals("amrit-user", user.getUserName());
            assertEquals(false, user.getDeleted());
        }
    }

    @Nested
    @DisplayName("Value semantics")
    class ValueSemanticsTests {

        @Test
        @DisplayName("two users holding the same values should be equal and share a hash code")
        void usersWithSameValues_shouldBeEqual() {
            assertEquals(populated(), populated());
            assertEquals(populated().hashCode(), populated().hashCode());
        }

        @Test
        @DisplayName("a user should equal itself and never equal null or an unrelated type")
        void user_shouldEqualItselfAndNotOtherTypes() {
            User user = populated();

            assertEquals(user, user);
            assertNotEquals(user, null);
            assertNotEquals(user, "not a User");
        }

        @Test
        @DisplayName("a difference in any single field should break equality")
        void equals_shouldDetectDifferenceInEveryField() {
            assertAll(differingValues().stream().map(field -> () -> {
                User variant = populated();
                field.getValue().accept(variant);
                assertNotEquals(populated(), variant,
                        "changing " + field.getKey() + " must break equality");
            }));
        }

        @Test
        @DisplayName("a null in any single field should break equality in both directions")
        void equals_shouldDetectNullInEveryField() {
            assertAll(nullValues().stream().map(field -> () -> {
                User variant = populated();
                field.getValue().accept(variant);
                assertNotEquals(populated(), variant,
                        "nulling " + field.getKey() + " must break equality");
                assertNotEquals(variant, populated(),
                        "equality must stay symmetric when " + field.getKey() + " is null");
            }));
        }

        @Test
        @DisplayName("two unset users should be equal and share a hash code")
        void unsetUsers_shouldBeEqual() {
            assertEquals(new User(), new User());
            assertEquals(new User().hashCode(), new User().hashCode());
        }

        @Test
        @DisplayName("toString should name the type and expose the user identity")
        void toString_shouldNameTypeAndExposeIdentity() {
            String text = populated().toString();

            assertTrue(text.startsWith("User("));
            assertTrue(text.contains("42"));
            assertTrue(text.contains("amrit-user"));
        }
    }
}
