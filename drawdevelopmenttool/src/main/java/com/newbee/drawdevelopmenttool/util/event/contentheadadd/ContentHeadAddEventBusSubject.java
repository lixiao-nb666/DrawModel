package com.newbee.drawdevelopmenttool.util.event.contentheadadd;



public interface ContentHeadAddEventBusSubject {

    public void addObserver(ContentHeadAddEventBusObserver observer);

    public void delectObjserver(ContentHeadAddEventBusObserver observer);

    public void eventListen(ContentHeadAddEventType eventType, Object... objects);

}
