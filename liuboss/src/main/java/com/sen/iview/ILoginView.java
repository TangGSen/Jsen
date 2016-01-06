package com.sen.iview;

/**
 * Created by Sen on 2016/1/5.
 */
public interface ILoginView<T> extends IView<T> {
    String getUserName();

    String getPassword();
}
