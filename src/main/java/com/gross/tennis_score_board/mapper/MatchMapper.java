package com.gross.tennis_score_board.mapper;

import com.gross.tennis_score_board.dto.MatchDTO;
import com.gross.tennis_score_board.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchDTO matchToMatchDTO(Match match);
    Match matchDTOToMatch(MatchDTO matchDTO);
    List<MatchDTO> matchListToMatchDTOList(List<Match> matchList);
    List<Match> matchDTOListToMatchList(List<MatchDTO> matchDTOList);
}
