package com.jbv.ps.services.contracts;

import com.jbv.ps.models.ContestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContestService {

    List<ContestDto> listContests();
    Optional<ContestDto> getContestById(UUID id);

    ContestDto saveNewContest(ContestDto contestDto);

    void updateContestById(UUID id, ContestDto contestDto);

    void deleteContestById(UUID id);

    void updateContestPatchById(UUID id, ContestDto contestDto);
}
