package com.tanvi.paging.algos;

import com.tanvi.paging.Page;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements PagingAlgo {
    private Queue<Page> queue = new LinkedList<>();

    @Override
    public void loadNewPage(Page page) {
        queue.add(page);
    }

    @Override
    public void referencePage(Page page) {
        // In this algo there is no action to be taken if a page that is already in memory is referenced again
        // because eviction is always FIFO based.
    }

    public Page evictPage() {
        return queue.remove();
    }

}
