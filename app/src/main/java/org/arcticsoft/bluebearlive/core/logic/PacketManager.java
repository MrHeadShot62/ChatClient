package org.arcticsoft.bluebearlive.core.logic;

import com.mrheadshot62.api.Packet;
import com.mrheadshot62.api.Types;
import com.mrheadshot62.api.types.*;
import com.mrheadshot62.api.MultiPacket;

import org.arcticsoft.bluebearlive.core.aLogic.APacketController;
import org.arcticsoft.bluebearlive.socket.Controller;

/**
 * Created by DmitriyRoot on 05.01.2017.
 */

public class PacketManager{

    private static PacketManager instance = null;

    private PacketManager() {
    }

    public static synchronized PacketManager getInstance(){
        if(instance == null){
            instance = new PacketManager();
            return instance;
        }else {
            return instance;
        }
    }

    public static void PacketGenerator(User user, AuthPacket authPacket) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION), new Packet(authPacket, Types.AUTH)));
    }

    public static void PacketGenerator(User user, CommandPacket commandPacket) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION), new Packet(commandPacket, Types.Command)));
    }

    public static void PacketGenerator(User user, CompressendImagePacket compressendImagePacket) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION), new Packet(compressendImagePacket, Types.Command)));
    }

    public static void PacketGenerator(User user, ImagePacket imagePacket) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION), new Packet(imagePacket, Types.Image)));
    }

    public static void PacketGenerator(User user) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION)));
    }

    public static void PacketGenerator(User user, RegisterPacket registerPacket) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION), new Packet(registerPacket, Types.REGISTER)));
    }

    public static void PacketGenerator(User user, UserPacket userPacket) {
        sendPackets(new MultiPacket(new Packet(createPermissionPacket(user), Types.PERMISSION), new Packet(userPacket, Types.USER)));
    }

    private static PermissionPacket createPermissionPacket(User user){
        return new PermissionPacket(user.getSessionKey(), user.getPermissionLevel());
    }

    private static int sendPackets(MultiPacket multiPacket) {
        Controller.sendMultiPacket(multiPacket);
        return 0;
    }
}
