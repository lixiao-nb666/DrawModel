package com.newbee.drawdevelopmenttool.util.event;

public interface DialogEventBusSubject {

    public void addObserver(DialogEventBusObserver observer);

    public void delectObjserver(DialogEventBusObserver observer);

    public void eventListen(DialogEventType eventType, Object... objects);

}
