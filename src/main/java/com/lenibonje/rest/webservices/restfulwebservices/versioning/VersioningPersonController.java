package com.lenibonje.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {


    //Twitter
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Bob CHarlie");
    }

    //Twitter
    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Bob","CHarlie"));
    }

    //Amazon
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonParamV1() {
        return new PersonV1("Bob CHarlie");
    }

    //Amazon
    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonParamV2() {
        return new PersonV2(new Name("Bob","CHarlie"));
    }

    //Microsoft
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonHeaderV1() {
        return new PersonV1("Bob CHarlie");
    }

    //Microsoft
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonHeaderV2() {
        return new PersonV2(new Name("Bob","CHarlie"));
    }


    //Github
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonAcceptHeaderV1() {
        return new PersonV1("Bob CHarlie");
    }

    //Microsoft
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonAcceptHeaderV2() {
        return new PersonV2(new Name("Bob","CHarlie"));
    }





}
