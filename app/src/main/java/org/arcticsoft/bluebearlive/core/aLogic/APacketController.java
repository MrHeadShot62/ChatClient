package org.arcticsoft.bluebearlive.core.aLogic;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;

import org.arcticsoft.bluebearlive.core.iLogic.IPacketController;
import org.arcticsoft.bluebearlive.core.logic.Login;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public abstract class APacketController implements IPacketController {
    @Override
    public int checkType(Packet packet) {
        switch (packet.getType()){
            case Types.AUTH:
//                packet.getData().
//                new Login().startLogin();
        }
        return 0;
    }
}
