package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.filling.FillWithTwoLetters;
import edu.kit.informatik.matchthree.filling.NotFill;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.MoveFactory;

public class GameTest {

  @Test
  public void testGameFullMatch() {
    Board board = new MatchThreeBoard(Token.set("XY"), 3, 3);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board.setTokenAt(Position.at(i, j), new Token("X"));
      }
    }
    assertEquals(board.toTokenString(), "XXX;XXX;XXX");
    Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
    board.setFillingStrategy(new NotFill());
    Game game = new MatchThreeGame(board, matcher);
    game.initializeBoardAndStart();
    assertEquals(board.toTokenString(), "   ;   ;   ");
    assertEquals(game.getScore(), 9);
  }
  
  @Test
  public void testGameTwoMatchesByMoveNotFill() {
    Board board = new MatchThreeBoard(Token.set("XYZ"), 3, 3);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i + j) % 2 == 0) {
          board.setTokenAt(Position.at(i, j), new Token("X"));
        } else {
          board.setTokenAt(Position.at(i, j), new Token("Y"));
        }
      }
    }
    assertEquals(board.toTokenString(), "XYX;YXY;XYX");
    Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
    board.setFillingStrategy(new NotFill());
    Game game = new MatchThreeGame(board, matcher);
    game.initializeBoardAndStart();
    assertEquals(board.toTokenString(), "XYX;YXY;XYX");
    MoveFactory moveFactory = new MoveFactoryImplementation();
    game.acceptMove(moveFactory.flipRight(Position.at(0, 1)));
    assertEquals(board.toTokenString(), "  X;  Y;  X");
    assertEquals(game.getScore(), 6);
  }
  
  @Test
  public void testGameTwoMatchesByMoveFillWithTwoLetters() {
    Board board = new MatchThreeBoard(Token.set("XYZA"), 3, 3);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i + j) % 2 == 0) {
          board.setTokenAt(Position.at(i, j), new Token("X"));
        } else {
          board.setTokenAt(Position.at(i, j), new Token("Y"));
        }
      }
    }
    assertEquals(board.toTokenString(), "XYX;YXY;XYX");
    Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
    board.setFillingStrategy(new FillWithTwoLetters("Y", "A"));
    Game game = new MatchThreeGame(board, matcher);
    game.initializeBoardAndStart();
    assertEquals(board.toTokenString(), "XYX;YXY;XYX");
    MoveFactory moveFactory = new MoveFactoryImplementation();
    game.acceptMove(moveFactory.flipRight(Position.at(0, 1)));
    assertEquals(board.toTokenString(), "YAX;AYY;YAX");
    assertEquals(game.getScore(), 6);
  }

  @Test
  public void testGameAllMatchByStart() {
    Board board = new MatchThreeBoard(Token.set("XY"), 3, 3);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i + j) % 2 == 0) {
          board.setTokenAt(Position.at(i, j), new Token("X"));
        } else {
          board.setTokenAt(Position.at(i, j), new Token("Y"));
        }
      }
    }
    assertEquals(board.toTokenString(), "XYX;YXY;XYX");
    Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(1, 1))));
    board.setFillingStrategy(new NotFill());
    Game game = new MatchThreeGame(board, matcher);
    game.initializeBoardAndStart();
    assertEquals(board.toTokenString(), "   ;   ;   ");
    assertEquals(game.getScore(), 12);
  }

  
}
