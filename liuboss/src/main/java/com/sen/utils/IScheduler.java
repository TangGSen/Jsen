package com.sen.utils;

import rx.Scheduler;

/**
 * Created by Sen on 2015/12/21.
 */
public interface IScheduler {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
