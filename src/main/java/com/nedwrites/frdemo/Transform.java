package com.nedwrites.frdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;

public class Transform {
    private final String name;
    private final boolean useInML;
    private final boolean enabled;
    private final String jsltExpression;

    public Transform(String name, boolean useInML, boolean enabled, String jsltExpression){
       this.name = name;
       this.useInML = useInML;
       this.enabled = enabled;
       this.jsltExpression = jsltExpression;
    }
    public String getName(){
        return name;
    }
    public boolean getUseInML(){
        return useInML;
    }
    public boolean getEnabled(){
        return enabled;
    }
    public Expression getJsltExpression(){
        return Parser.compileString(jsltExpression);
    }
}
