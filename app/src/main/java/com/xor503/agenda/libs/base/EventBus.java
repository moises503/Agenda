package com.xor503.agenda.libs.base;

/**
 * Created by xor503 on 10/13/16.
 */

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
