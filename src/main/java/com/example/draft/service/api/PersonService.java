package com.example.draft.service.api;

import com.example.draft.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PERSON-SERVICE")
public interface PersonService {

    @GetMapping("/person/findByNationalCode")
    public Person findByNationalCode(@RequestParam String nationalCode);

}
