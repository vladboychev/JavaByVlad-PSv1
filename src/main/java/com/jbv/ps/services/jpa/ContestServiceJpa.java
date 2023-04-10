package com.jbv.ps.services.jpa;

import com.jbv.ps.mappers.ContestMapper;
import com.jbv.ps.models.ContestDto;
import com.jbv.ps.repositories.ContestRepositoryJpa;
import com.jbv.ps.services.contracts.ContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class ContestServiceJpa implements ContestService {

    private final ContestRepositoryJpa contestRepositoryJpa;
    private final ContestMapper contestMapper;
    @Override
    public List<ContestDto> listContests() {
        return contestRepositoryJpa.findAll()
                .stream()
                .map(contestMapper::contestToContestDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ContestDto> getContestById(UUID id) {
        return Optional.ofNullable(contestMapper.contestToContestDto(contestRepositoryJpa.findById(id)
                .orElse(null)));
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
