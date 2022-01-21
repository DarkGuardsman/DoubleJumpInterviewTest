package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.ChargeCrafterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InterviewMod.ID);

    public static final RegistryObject<ChargeCrafterBlock> CHARGER = BLOCKS.register("charger", ChargeCrafterBlock::new);
}
