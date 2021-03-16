package com.newbee.drawdevelopmenttool.util.event;

import java.util.ArrayList;
import java.util.List;

public class DialogEventBusSubscriptionSubject implements DialogEventBusSubject {

    private static DialogEventBusSubscriptionSubject subscriptionSubject;
    private List<DialogEventBusObserver> observerList = new ArrayList<>();

    private DialogEventBusSubscriptionSubject() {

    }

    public static DialogEventBusSubscriptionSubject getInstance() {
        if (null == subscriptionSubject) {
            synchronized (DialogEventBusSubscriptionSubject.class) {
                if (null == subscriptionSubject) {
                    subscriptionSubject = new DialogEventBusSubscriptionSubject();
                }
            }
        }
        return subscriptionSubject;
    }

    public void close() {
        observerList.clear();
    }


    @Override
    public void addObserver(DialogEventBusObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void delectObjserver(DialogEventBusObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void eventListen(DialogEventType eventType, Object... objects) {
        for (DialogEventBusObserver observer : observerList) {
            observer.eventListen(eventType, objects);
        }
    }


}
