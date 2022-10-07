package com.nedwrites.frdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpRequest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FrdemoApplicationTests {
	@Autowired
	private JSLTRequestController jsltRequestController;

	@Test
	public void contextLoads() throws Exception {
		Assert.notNull(jsltRequestController, "JSLTRequestController failed to init.");
	}
	@Test
	public void testJsltRequest() throws Exception {

		//Generate some random strings for setting data values.
		String string1 = RandomString.make(8);
		String string2 = RandomString.make(8);

		//Create a simple config with a single transform that returns the input object
		Transform transform = new Transform("test",true,true,".");
		List<Transform> transformList = new ArrayList<Transform>();
		transformList.add(transform);
		FeatureConfig config = new FeatureConfig(1,"testconfig", transformList );

		//Create a mock input data object;
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode mockInput = objectMapper.createObjectNode();
		mockInput.put(string1, string2);
		JSLTRequest jsltRequest = new JSLTRequest(config, mockInput);
		JsonNode output = jsltRequest.getOutput();

		//Asser that the output is the same as the input.
		Assert.isTrue(output.get("test").equals(mockInput), "JSLTRequest.getOutput() failed to return correct value.");
	}
}
