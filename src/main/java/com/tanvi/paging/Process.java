package com.tanvi.paging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

public class Process {

    private int pid;
    private String name;
    private int arrivalTime;
    private int numPageFetches;
    private int serviceDuration;
    private ArrayList<Integer> frames;
    //private List<Page> pages = new ArrayList<>(10);
    private List<Integer> pagesToReference;

    private Map<String, Map<Integer, Integer>> pageTable = new HashMap<>();

    // For logging
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");

    public Process(int pid) {
        Random random = new Random();
        this.pid = pid;
        this.name = "Process" + pid;
        this.arrivalTime = 0;
        //this.numPageFetches = 31;
        this.numPageFetches = Arrays.asList(5, 7, 11, 31).get(random.nextInt(4));
        this.serviceDuration = random.nextInt(10)+1;

        pagesToReference = new ArrayList<>(numPageFetches);
        int currentPage = 0;
        for (int i = 0; i < numPageFetches; i++) {
            currentPage = (currentPage + getRandomDelta()) % 10;
            if(currentPage == -1)
                currentPage = 9;
            if(currentPage == -2)
                currentPage = 8;
            pagesToReference.add(currentPage);
        }
    }

    private int getRandomDelta() {
        Random rand = new Random();
        int r = rand.nextInt(10);
        if (r < 7) {
            return rand.nextInt(3) - 1;
        } else {
            return rand.nextInt(8) - 2;
        }
    }

    public void allocateFrames(ArrayList<Integer> frames) {
        this.frames = frames;
    }


    public int getServiceDuration() {
        return serviceDuration;
    }

    public List<Integer> getPagesToReference() {
        return pagesToReference;
    }

    public String getName() {
        return name;
    }

    public int getNumPageFetches() {
        return numPageFetches;
    }

}
