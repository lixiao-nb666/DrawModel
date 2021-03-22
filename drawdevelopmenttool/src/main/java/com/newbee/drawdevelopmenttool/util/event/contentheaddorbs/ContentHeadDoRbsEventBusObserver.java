package com.newbee.drawdevelopmenttool.util.event.contentheaddorbs;




public interface ContentHeadDoRbsEventBusObserver {

    public void eventListen(ContentHeadDoRbsEventType eventType, Object... objects);
}
