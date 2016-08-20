package com.cs32191617.kwic.pubsub;

/**
 * Created by zixian on 8/20/16.
 *
 * Defines the subscriber behaviour in the pub-sub model.
 */
public interface Subscriber {
    public void handleAction(Action action);
}
