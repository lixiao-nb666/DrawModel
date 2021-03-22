package com.newbee.drawdevelopmenttool.eventbus;

public interface EventBusSubject {

    public void addObserver(EventBusObserver observer);

    public void delectObjserver(EventBusObserver observer);

    public void eventListen(EventType eventType, Object... objects);

}
