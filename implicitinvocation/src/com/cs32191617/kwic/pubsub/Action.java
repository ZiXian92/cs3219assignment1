package com.cs32191617.kwic.pubsub;

/**
 * Created by zixian on 8/20/16.
 *
 * Defines the action object representing the task that needs to be done.
 */
public class Action<T> {
    private ActionTypes actionType;
    private T data;

    public Action(ActionTypes actionType, T data){
        this.actionType = actionType;
        this.data = data;
    }

    public ActionTypes getActionType(){ return this.actionType; }
    public T getData(){ return this.data; }
}
