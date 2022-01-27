package darkguardsman.interview;

import darkguardsman.interview.content.prefab.IGuiTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 1/27/2022.
 */
public class CommonProxy implements IGuiHandler
{
    public void preInit()
    {

    }

    public void init()
    {

    }

    public void postInit()
    {

    }

    @Nullable
    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof IGuiTile) {
            return ((IGuiTile) tileEntity).getServerGuiElement(i, entityPlayer);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if(tileEntity instanceof IGuiTile) {
            return ((IGuiTile) tileEntity).getClientGuiElement(i, entityPlayer);
        }
        return null;
    }
}
