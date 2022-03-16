package com.example.draft.service.api;

import com.example.draft.model.Draft;
import com.example.draft.model.DraftDto;
import com.example.draft.model.DraftRequest;

import java.util.List;

public interface CallDraftService {

    public Draft calcDraft(DraftDto dto);

    List<Draft> findDraftListByPersonId(Integer personId);

    void updateDraftStatus(DraftRequest draftRequest);
}
