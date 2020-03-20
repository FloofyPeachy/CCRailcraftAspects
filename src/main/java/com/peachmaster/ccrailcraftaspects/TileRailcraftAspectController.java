package com.peachmaster.ccrailcraftaspects;


import cpw.mods.fml.relauncher.ReflectionHelper;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import mods.railcraft.api.core.WorldCoordinate;
import mods.railcraft.api.signals.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class TileRailcraftAspectController extends TileEntity implements IPeripheral, IControllerTile {

    public ArrayList<IComputerAccess> computers = new ArrayList<IComputerAccess>();
    public SimpleSignalController theSignalController = new SimpleSignalController("Railcraft Aspect Controller", this);
    public SignalAspect currentAspect;

    @Override
    public String getType() {
        return "railcraft_aspect_controller";
    }

    @Override
    public String[] getMethodNames() {
        return new String[]{"setAspect"};
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
        switch(method) {
            case 0: {
                if (arguments[0].toString().equals("green") || arguments[0].toString().equals("1") ) {
                    theSignalController.setAspect(SignalAspect.GREEN);
                    currentAspect = SignalAspect.GREEN;
                }
                if (arguments[0].toString().equals("blink_yellow") || arguments[0].toString().equals("2")) {
                    theSignalController.setAspect(SignalAspect.BLINK_YELLOW);
                    currentAspect = SignalAspect.BLINK_YELLOW;
                }
                if (arguments[0].toString().equals("yellow")|| arguments[0].toString().equals("3")) {
                    theSignalController.setAspect(SignalAspect.YELLOW);
                    currentAspect = SignalAspect.YELLOW;
                }
                if (arguments[0].toString().equals("red") || arguments[0].toString().equals("4")) {
                    theSignalController.setAspect(SignalAspect.RED);
                    currentAspect = SignalAspect.RED;
                }
                if (arguments[0].toString().equals("off") || arguments[0].toString().equals("5")) {
                    theSignalController.setAspect(SignalAspect.OFF);
                    currentAspect = SignalAspect.OFF;
                }



            } default:
                return new Object[] {"nil"};
        }
    }

    @Override
    public void attach(IComputerAccess computer) {
        computers.add(computer);
    }

    @Override
    public void detach(IComputerAccess computer) {
        computers.remove(computer);
    }

    @Override
    public boolean equals(IPeripheral other) {
        return false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        //   System.out.println(theSignalController.isPaired());
        if (theSignalController.getAspect() == SignalAspect.BLINK_RED) {
            theSignalController.setAspect(SignalAspect.OFF);
        }

        }




    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        theSignalController.readFromNBT(nbt);
    }


    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        theSignalController.writeToNBT(nbttagcompound);

    }



    @Override
    public SignalController getController() {
        return theSignalController;
    }




}
