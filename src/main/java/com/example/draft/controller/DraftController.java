package com.example.draft.controller;

import com.example.draft.model.Draft;
import com.example.draft.model.DraftDto;
import com.example.draft.model.DraftRequest;
import com.example.draft.service.api.CallDraftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/draft")
public class DraftController {

    private final CallDraftService callDraftService;

    public DraftController(CallDraftService callDraftService) {
        this.callDraftService = callDraftService;
    }

    @PostMapping
    public Draft calcDraft(@RequestBody DraftDto dto){
        return callDraftService.calcDraft(dto);
    }


    @GetMapping("/findDraftListByPersonId")
    public List<Draft> findDraftListByPersonId(@RequestParam Integer personId){
       return callDraftService.findDraftListByPersonId(personId);
    }

    @PutMapping("/updateDraftStatus")
    public void updateDraftStatus(@RequestBody DraftRequest draftRequest){
        callDraftService.updateDraftStatus(draftRequest);
    }
}
