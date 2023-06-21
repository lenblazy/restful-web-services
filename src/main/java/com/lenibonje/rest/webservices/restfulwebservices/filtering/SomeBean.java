package com.lenibonje.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIgnoreProperties({"field3", "field1"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {

//    @JsonIgnore
    private String field1;
    private String field2;
    private String field3;

    public SomeBean(String s1, String s2, String s3) {
        field1 = s1;
        field2 = s2;
        field3 = s3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
