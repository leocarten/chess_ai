package com.github.bhlangonijr;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class ParaPrimes {


    public boolean isPrime(int number){
        int half = (int)Math.ceil(number/2);
        for(int i = 2; i <= half; i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public void findPrimesInRange(int start, int stop, Queue<Integer> primeQueue){
        for(int num = start; num < stop; num++){
            if(isPrime(num) && num != 1){
                primeQueue.add(num);
            }
        }
    }

    public void getPrimes(int total, int amountOfCores){
        Queue<Integer> primes = new ConcurrentLinkedQueue<>();
        // ArrayList primes = new ArrayList<>();
        List<Thread> threadList = new ArrayList<>();

        int chunk = total / amountOfCores;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < amountOfCores; i++) {
            int start = i * chunk + 1;
                        int end;
            if (i == amountOfCores - 1) {
                end = total;
            } else {
                end = (i + 1) * chunk;
            }
        
            Thread thread = new Thread(() -> findPrimesInRange(start, end, primes));
            threadList.add(thread);
        }

        for (Thread thread_ : threadList) {
            try {
                thread_.start();
            } catch (IllegalThreadStateException e) {
                e.printStackTrace();
            }
        }
        
        for (Thread thread__ : threadList) {
            try {
                thread__.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("This parallel algorithm took "+elapsedTime+" milliseconds using "+amountOfCores+" cores.");
        // System.out.println(primes.toString());
    }


}

