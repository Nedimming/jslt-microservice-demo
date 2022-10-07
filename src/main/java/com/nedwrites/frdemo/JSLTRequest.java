package com.nedwrites.frdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.schibsted.spt.data.jslt.Expression;

import java.util.List;

public class JSLTRequest {
    private final FeatureConfig config;
    private final JsonNode input;

    public JSLTRequest(FeatureConfig config, JsonNode input){
        this.config = config;
        this.input = input;
    }

    public FeatureConfig getConfig(){
        return config;
    }

    public JsonNode getInput(){
        return input;
    }

    public JsonNode getOutput() {
        ObjectMapper objectMapper = new ObjectMapper();
        //Allow for arrays of similar input objects.
        if(input.isArray()){
            ArrayNode output = objectMapper.createArrayNode();
            for (JsonNode node : input) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                output.add(this.processNode(node, objectNode));
            }
            return output;
        }else {
            //Process the entire input object as a single input.
            ObjectNode output = objectMapper.createObjectNode();
            this.processNode(input, output);
            return output;
        }
    }
    public JsonNode processNode(JsonNode node, ObjectNode objectNode){
        List<Transform> transforms = config.getTransforms();
        for (Transform transform : transforms) {
            if (transform.getEnabled()) {
                Expression jslt = transform.getJsltExpression();
                objectNode.set(transform.getName(), jslt.apply(node));
            }
        }
        return objectNode;
    }
}