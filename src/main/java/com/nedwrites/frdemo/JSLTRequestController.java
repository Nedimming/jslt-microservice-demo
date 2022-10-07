package com.nedwrites.frdemo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

/*
Simple controller to handle our single endpoint "transform".
TODO: Implement Authentication and Authorization
 */
@RestController
public class JSLTRequestController {
    @PostMapping("/transform")
    public JsonNode transform(@RequestBody JSLTRequest JSLTRequest){
        return JSLTRequest.getOutput();
    }
}