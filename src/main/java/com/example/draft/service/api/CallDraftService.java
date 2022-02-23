package com.example.draft.service.api;

import com.example.draft.model.Draft;
import com.example.draft.model.DraftDto;

public interface CallDraftService {

    public Draft calcDraft(DraftDto dto);
}
