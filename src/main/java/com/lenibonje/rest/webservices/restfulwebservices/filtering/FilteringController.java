package com.lenibonje.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean getSomeBean(){
        return new SomeBean("value 1", "value 2", "value 3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> getSomeBeanList(){
        return Arrays.asList(
                new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 8", "value 3", "value 32"),
                new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 1", "value 2", "value 3")
                );
    }



}
