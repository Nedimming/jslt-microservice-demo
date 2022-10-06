package com.nedwrites.frdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        ObjectNode output = objectMapper.createObjectNode();
        List<Transform> transforms = config.getTransforms();
        for (Transform transform : transforms){
            if(transform.getEnabled()) {
                Expression jslt = transform.getJsltExpression();
                output.set(transform.getName(), jslt.apply(input));
            }
        }
        return output;
    }
}