package com.example.draft.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DraftRequest {

    List<Draft> draftList;
}
