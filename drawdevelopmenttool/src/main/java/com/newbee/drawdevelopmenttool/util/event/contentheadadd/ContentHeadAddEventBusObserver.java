package com.newbee.drawdevelopmenttool.util.event.contentheadadd;



public interface ContentHeadAddEventBusObserver {

    public void eventListen(ContentHeadAddEventType eventType, Object... objects);
}
