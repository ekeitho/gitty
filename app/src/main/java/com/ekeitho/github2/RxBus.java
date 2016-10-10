package com.ekeitho.github2;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxBus {

    private final Subject<Object, Object> mBusSubject = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        mBusSubject.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return mBusSubject;
    }
}