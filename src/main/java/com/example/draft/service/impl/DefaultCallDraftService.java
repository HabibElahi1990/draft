package com.example.draft.service.impl;

import com.example.draft.model.Draft;
import com.example.draft.model.DraftDto;
import com.example.draft.model.DraftRequest;
import com.example.draft.model.Person;
import com.example.draft.repository.DraftRepository;
import com.example.draft.service.api.CallDraftService;
import com.example.draft.service.api.PersonService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCallDraftService implements CallDraftService {

    private final DraftRepository repository;
    private final PersonService personService;

    public DefaultCallDraftService(DraftRepository repository, PersonService personService) {
        this.repository = repository;
        this.personService = personService;
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

    @Override
    public List<Draft> findDraftListByPersonId(Integer personId) {
        return repository.findDraftByPersonIdAndStatus(personId,null);
    }

    @Override
    public void updateDraftStatus(DraftRequest draftRequest) {
        List<Integer> integerList=new ArrayList<>(draftRequest.getDraftList().size());
        draftRequest.getDraftList().forEach(n->integerList.add(n.getId()));
        List<Draft> draftListByIds = repository.findDraftListByIds(integerList);
        draftListByIds.forEach(n->n.setStatus(1));
        repository.saveAll(draftListByIds);
    }

    public Draft saveDraft(Draft draft) {
        return repository.save(draft);
    }

    private Integer getPersonId(String nationCode) {
        return personService.findByNationalCode(nationCode).getId();
    }
}
