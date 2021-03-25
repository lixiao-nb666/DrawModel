package com.newbee.drawdevelopmenttool.draw.save;

import java.util.ArrayList;
import java.util.List;

public class SaveBitMapSubscriptionSubject implements SaveBitMapSubject {

    private static SaveBitMapSubscriptionSubject subscriptionSubject;
    private List<SaveBitMapObserver> observerList = new ArrayList<>();

    private SaveBitMapSubscriptionSubject() {

    }

    public static SaveBitMapSubscriptionSubject getInstance() {
        if (null == subscriptionSubject) {
            synchronized (SaveBitMapSubscriptionSubject.class) {
                if (null == subscriptionSubject) {
                    subscriptionSubject = new SaveBitMapSubscriptionSubject();
                }
            }
        }
        return subscriptionSubject;
    }

    public void close() {
        observerList.clear();
    }


    @Override
    public void addObserver(SaveBitMapObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void delectObjserver(SaveBitMapObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void over(String fileName, String filePath) {
        for (SaveBitMapObserver observer : observerList) {
            observer.over(fileName,filePath);
        }
    }


}
