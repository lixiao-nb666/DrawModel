package com.newbee.drawdevelopmenttool.util.event.contentheaddorbs;



import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusObserver;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventBusSubject;
import com.newbee.drawdevelopmenttool.util.event.contentheadadd.ContentHeadAddEventType;

import java.util.ArrayList;
import java.util.List;

public class ContentHeadDoRbsEventBusSubscriptionSubject implements ContentHeadDoRbsEventBusSubject {

    private static ContentHeadDoRbsEventBusSubscriptionSubject subscriptionSubject;
    private List<ContentHeadDoRbsEventBusObserver> observerList = new ArrayList<>();

    private ContentHeadDoRbsEventBusSubscriptionSubject() {

    }

    public static ContentHeadDoRbsEventBusSubscriptionSubject getInstance() {
        if (null == subscriptionSubject) {
            synchronized (ContentHeadDoRbsEventBusSubscriptionSubject.class) {
                if (null == subscriptionSubject) {
                    subscriptionSubject = new ContentHeadDoRbsEventBusSubscriptionSubject();
                }
            }
        }
        return subscriptionSubject;
    }

    public void close() {
        observerList.clear();
    }


    @Override
    public void addObserver(ContentHeadDoRbsEventBusObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void delectObjserver(ContentHeadDoRbsEventBusObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void eventListen(ContentHeadDoRbsEventType eventType, Object... objects) {
        for (ContentHeadDoRbsEventBusObserver observer : observerList) {
            observer.eventListen(eventType, objects);
        }
    }


}
