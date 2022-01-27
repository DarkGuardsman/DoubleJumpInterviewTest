package darkguardsman.interview.content.charger;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ItemCharge extends Item
{
    public static final int MAX_CHARGE = 200;
    public static final String NBT_CHARGE = "Charge";

    public void setCharge(ItemStack stack, int amount) //Optionally could be a capability if cross mod support is desired
    {
        stack.getOrCreateTag().putInt(NBT_CHARGE, Math.max(0, amount));
    }

    public int getCharge(ItemStack stack) {
        if(stack.getTag() != null) {
            return stack.getTag().getInt(NBT_CHARGE);
        }
        return 0;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getCharge(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return (int)Math.floor(getCharge(stack) * 13.0 / MAX_CHARGE);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        final float f = Math.max(0.0F, (MAX_CHARGE - stack.getDamageValue()) / (float)MAX_CHARGE);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
        if (this.allowdedIn(tab)) {
            items.add(new ItemStack(this));

            final ItemStack stack = new ItemStack(this);
            setCharge(stack, MAX_CHARGE);
            items.add(stack);
        }
    }
}
