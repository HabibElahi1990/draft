package com.example.draft.repository;

import com.example.draft.model.Draft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DraftRepository extends JpaRepository<Draft,Integer> {

    List<Draft> findDraftByPersonIdAndStatus(Integer personId,Integer status);

    @Query("select d from Draft  d where d.id in (?1)")
    List<Draft> findDraftListByIds(List<Integer> idList);
}
