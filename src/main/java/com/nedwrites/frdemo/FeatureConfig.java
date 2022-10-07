package com.nedwrites.frdemo;

import java.util.List;

/*
FeatureConfig contains a List of Transforms used to process the data input.
 */
public class FeatureConfig {
    private final long id;
    private final String name;
    private final List<Transform> transforms;

    public FeatureConfig(long id, String name, List<Transform> transforms){
        this.id = id;
        this.name = name;
        this.transforms = transforms;
    }

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public List<Transform> getTransforms(){
        return transforms;
    }
}
