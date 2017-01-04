package org.arcticsoft.bluebearlive.core.iLogic;

/**
 * Created by DmitriyRoot on 04.01.2017.
 */

public interface IDataBase {

    boolean isConnected();

    void sendData();

    void loadData();
}
