package com.sen.presenter.imp;

import com.sen.iview.ILoginView;
import com.sen.presenter.IPresenter;

/**
 * Created by Sen on 2016/1/5.
 */
public interface ILoginPersenter<T> extends IPresenter<T> {
    void setView(ILoginView<T> view);
}
