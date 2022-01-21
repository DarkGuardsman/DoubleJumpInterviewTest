package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ModCreativeTab extends CreativeModeTab
{
    public static final CreativeModeTab TAB = new ModCreativeTab();

    public ModCreativeTab()
    {
        super(InterviewMod.ID);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(ModItems.CHARGE.get());
    }
}
