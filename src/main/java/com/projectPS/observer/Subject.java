package com.projectPS.observer;


public interface Subject {
    void subscribe(RecipeObserver o);
    void unsubscribe(RecipeObserver o);
    void notifySubscribers();
}
