package com.github;
import com.github.bhlangonijr.ParaPrimes;
import com.github.bhlangonijr.SeqPrimes;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Cores;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Main {
    public static void main(String[] args) {
        // Get the amount of cores on this Machine
        Cores cores = new Cores();
        int amountOfCores = cores.getNumCores();
        System.out.println("This machine has: "+amountOfCores+" cores.");

        // View the speed using single core using Prime number algorithms.
        SeqPrimes seqPrimes = new SeqPrimes();
        // seqPrimes.getPrimes(200000);

        // View the speed using multiple core using Prime number algorithms.
        ParaPrimes paraPrimes = new ParaPrimes();
        // paraPrimes.getPrimes(200000, amountOfCores);

        /*
         === FOOD FOR THOUGHT ===
         * We will need to think about how many cores we want to use per iteration.
         * Speed-up only really begins to occur when we have >10,000 iterations...
         */


        Board board = new Board();
        int counter = 0;
        String break_up = "===============================";
        Score score = new Score();
        if(!board.isStaleMate() && !board.isDraw()){ // might need to change this logic
            System.out.println(break_up);
            String fen = board.getFen();
            String[] pieces_only = fen.split(" ");
            String setup = pieces_only[0];
            // score
            System.out.println(score.evaluateScore(setup));
        }


    }
}
