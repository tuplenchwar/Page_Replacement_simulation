package com.tanvi.paging.algos;

import com.tanvi.paging.Page;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomPick implements PagingAlgo {
    private List<Page> pageList = new LinkedList<>();
    private Random random = new Random();

    @Override
    public void referencePage(Page page) {
        // In this algo there is no action to be taken if a page that is already in memory is referenced again
        // because eviction is random.
    }

    @Override
    public Page evictPage() {
        return pageList.remove(random.nextInt(pageList.size()));
    }

    @Override
    public void loadNewPage(Page page) {
        pageList.add(page);
    }
}
