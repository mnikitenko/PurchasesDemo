/*
 * Created by Marina 21/06/17
 * All rights reserved.
 *
 * Last Modification at: 26/06/17
 */
package ua.com.marinanikitenko.purchasesdemo.notification;




/*
 * Helper,where registered observable objects
 */

import ua.com.marinanikitenko.purchasesdemo.model.User;

public class ObservableItem {

    public User user;


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}