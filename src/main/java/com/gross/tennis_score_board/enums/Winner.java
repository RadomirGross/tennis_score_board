package com.gross.tennis_score_board.enums;

public enum Winner {
  PLAYER1(true),
  PLAYER2(false);

  private boolean winner;

  Winner(boolean winner) {
      this.winner = winner;
  }
}
