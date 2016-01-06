package com.sen.presenter.imp;

import com.sen.iview.ILoginView;
import com.sen.model.LoginData;
import com.sen.service.LoginService;
import com.sen.utils.IScheduler;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Sen on 2016/1/5.
 */
public class LoginPersenter implements ILoginPersenter<LoginData> {
    private Subscription mSubscription = Subscriptions.empty();
    private IScheduler mISchedule;
    private LoginService  mLoginService;
    private ILoginView<LoginData> view;

    public LoginPersenter(IScheduler iScheduler, LoginService loginService){
        mISchedule = iScheduler;
        mLoginService = loginService;
    }
    @Override
    public void start() {
        mSubscription = getObserable().subscribe(getSubscriber());
    }

    private Subscriber<? super LoginData> getSubscriber() {
        return new Subscriber<LoginData>() {

            @Override
            public void onStart() {
                super.onStart();
                view.setLoading(true);


            }

            @Override
            public void onCompleted() {
                view.setLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                view.setLoading(false);
                view.error(e);
            }

            @Override
            public void onNext(LoginData baiDuData) {
                view.setModel(baiDuData);
            }
        };
    }

    private Observable<LoginData> getObserable() {

        return mLoginService.getData(view.getUserName(),view.getPassword())
                .subscribeOn(mISchedule.backgroundThread())
                .observeOn(mISchedule.mainThread());
    }


    @Override
    public void finish() {
        if (mSubscription.isUnsubscribed())
        mSubscription.unsubscribe();
    }


    @Override
    public void setView(ILoginView<LoginData> view) {
        this.view = view;
    }
}
