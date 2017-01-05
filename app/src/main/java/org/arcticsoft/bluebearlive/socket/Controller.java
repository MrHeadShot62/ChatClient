package org.arcticsoft.bluebearlive.socket;

/**
 * Created by novak on 05.01.2017.
 */

public class Controller {
    public Controller() {
        new ClientThread("127.0.0.1").start();
    }
}
