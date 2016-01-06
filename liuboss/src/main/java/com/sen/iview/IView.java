package com.sen.iview;

/**
 * Created by Sen on 2015/12/21.
 */
public interface IView<T> {
    void setLoading(boolean isLoading);

    void setModel(T object);

    void error(Throwable throwable);
}
