package com.jbv.ps.services.impl;

import com.jbv.ps.models.ContestDto;
import com.jbv.ps.services.contracts.ContestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContestServiceImpl implements ContestService {
    @Override
    public List<ContestDto> listContests() {
        return null;
    }

    @Override
    public Optional<ContestDto> getContestById(UUID id) {
        return Optional.empty();
    }

    @Override
    public ContestDto saveNewContest(ContestDto contestDto) {
        return null;
    }

    @Override
    public void updateContestById(UUID id, ContestDto contestDto) {

    }

    @Override
    public void deleteContestById(UUID id) {

    }

    @Override
    public void updateContestPatchById(UUID id, ContestDto contestDto) {

    }
}
