package org.arcticsoft.bluebearlive.core.aLogic;

import com.mrheadshot62.api.MultiPacket;

import org.arcticsoft.bluebearlive.core.iLogic.IPacketController;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public abstract class APacketController implements IPacketController {

    @Override
    public int sendPackets(MultiPacket multiPacket) {
        return 0;
    }
}
