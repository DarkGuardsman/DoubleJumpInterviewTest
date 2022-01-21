package darkguardsman.interview.content.charger;

import darkguardsman.interview.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ChargeCrafterBlock extends BaseEntityBlock
{
    public ChargeCrafterBlock()
    {
        super(Properties.of(Material.STONE)
                .sound(SoundType.STONE)
                .strength(2.0f));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return ModBlockEntities.CHARGER.get().create(pos, state);
    }
}
