package com.avensys.rts.instructionservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.avensys.rts.instructionservice.payloadrequest.InstructionRequestDTO;
import com.avensys.rts.instructionservice.payloadresponse.InstructionResponseDTO;
import com.avensys.rts.instructionservice.service.InstructionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@ExtendWith(MockitoExtension.class)
public class InstructionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AutoCloseable autoCloseable;

	@InjectMocks
	InstructionController instructionController;
	@Mock
	MessageSource messageSource;
	@Mock
	InstructionService instructionService;
	InstructionRequestDTO instructionRequestDTO;
	InstructionResponseDTO instructionResponseDTO;
	String formData = "{\"id\":1,\"accountSubmissionData\":{\"msa\":\"yes\",\"revenue\":32432434,\"website\":\"www.tcs.com\",\"industry\":\"InformationTechnology\",\"salesName\":\"Test\",\"leadSource\":\"Test\",\"accountName\":\"TCS\",\"addressCity\":\"\",\"billingCity\":\"\",\"subIndustry\":\"SoftwareDevelopment\",\"addressLine1\":\"Bhopal\",\"addressLine2\":\"\",\"addressLine3\":\"\",\"accountRating\":\"Tier1\",\"accountSource\":\"TalentService\",\"accountStatus\":\"Active\",\"leadSalesName\":\"Test\",\"noOfEmployees\":6,\"parentCompany\":\"\",\"accountRemarks\":\"\",\"addressCountry\":\"\",\"billingAddress\":\"true\",\"landlineNumber\":324,\"secondaryOwner\":\"Test\",\"landlineCountry\":\"\",\"leadAccountName\":\"TCS\",\"revenueCurrency\":\"INR₹\",\"uploadAgreement\":\"Reema_Sahu_Java_5Yrs.docx(1).pdf\",\"addressPostalCode\":\"\",\"billingAddressLine1\":\"Bhopal\",\"billingAddressLine2\":\"\",\"billingAddressLine3\":\"\",\"billingAddressCountry\":\"\",\"billingAddressPostalCode\":\"\"},\"commercialSubmissionData\":{\"msp\":\"Test\",\"markUp\":\"Test\"},\"accountNumber\":\"A0958950\",\"createdAt\":\"2024-01-16T13:02:13.006307\",\"updatedAt\":\"2024-01-16T13:06:15.374175\",\"accountCountry\":\"India\",\"createdByName\":\"Super1Admin1\",\"updatedByName\":\"Super1Admin1\"}";

	@BeforeEach
	public void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		instructionRequestDTO = new InstructionRequestDTO(1, "guidelines", 1, 1, "entityType", 1, formData);
		instructionResponseDTO = new InstructionResponseDTO(1, 1, "guidelines", 1, "submissionData", 1);
		this.mockMvc = MockMvcBuilders.standaloneSetup(instructionController).build();
	}

	@AfterEach
	public void tearDown() {

	}

	@Test
	void testCreateInstruction() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		RequestBuilder request = MockMvcRequestBuilders.post("/api/instructions/add")
				.content(asJsonString(new InstructionRequestDTO(1, "guidelines", 1, 1, "entityType", 1, formData)))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}

	/**
	 * This method is used to convert Json object to string.
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Test
	void testGetInstructionById() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/instructions/{instructionId}", 1)).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	void testGetInstructionByAccountId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/instructions").param("accountId", "1"))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testGetInstructionByEntityId() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/instructions/entity/{entityType}/{entityId}", "entityType", 1))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void testUpdateInstructionById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		RequestBuilder request = MockMvcRequestBuilders.put("/api/instructions/{instructionId}", 1)
				.content(asJsonString(new InstructionRequestDTO(1, "guidelines", 1, 1, "entityType", 1, formData)))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andReturn();
	}

	@Test
	void testDeleteInstructionByEntityId() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/instructions/entity/{entityType}/{entityId}", "entityType", 1))
				.andExpect(status().isOk()).andReturn();
	}

}
