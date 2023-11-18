package com.github.bhlangonijr.chesslib;

public class Cores {
    public int getNumCores(){
        return Runtime.getRuntime().availableProcessors();
    }
}
