package com.tanvi.paging.algos;

import com.tanvi.paging.Page;

public interface PagingAlgo {
    void referencePage(Page page);

    Page evictPage();

    void loadNewPage(Page page);

}
