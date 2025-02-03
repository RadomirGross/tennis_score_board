package com.gross.tennis_score_board.mapper;

import com.gross.tennis_score_board.dto.PlayerDTO;
import com.gross.tennis_score_board.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    PlayerDTO playerToPlayerDTO(Player player);
    Player playerDTOToPlayer(PlayerDTO playerDTO);
    List<PlayerDTO> playerListToPlayerDTOList(List<Player> playerList);
}
