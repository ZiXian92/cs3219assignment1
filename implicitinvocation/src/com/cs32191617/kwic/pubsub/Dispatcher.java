package com.cs32191617.kwic.pubsub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zixian on 8/20/16.
 *
 * Defines the dispatcher/publisher in the pub-sub model.
 */
public class Dispatcher {
    public static Dispatcher globalInstance = new Dispatcher();

    private Map<ActionTypes, List<Subscriber>> subscribers;

    private Dispatcher(){
        this.subscribers = new TreeMap<ActionTypes, List<Subscriber>>();
    }

    public static Dispatcher getInstance(){ return globalInstance; }

    /**
     * Sets the given subscriber to listen for actions of the given action type.
     * If the subscriber is listening to the same action type already, this does not lead to duplicate listening.
     * @param actionType The action type to listen to
     * @param subscriber The action handler
     */
    public void subscribeTo(ActionTypes actionType, Subscriber subscriber){
        if(!this.subscribers.containsKey(actionType)) this.subscribers.put(actionType, new ArrayList<Subscriber>());
        if(!this.subscribers.get(actionType).contains(subscriber)) this.subscribers.get(actionType).add(subscriber);
    }

    /**
     * Sets the given subscriber to unlisten on events of the given action type.
     * @param actionType  The action type to unlisten
     * @param subscriber The action handler to be removed
     */
    public void unsubscribeFrom(ActionTypes actionType, Subscriber subscriber){
        if(this.subscribers.containsKey(actionType)) this.subscribers.get(actionType).remove(subscriber);
    }

    /**
     * Dispatches an action by broadcasting to all listeners of the action's action type.
     * @param action The action to be dispatched
     */
    public void dispatch(Action action){
        if(action==null) return;
        List<Subscriber> listeners = this.subscribers.get(action.getActionType());
        if(listeners!=null) for(Subscriber s: listeners) s.handleAction(action);
    }
}
