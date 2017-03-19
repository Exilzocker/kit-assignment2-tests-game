package edu.kit.informatik.matchthree.filling;

import edu.kit.informatik.matchthree.framework.FillingStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

public class FillWithTwoLetters implements FillingStrategy {

  private String letter1;
  private String letter2;

  public FillWithTwoLetters(String letter1, String letter2) {
    super();
    this.letter1 = letter1;
    this.letter2 = letter2;
  }

  @Override
  public void fill(Board board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board.getTokenAt(Position.at(i, j)) == null) {
          if ((i + j) % 2 == 0) {
            board.setTokenAt(Position.at(i, j), new Token(letter1));
          } else {
            board.setTokenAt(Position.at(i, j), new Token(letter2));
          }
        }
      }
    }
  }

}
