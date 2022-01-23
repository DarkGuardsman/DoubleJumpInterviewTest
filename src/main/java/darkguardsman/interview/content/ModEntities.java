package darkguardsman.interview.content;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.ghoul.Ghoul;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Created by Robin Seifert on 1/22/2022.
 */
public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, InterviewMod.ID);

    public static final RegistryObject<EntityType<Ghoul>> GHOUL = ENTITIES.register("ghoul",
            () -> EntityType.Builder.<Ghoul>of(Ghoul::new, MobCategory.MONSTER)
                    .sized(0.8F, 1.8F)
                    .clientTrackingRange(64)
                    .build("ghoul"));

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GHOUL.get(), Ghoul.createAttributes().build());
    }
}
