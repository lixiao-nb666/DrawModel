package com.newbee.drawdevelopmenttool.util.draw.thread;

public interface SaveBitMapSubject {

    public void addObserver(SaveBitMapObserver observer);

    public void delectObjserver(SaveBitMapObserver observer);

    public void over(String fileName, String filePath);

}
