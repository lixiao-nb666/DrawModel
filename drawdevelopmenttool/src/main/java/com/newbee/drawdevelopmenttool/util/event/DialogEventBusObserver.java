package com.newbee.drawdevelopmenttool.util.event;



public interface DialogEventBusObserver {

    public void eventListen(DialogEventType eventType, Object... objects);
}
