package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ModCreativeTab extends CreativeTabs
{
    public static final CreativeTabs TAB = new ModCreativeTab();

    public ModCreativeTab()
    {
        super(InterviewMod.ID);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModItems.CHARGE);
    }
}
