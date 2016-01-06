package com.sen.service;

import com.sen.model.LoginData;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Sen on 2016/1/5.
 */
public class LoginService {
    public Observable<LoginData> getData(final String userName, final String password) {
        return Observable.create(new Observable.OnSubscribe<LoginData>() {
            @Override
            public void call(Subscriber<? super LoginData> subscriber) {
                //Simulate some latency
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                LoginData loginData = new LoginData();
                loginData.setUserName(userName);
                loginData.setPassword(password);

                subscriber.onNext(loginData);
                subscriber.onCompleted();
            }
        });
    }
}
