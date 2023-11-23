package com.github;
import com.github.bhlangonijr.ParaPrimes;
import com.github.bhlangonijr.SeqPrimes;
import com.github.bhlangonijr.optimizedMove;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Cores;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Main {

    public boolean containsMove(List<Move> moves, String userMove) {
        for (Move move : moves) {
            String theMove = move.toString();
            if (theMove.equals(userMove)) {
                return true; // Return true if the move is found in the list
            }
        }
        return false; // Return false if the move is not found in the list
    }
    

    public static double calculateAverage(ArrayList<Integer> list) {

        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum/(list.size());
    }

    public static void main(String[] args) {
        Random random = new Random();
        // Get the amount of cores on this Machine
        Cores cores = new Cores();
        int amountOfCores = cores.getNumCores();
        System.out.println("This machine has: "+amountOfCores+" cores.");

        // View the speed using single core using Prime number algorithms.
        SeqPrimes seqPrimes = new SeqPrimes();
        seqPrimes.getPrimes(30000);

        // View the speed using multiple core using Prime number algorithms.
        ParaPrimes paraPrimes = new ParaPrimes();
        paraPrimes.getPrimes(10000, amountOfCores);

        /*
         === FOOD FOR THOUGHT ===
         * We will need to think about how many cores we want to use per iteration.
         * Speed-up only really begins to occur when we have >10,000 iterations...
         */

        String break_up = "===============================";
        Score score = new Score();
        // if(!board.isStaleMate() && !board.isDraw()){ // might need to change this logic
        //     System.out.println(break_up);
        //     String fen = board.getFen();
        //     String[] pieces_only = fen.split(" ");
        //     String setup = pieces_only[0];
        //     // score
        //     System.out.println(score.evaluateScore(setup));
        // }

        // ArrayList total = new ArrayList<>();
        // int haha = 0;
        // while(haha < 50){
        Board board = new Board();
        int counter = 0;
        while(!board.isDraw() && !board.isStaleMate()) {
            System.out.println("-------------------------");
            int scoreBoard = score.evaluateScore(board.getFen());
            System.out.println("Evaluation: " + scoreBoard);
            System.out.println(board);

            if (counter % 2 == 0) {
                // optimizedMove bestMove = findBestMove(board, 3);
                optimizedMove bestMove = new optimizedMove();
                String bestmove = bestMove.findBestMove(board, 3);
                System.out.println("The AI optimal choice movement: " + bestmove);
                board.doMove(bestmove);
            } else {
                if (board.isKingAttacked()) {
                    System.out.println("The game has finished in: "+counter+" moves.");
                    // total.add(counter);
                    break;
                }
                else if(board.isDraw()){
                    System.out.println("The game has ended in a draw in: "+counter+" moves.");
                    // total.add(counter);
                    break;
                }
                List<Move> legalMoves = board.legalMoves();
                System.out.println("Legal moves for this turn: " + legalMoves);
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter move: ");
                String userInput = scanner.nextLine().toString();
                
                Main mainInstance = new Main();
                while (!mainInstance.containsMove(legalMoves, userInput)) {
                    System.out.print("Please choose a new move: ");
                    userInput = scanner.nextLine().toString();
                }
                
                board.doMove(userInput);
            }

            counter++;
        // }
        // haha++;
    }
    // System.out.println("You just played "+haha+" games of chess.");
    // Main main = new Main();
    // System.out.println(total);
    // System.out.println("The average moves per game was: "+main.calculateAverage(total));

    }
}
