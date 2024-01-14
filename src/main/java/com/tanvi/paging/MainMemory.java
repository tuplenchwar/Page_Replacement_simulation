package com.tanvi.paging;

import com.tanvi.paging.algos.PagingAlgo;
import java.util.ArrayList;

public class MainMemory {
    private ArrayList<Page> frames;
    private int MAIN_MEMORY_SIZE = 4;
    private PagingAlgo pagingAlgo;
    private int numPagesInMemory = 0;
    private int hit = 0;
    private int miss = 0;

    public MainMemory(ArrayList<Page> frames, PagingAlgo pagingAlgo) {
        this.frames = frames;
        this.pagingAlgo = pagingAlgo;
    }

    public Page load(Page page) {
        Page evictedPage = null;
        // Page already loaded
        if (page.isInMemory()) {
            pagingAlgo.referencePage(page);
            hit++;
            return null;
        }

        miss++;
        // Page not loaded but memory is full, evict something
        if (numPagesInMemory == MAIN_MEMORY_SIZE) {
            evictedPage = pagingAlgo.evictPage();
            evictedPage.setInMemory(false);
            numPagesInMemory--;
        }

        pagingAlgo.loadNewPage(page);
        page.setInMemory(true);

        numPagesInMemory++;
        return evictedPage;
    }

    public double getHitRatio() {

        return  (double)hit / (hit+miss);
    }
}
