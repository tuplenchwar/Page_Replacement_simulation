package com.tanvi.paging;

public class Page {
    private int number;
    private boolean inMemory;

    public Page(int number) {
        this(number, false);
    }

    public Page(int number, boolean inMemory) {
        this.number = number;
        this.inMemory = inMemory;
    }

    public boolean isInMemory() {
        return inMemory;
    }

    public void setInMemory(boolean inMemory) {
        this.inMemory = inMemory;
    }

    public int getNumber() {
        return number;
    }
}
