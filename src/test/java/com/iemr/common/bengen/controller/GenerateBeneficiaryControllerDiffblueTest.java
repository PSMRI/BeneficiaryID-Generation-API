package com.iemr.common.bengen.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iemr.common.bengen.domain.M_BeneficiaryRegidMapping;
import com.iemr.common.bengen.service.GenerateBeneficiaryService;

import jakarta.servlet.http.HttpServletRequest;

@ContextConfiguration(classes = {GenerateBeneficiaryController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GenerateBeneficiaryControllerDiffblueTest {
    @Autowired
    private GenerateBeneficiaryController generateBeneficiaryController;

    @Mock
    private GenerateBeneficiaryService generateBeneficiaryService;

    /**
     * Method under test:
     * {@link GenerateBeneficiaryController#getBeneficiaryIDs(String, HttpServletRequest)}
     */
    @Test
    void testGetBeneficiaryIDs() throws Exception {
        String request = "{\"benIDRequired\":123,\"vanID\":234}";
        
        HttpServletRequest httpRequest = mock(HttpServletRequest.class);
        
        M_BeneficiaryRegidMapping benMapping = new M_BeneficiaryRegidMapping(123L, 234L,Timestamp.valueOf("2020-09-09 09:09:09"),"Antu Kundu");
        
        benMapping.setBeneficiaryId(234L);
        benMapping.setBenRegId(123L);
        benMapping.setCreatedDate(Timestamp.valueOf("2020-09-09 09:09:09"));
        benMapping.setCreatedBy("Antu Kundu");
        benMapping.setVanID(234);
        benMapping.setBenIDRequired(123L);
        
        benMapping.toString();
        
        List<M_BeneficiaryRegidMapping> list = new ArrayList<>();
        
        list.add(benMapping);
        
        when(generateBeneficiaryService.getBeneficiaryIDs(benMapping.getBenIDRequired(), benMapping.getVanID())).thenReturn(list);
        
        String response = generateBeneficiaryController.getBeneficiaryIDs(request, httpRequest);
        
        Assertions.assertEquals(response, generateBeneficiaryController.getBeneficiaryIDs(request, httpRequest));
    }
}
