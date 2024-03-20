package com.iemr.common.bengen.controller.version;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VersionController.class})
@ExtendWith(SpringExtension.class)
class VersionControllerDiffblueTest {
    @Autowired
    private VersionController versionController;

    /**
     * Method under test: {@link VersionController#versionInformation()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testVersionInformation() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Current JVM does not support JDK which compiled the project.
        //   Diffblue Cover is running on JVM version <unknown>, but your
        //   project was built with JDK version <unknown>.
        //   Diffblue Cover needs to be executed with the same or a more recent Java
        //   version than the version with which the project was compiled.
        //   Classes compiled with wrong JDK version:
        //     org/apache/jasper/servlet/JspCServletContext has been compiled by a more recent version of the Java Runtime (class file version 65.0), this version of the Java Runtime only recognizes class file versions up to 61.0
        //   For best results recompile the project and run Diffblue Cover with the same
        //   supported Java version: 8, 11 (but not 11.0.7), 17, and 21.
        //   See https://diff.blue/E051 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/version");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(versionController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
