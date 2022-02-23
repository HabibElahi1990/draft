package com.example.draft.controller;

import com.example.draft.model.Draft;
import com.example.draft.model.DraftDto;
import com.example.draft.service.api.CallDraftService;
import org.springframework.web.bind.annotation.*;

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
}
