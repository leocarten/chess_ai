package com.github;
import java.util.HashMap;
import java.util.Map;

import com.github.bhlangonijr.chesslib.Board;

public class Score {

    public Map<String, Integer> scoringSystem() {
        Map<String, Integer> scoring_map = new HashMap<>();
        scoring_map.put("p", 1);
        scoring_map.put("n", 3);
        scoring_map.put("b", 3);
        scoring_map.put("r", 5);
        scoring_map.put("q", 9);
        scoring_map.put("k", 11);
        return scoring_map;
    }
    
    public int evaluateScore(String board) {
        int white_score = 0;
        int black_score = 0;
        Map<String, Integer> scoring_system = scoringSystem();
        
        for (int i = 0; i < board.length(); i++) {
            char current_piece = board.charAt(i);
            String pieceAsString = String.valueOf(current_piece); // Convert char to String
            if (!Character.isDigit(current_piece) && current_piece != '/') {
                if (Character.isLowerCase(current_piece)) {
                    Integer pieceScore = scoring_system.get(pieceAsString);
                    black_score += pieceScore != null ? pieceScore : 0;
                } else {
                    char lowercaseChar = Character.toLowerCase(current_piece);
                    String otherPiece = String.valueOf(lowercaseChar);
                    Integer pieceScore = scoring_system.get(otherPiece);
                    white_score += pieceScore != null ? pieceScore : 0;
                }
            }
    }
    return white_score - black_score;
    
    }
}
