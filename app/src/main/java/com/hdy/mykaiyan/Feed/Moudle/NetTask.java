package com.hdy.mykaiyan.Feed.Moudle;

/**
 * 作者：By hdy
 * 日期：On 2017/10/14
 * 时间：At 22:00
 */

public interface NetTask<T> {
    void execute(T data,LoadCallback callback);
}
