package com.github.bhlangonijr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.github.Score;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

public class optimizedMove {

    public static int minimax(Board board, int depth, boolean maximizingPlayer) {
        if (depth == 0 || board.isDraw() || board.isStaleMate()) {
            Score score = new Score();
            return score.evaluateScore(board.getFen());
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : board.legalMoves()) {
                board.doMove(move);
                int eval = minimax(board, depth - 1, false);
                board.undoMove();
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : board.legalMoves()) {
                board.doMove(move);
                int eval = minimax(board, depth - 1, true);
                board.undoMove();
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    public String findBestMove(Board board, int depth) {
        Move bestMove = null;
        int maxEval = Integer.MIN_VALUE;

        for (Move move : board.legalMoves()) {
            board.doMove(move.toString());
            int eval = minimax(board, depth - 1, false);
            board.undoMove();

            if (eval > maxEval) {
                maxEval = eval;
                bestMove = move;
            }
        }
        return bestMove.toString();
    }

}
