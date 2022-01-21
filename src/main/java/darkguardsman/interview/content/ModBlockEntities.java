package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.ChargeCrafterEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, InterviewMod.ID);

    public static final RegistryObject<BlockEntityType<ChargeCrafterEntity>> CHARGER = BLOCK_ENTITIES.register("charger",
            () -> BlockEntityType.Builder.of(ChargeCrafterEntity::new, ModBlocks.CHARGER.get()).build(null));
}
