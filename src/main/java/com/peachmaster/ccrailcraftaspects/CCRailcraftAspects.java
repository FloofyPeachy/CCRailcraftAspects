package com.peachmaster.ccrailcraftaspects;

import com.sun.xml.internal.ws.assembler.MetroTubelineAssembler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRail;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Mod(modid = CCRailcraftAspects.MODID, name = CCRailcraftAspects.NAME, version = CCRailcraftAspects.VERSION, dependencies = "required-after:Railcraft;required-after:ComputerCraft")
public class CCRailcraftAspects
{
    public static final String MODID = "CCRailcraftAspects";
    public static final String NAME = "CC Railcraft Aspects";
    public static final String VERSION = "1.0";

    public static BlockRailcraftAspectController railcraftAspectController;
    public static Logger ccraLog = LogManager.getLogger(NAME);
    
    @EventHandler
    public void init(FMLInitializationEvent event) {

        ccraLog.log(Level.INFO, "Starting up CC Railcraft Aspects! Powered by Bruh Momentus Technology!");
        railcraftAspectController   = new BlockRailcraftAspectController(Material.anvil);

        GameRegistry.addShapedRecipe(new ItemStack(railcraftAspectController, 1),  "III",
                "IRI",
                'I', Items.iron_ingot,
                'R', Items.redstone);

        Class computerCraft = null;
        try {
            computerCraft = Class.forName("dan200.computercraft.ComputerCraft");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Method computerCraft_registerPeripheralProvider = computerCraft.getMethod("registerPeripheralProvider", new Class[] { Class.forName("dan200.computercraft.api.peripheral.IPeripheralProvider") });

            //Register all CC required blocks
            computerCraft_registerPeripheralProvider.invoke(null, BlockRailcraftAspectController.instance);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


       // PacketHandler.init();

    }
}
