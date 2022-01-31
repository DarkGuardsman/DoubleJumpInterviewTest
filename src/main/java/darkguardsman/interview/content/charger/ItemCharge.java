package darkguardsman.interview.content.charger;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ItemCharge extends Item
{
    public static final int MAX_CHARGE = 200;
    public static final String NBT_CHARGE = "Charge";

    public void setCharge(ItemStack stack, int amount) //Optionally could be a capability if cross mod support is desired
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setInteger(NBT_CHARGE, Math.max(0, amount));
    }

    public int getCharge(ItemStack stack)
    {
        if (stack.hasTagCompound())
        {
            return stack.getTagCompound().getInteger(NBT_CHARGE);
        }
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> textList, ITooltipFlag flags)
    {
        double p = getDurabilityForDisplay(stack) * 100;
        textList.add(String.format("C: %s / %s (%s)", getCharge(stack), MAX_CHARGE, (int)p));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return getCharge(stack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        return 1 - ((double) getCharge(stack) / (double) MAX_CHARGE);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            items.add(new ItemStack(this));

            final ItemStack stack = new ItemStack(this);
            setCharge(stack, MAX_CHARGE);
            items.add(stack);
        }

    }
}
