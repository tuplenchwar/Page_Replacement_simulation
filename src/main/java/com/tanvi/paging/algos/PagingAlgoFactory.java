package com.tanvi.paging.algos;

public class PagingAlgoFactory {
    public enum PagingAlgoTypes {
        FIFO,
        LRU,
        LFU,
        MFU,
        RANDOM_PICK
    }

    public static PagingAlgo getPagingAlgo(PagingAlgoTypes type) {
        switch (type) {
            case FIFO:
                return new FIFO();
            case LRU:
                return new LRU();
            case LFU:
                return new LFU();
            case MFU:
                return new MFU();
            case RANDOM_PICK:
                return new RandomPick();
            default:
                System.out.println("ERROR: Unknown Paging Algo");
                return null;
        }
    }

}
