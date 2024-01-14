package com.tanvi.paging.algos;

import com.tanvi.paging.Page;

import java.util.HashMap;
import java.util.Map;

public abstract class FrequencyBasedPaging implements PagingAlgo{
    Map<Page, Integer> pageUsage = new HashMap<>();

    @Override
    public void referencePage(Page page) {
        pageUsage.put(page, pageUsage.get(page)+1);
    }

    @Override
    public abstract Page evictPage();

    @Override
    public void loadNewPage(Page page) {
        pageUsage.put(page, 1);
    }
}
