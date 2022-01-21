package darkguardsman.interview.content.charger;


import darkguardsman.interview.content.ModItems;
import lombok.AllArgsConstructor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
@AllArgsConstructor
public class ChargeItemHandler extends ItemStackHandler
{
    public static final int SLOT_CHARGE_ITEM = 0;
    public static final int SLOT_FUEL_ITEM = 1;

    private final ChargeCrafterEntity host;

    @Override
    protected void onContentsChanged(int slot) {
        host.setChanged();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if(slot == SLOT_CHARGE_ITEM) {
            return stack.getItem() == ModItems.CHARGE.get();
        } else if(slot == SLOT_FUEL_ITEM) {
            return ChargeFuelReg.isFuel(stack.getItem());
        }
        return false;
    }
}
