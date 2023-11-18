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
            if (current_piece != '/' && !Character.isDigit(current_piece)) {
                // Integer pieceScore = scoring_system.get(pieceAsString);
                if (Character.isLowerCase(current_piece)) {
                    Integer pieceScore = scoring_system.get(pieceAsString);
                    black_score += pieceScore.intValue();
                }
                else{
                    char lowercaseChar = Character.toLowerCase(current_piece);
                    String otherPeice = String.valueOf(lowercaseChar);
                    Integer pieceScore = scoring_system.get(otherPeice);
                    white_score += pieceScore.intValue();
                }
        }
    }
    return white_score - black_score;
    
    }
}
