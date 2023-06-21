package com.lenibonje.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean() {

        SomeBean someBean = new SomeBean("value 1", "value 2", "value 3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue getSomeBeanList() {

        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 8", "value 3", "value 32"),
                new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 1", "value 2", "value 3")
        );

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }


}
