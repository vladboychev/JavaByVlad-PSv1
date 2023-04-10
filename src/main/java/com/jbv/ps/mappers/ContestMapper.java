package com.jbv.ps.mappers;

import com.jbv.ps.domain.Contest;
import com.jbv.ps.models.ContestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContestMapper {

    Contest contestDtoToContest(ContestDto contestDto);

    ContestDto contestToContestDto(Contest contest);
}
