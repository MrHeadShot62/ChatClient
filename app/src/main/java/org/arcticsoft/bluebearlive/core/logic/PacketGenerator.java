package org.arcticsoft.bluebearlive.core.logic;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.api.MultiPacket;

import org.arcticsoft.bluebearlive.core.aLogic.APacketController;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class PacketGenerator extends APacketController{

    public PacketGenerator(AuthPacket authPacket, PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket(new Packet(permissionPacket, Types.PERMISSION), new Packet(authPacket, Types.AUTH)));
    }

    public PacketGenerator(CommandPacket commandPacket, PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket( new Packet(permissionPacket, Types.PERMISSION), new Packet(commandPacket, Types.Command)));
    }

    public PacketGenerator(CompressendImagePacket compressendImagePacket, PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket(new Packet(permissionPacket, Types.PERMISSION), new Packet(compressendImagePacket, Types.Command)));
    }

    public PacketGenerator(ImagePacket imagePacket, PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket(new Packet(permissionPacket, Types.PERMISSION), new Packet(imagePacket, Types.Image)));
    }

    public PacketGenerator(PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket(new Packet(permissionPacket, Types.PERMISSION)));
    }

    public PacketGenerator(RegisterPacket registerPacket, PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket(new Packet(permissionPacket, Types.PERMISSION), new Packet(registerPacket, Types.REGISTER)));
    }

    public PacketGenerator(UserPacket userPacket, PermissionPacket permissionPacket) {
        sendPackets(new MultiPacket(new Packet(permissionPacket, Types.PERMISSION), new Packet(userPacket, Types.USER)));
    }

    @Override
    public int sendPackets(MultiPacket multiPacket) {
        return super.sendPackets(multiPacket);
    }
}
