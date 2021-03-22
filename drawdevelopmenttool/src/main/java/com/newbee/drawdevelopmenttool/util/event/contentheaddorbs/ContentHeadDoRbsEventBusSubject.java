package com.newbee.drawdevelopmenttool.util.event.contentheaddorbs;


import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusObserver;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventType;

public interface ContentHeadDoRbsEventBusSubject {

    public void addObserver(ContentHeadDoRbsEventBusObserver observer);

    public void delectObjserver(ContentHeadDoRbsEventBusObserver observer);

    public void eventListen(ContentHeadDoRbsEventType eventType, Object... objects);

}
