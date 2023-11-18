package com.github.bhlangonijr;
import java.util.ArrayList;

public class SeqPrimes {

    public boolean isPrime(int number){
        int half = (int)Math.ceil(number/2);
        for(int i = 2; i <= half; i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
    
    public ArrayList getPrimes(int total){
        ArrayList primes = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        int count = 2;
        while(count <= total){
            if(isPrime(count)){
                primes.add(count);
            }
            count++;
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("This sequential algorithm took "+elapsedTime+" milliseconds using 1 core.");
        // System.out.println(primes.toString());
        return primes;
    }



}
