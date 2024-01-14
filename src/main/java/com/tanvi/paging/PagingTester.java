package com.tanvi.paging;

import com.tanvi.paging.algos.PagingAlgoFactory.PagingAlgoTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class PagingTester {
    private static final Logger logger = LoggerFactory.getLogger(PagingTester.class);

    public static final int MAX_PROCESSES = 150;
    public static final int MAX_CONCURRENT_PROCESSES = 25; //To simulate only 25 processes running at a time
    public static final int NUM_RUNS_PER_ALGO = 5;

    public static void main(String[] args) {
        PagingTester pagingTester = new PagingTester();
        pagingTester.run();
    }

    private void run() {
        try {
            List<Process> jobQueue = new ArrayList<>();
            Map<PagingAlgoTypes, Double> hitPercentAcrossRuns = new HashMap<>(5);

            for (int i = 0; i < NUM_RUNS_PER_ALGO; i++) {
                jobQueue.clear();
                for (int j = 0; j < MAX_PROCESSES; j++) {
                    jobQueue.add(new Process(j + 1));
                }
                for (PagingAlgoTypes pagingAlgo : PagingAlgoTypes.values()) {
                    logger.info("*** Starting simulation for PagingAlgo:{} Num Processes:{} ***", pagingAlgo, MAX_PROCESSES);
                    ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENT_PROCESSES);
                    List<Future<Double>> futures = new ArrayList<>();

                    for (int j = 0; j < MAX_PROCESSES; j++) {
                        futures.add(executorService.submit(new ProcessExecutor(jobQueue.get(j), pagingAlgo)));
                    }

                    double hitPercentage = 0.0;
                    for (Future<Double> future : futures) {
                        try {
                            hitPercentage += future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println("Exception: " + e);
                            e.printStackTrace();
                        }
                    }
                    hitPercentage = (hitPercentage / MAX_PROCESSES);
                    hitPercentAcrossRuns.put(pagingAlgo, hitPercentAcrossRuns.getOrDefault(pagingAlgo, 0.0) + hitPercentage);
                    executorService.shutdown();
                }
            }

            for (Map.Entry<PagingAlgoTypes, Double> pagingAlgoEntry : hitPercentAcrossRuns.entrySet()) {
                System.out.printf("\n*** Average hit ratio for " + pagingAlgoEntry.getKey() + ": %.2f", pagingAlgoEntry.getValue() / NUM_RUNS_PER_ALGO);
            }
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
    }
}
