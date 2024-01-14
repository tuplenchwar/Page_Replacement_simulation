package com.tanvi.paging.algos;

import com.tanvi.paging.Page;

import java.util.Map;

public class LFU extends FrequencyBasedPaging {
    @Override
    public Page evictPage() {
        Integer leastFrequency = Integer.MAX_VALUE;
        Page pageToEvict = null;
        for (Map.Entry<Page, Integer> pageUsageEntry : pageUsage.entrySet()) {
            Page page = pageUsageEntry.getKey();
            Integer frequency = pageUsageEntry.getValue();
            if(frequency < leastFrequency) {
                leastFrequency = frequency;
                pageToEvict = page;
            }
        }

        pageUsage.remove(pageToEvict);
        return pageToEvict;
    }
}
