package darkguardsman.interview.content.charger;


import darkguardsman.interview.api.ChargeFuelReg;
import darkguardsman.interview.content.reg.ItemReg;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class InventoryChargeCrafter extends ItemStackHandler
{
    public static final int SLOT_CHARGE_ITEM = 0;
    public static final int SLOT_FUEL_ITEM = 1;
    public static final int INVENTORY_SIZE = 2;

    private final TileEntityChargeCrafter host;

    public InventoryChargeCrafter(TileEntityChargeCrafter host)
    {
        super(INVENTORY_SIZE);
        this.host = host;
    }

    @Override
    public void setSize(int size)
    {
       //Empty to prevent resize
    }

    @Override
    protected void onContentsChanged(int slot)
    {
        host.markDirty();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack)
    {
        if (slot == SLOT_CHARGE_ITEM)
        {
            return stack.getItem() == ItemReg.charge;
        }
        else if (slot == SLOT_FUEL_ITEM)
        {
            return ChargeFuelReg.isFuel(stack.getItem());
        }
        return false;
    }

    public void dropAllItems(World world, BlockPos pos)
    {
        for(ItemStack stack : stacks) {
            if(!stack.isEmpty()) {
                final EntityItem entityItem = new EntityItem(world);
                entityItem.setItem(stack.copy());
                entityItem.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                world.spawnEntity(entityItem);
            }
        }
        stacks.clear();
    }
}
