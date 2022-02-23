package com.example.draft.service.impl;

import com.example.draft.model.Draft;
import com.example.draft.model.DraftDto;
import com.example.draft.model.Person;
import com.example.draft.repository.DraftRepository;
import com.example.draft.service.api.CallDraftService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class DefaultCallDraftService implements CallDraftService {

    private final DraftRepository repository;
    private final RestTemplate restTemplate;

    public DefaultCallDraftService(DraftRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Draft calcDraft(DraftDto dto) {

        Draft draft = Draft.builder()
                .personId(getPersonId(dto.getNationalCode()))
                .totalAmount(dto.getTotalAmount())
                .franchiseAmount(dto.getFranchiseAmount())
                .firstInsuredAmount(dto.getFirstInsuredAmount())
                .nonInsuredAmount(dto.getNonInsuredAmount()).build();
        CallDraft callDraft = new CallDraft();
        Long payableAmount = callDraft.calcAmount(draft);
        draft.setPayableAMount(payableAmount);
        return saveDraft(draft);
    }

    public Draft saveDraft(Draft draft) {
        return repository.save(draft);
    }

    private Integer getPersonId(String nationCode) {
        String url = "http://PERSON-SERVICE/person/findByNationalCode";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nationalCode", nationCode);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Person> p = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, Person.class);
        return p.getBody().getId();
    }
}
