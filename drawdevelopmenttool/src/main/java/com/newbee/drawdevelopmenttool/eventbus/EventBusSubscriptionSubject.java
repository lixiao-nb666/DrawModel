package com.newbee.drawdevelopmenttool.eventbus;

import java.util.ArrayList;
import java.util.List;

public class EventBusSubscriptionSubject implements EventBusSubject {

    private static EventBusSubscriptionSubject subscriptionSubject;
    private List<EventBusObserver> observerList = new ArrayList<>();

    private EventBusSubscriptionSubject() {

    }

    public static EventBusSubscriptionSubject getInstance() {
        if (null == subscriptionSubject) {
            synchronized (EventBusSubscriptionSubject.class) {
                if (null == subscriptionSubject) {
                    subscriptionSubject = new EventBusSubscriptionSubject();
                }
            }
        }
        return subscriptionSubject;
    }

    public void close() {
        observerList.clear();
    }


    @Override
    public void addObserver(EventBusObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void delectObjserver(EventBusObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void eventListen(EventType eventType, Object... objects) {
        for (EventBusObserver observer : observerList) {
            observer.eventListen(eventType, objects);
        }
    }


}
