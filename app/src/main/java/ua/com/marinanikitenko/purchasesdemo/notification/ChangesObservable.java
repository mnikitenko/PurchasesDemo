/*
 * Created by Marina .21/06/17
 * All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.notification;

import java.util.Observable;

import ua.com.marinanikitenko.purchasesdemo.model.User;


/**
 *
 * Helper for send notification what object was changed
 */

public class ChangesObservable extends Observable {

    private User user;

    public void notifyUserChanges(User mUser){
        ObservableItem mObservableItem = new ObservableItem();
        mObservableItem.setUser(mUser);
        setChanged();
        notifyObservers(mObservableItem);
    }

}
