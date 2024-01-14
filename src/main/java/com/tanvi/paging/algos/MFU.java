package com.tanvi.paging.algos;

import com.tanvi.paging.Page;

import java.util.Map;

public class MFU extends FrequencyBasedPaging {
    @Override
    public Page evictPage() {
        Integer maxFrequency = Integer.MIN_VALUE;
        Page pageToEvict = null;
        for (Map.Entry<Page, Integer> pageUsageEntry : pageUsage.entrySet()) {
            Page page = pageUsageEntry.getKey();
            Integer frequency = pageUsageEntry.getValue();
            if(frequency > maxFrequency) {
                maxFrequency = frequency;
                pageToEvict = page;
            }
        }

        pageUsage.remove(pageToEvict);
        return pageToEvict;
    }
}
