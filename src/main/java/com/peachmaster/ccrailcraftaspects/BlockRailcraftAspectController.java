package com.peachmaster.ccrailcraftaspects;

import cpw.mods.fml.common.registry.GameRegistry;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRailcraftAspectController extends BlockContainer implements IPeripheralProvider {

    public static BlockRailcraftAspectController instance;
    public BlockRailcraftAspectController(Material p_i45386_1_) {
        super(p_i45386_1_);
        instance = this;
        GameRegistry.registerBlock(this, "Railcraft Aspect Controller");
        GameRegistry.registerTileEntity(TileRailcraftAspectController.class, "rcac");
        setBlockName("Railcraft Aspect Controller");
       setBlockTextureName(CCRailcraftAspects.MODID + ":railcraft_aspect_controller" );
        setCreativeTab(CreativeTabs.tabTransport);
    }
    @Override
    public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        return (IPeripheral)tileEntity;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileRailcraftAspectController();
    }
}
