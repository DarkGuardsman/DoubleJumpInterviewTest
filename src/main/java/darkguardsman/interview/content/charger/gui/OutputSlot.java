package darkguardsman.interview.content.charger.gui;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class OutputSlot extends SlotItemHandler
{
    public OutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
    {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack p_39553_) {
        return false;
    }
}
