package com.newbee.drawdevelopmenttool.eventbus;

public interface EventBusObserver {

    public void eventListen(EventType eventType, Object... objects);
}
