package com.newbee.drawdevelopmenttool.util.event.contentheadadd;



import java.util.ArrayList;
import java.util.List;

public class ContentHeadAddEventBusSubscriptionSubject implements ContentHeadAddEventBusSubject {

    private static ContentHeadAddEventBusSubscriptionSubject subscriptionSubject;
    private List<ContentHeadAddEventBusObserver> observerList = new ArrayList<>();

    private ContentHeadAddEventBusSubscriptionSubject() {

    }

    public static ContentHeadAddEventBusSubscriptionSubject getInstance() {
        if (null == subscriptionSubject) {
            synchronized (ContentHeadAddEventBusSubscriptionSubject.class) {
                if (null == subscriptionSubject) {
                    subscriptionSubject = new ContentHeadAddEventBusSubscriptionSubject();
                }
            }
        }
        return subscriptionSubject;
    }

    public void close() {
        observerList.clear();
    }


    @Override
    public void addObserver(ContentHeadAddEventBusObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void delectObjserver(ContentHeadAddEventBusObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void eventListen(ContentHeadAddEventType eventType, Object... objects) {
        for (ContentHeadAddEventBusObserver observer : observerList) {
            observer.eventListen(eventType, objects);
        }
    }


}
