package com.tanvi.paging;

import com.tanvi.paging.algos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class ProcessExecutor implements Callable<Double> {
    private static final Logger logger = LoggerFactory.getLogger(ProcessExecutor.class);

    private Process process;
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S");

    private MainMemory memory;
    private List<Page> pages = new ArrayList<>(10);
    public ProcessExecutor(Process process, PagingAlgoFactory.PagingAlgoTypes pagingAlgoType) {
        this.process = process;
        for (int i = 0; i < 10; i++) {
            pages.add(new Page(i));
        }
        memory = new MainMemory(new ArrayList<>(4), PagingAlgoFactory.getPagingAlgo(pagingAlgoType));
    }

    @Override
    public Double call() throws Exception {

        logger.info("{} ENTER Size: {} ServiceDuration: {}", process.getName(),process.getNumPageFetches(),process.getServiceDuration());

        // Reference a page every 100ms
        long serviceDurationMillis = TimeUnit.SECONDS.toMillis(process.getServiceDuration());
        long timeElapsed = 0;
        for (Integer pageNumber : process.getPagesToReference()) {
            boolean isInMemory = pages.get(pageNumber).isInMemory();
            Page evictedPage = memory.load(pages.get(pageNumber));

            logger.info("{} Page Referenced: {} InMemory: {} Evicted Page: {} ",
                    process.getName(), pageNumber, isInMemory, (evictedPage!=null ? evictedPage.getNumber() : -1) );
            Thread.sleep(100);
            timeElapsed += 100;
            if (timeElapsed >= serviceDurationMillis) {
                logger.debug("!!! Service duration reached for {} !!!", process.getName());
                break;
            }
        }

        logger.info("{} EXIT Size:{} ServiceDuration:{} ",
                process.getName(), process.getNumPageFetches(), process.getServiceDuration());

        return memory.getHitRatio();
    }
}
