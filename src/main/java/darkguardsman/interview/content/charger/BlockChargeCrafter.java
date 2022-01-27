package darkguardsman.interview.content.charger;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class BlockChargeCrafter extends BlockContainer
{
    public BlockChargeCrafter()
    {
        super(Material.ROCK);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState blockState) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState,
                                    EntityPlayer player_, EnumHand hand, EnumFacing side,
                                    float faceX, float faceY, float faceZ) {
        if (!world.isRemote)
        {
            final TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntityChargeCrafter)
            {
               //TODO open inventory
            }

        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityChargeCrafter();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockState) {
        final TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileEntityChargeCrafter) {
            ((TileEntityChargeCrafter) tile).inventory.dropAllItems(world, pos);
        }
        super.breakBlock(world, pos, blockState);
    }
}
