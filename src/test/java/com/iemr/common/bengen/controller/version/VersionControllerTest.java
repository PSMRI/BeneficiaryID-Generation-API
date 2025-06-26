package com.iemr.common.bengen.controller.version;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.iemr.common.bengen.utils.response.OutputResponse;

@ExtendWith(MockitoExtension.class)
class VersionControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        VersionController versionController = new VersionController();

        mockMvc = MockMvcBuilders.standaloneSetup(versionController).build();

        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should return git.properties content when successfully read from test resources")
    void versionInformation_shouldReturnGitPropertiesContent() throws Exception {
        String expectedGitPropertiesContent;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("git.properties")) {
            if (inputStream == null) {
                throw new IOException("git.properties file not found in test resources.");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                expectedGitPropertiesContent = reader.lines().collect(Collectors.joining("\n"));
                if (!expectedGitPropertiesContent.endsWith("\n")) {
                    expectedGitPropertiesContent += "\n";
                }
            }
        }

        OutputResponse expectedOutput = new OutputResponse();
        expectedOutput.setResponse(expectedGitPropertiesContent);

        String expectedJsonResponse = expectedOutput.toString();

        mockMvc.perform(get("/version"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse));
    }
}
