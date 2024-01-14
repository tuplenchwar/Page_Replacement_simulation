package com.tanvi.paging.algos;
import com.tanvi.paging.Page;

import java.util.HashMap;
import java.util.Map;

public class LRU implements PagingAlgo {
    private Map<Page, Long> pageUsage = new HashMap<>();

    @Override
    public void referencePage(Page page) {
        pageUsage.put(page, System.currentTimeMillis());
    }

    public Page evictPage() {
        Long oldest = Long.MAX_VALUE;
        Page pageToEvict = null;
        for (Map.Entry<Page, Long> pageUsageEntry : pageUsage.entrySet()) {
            Page page = pageUsageEntry.getKey();
            Long lastReferenced = pageUsageEntry.getValue();
            if(lastReferenced < oldest) {
                oldest = lastReferenced;
                pageToEvict = page;
            }
        }
        pageUsage.remove(pageToEvict);
        return pageToEvict;
    }

    @Override
    public void loadNewPage(Page page) {
        referencePage(page);
    }
}
