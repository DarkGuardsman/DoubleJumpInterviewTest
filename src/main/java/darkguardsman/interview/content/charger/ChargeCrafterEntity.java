package darkguardsman.interview.content.charger;

import darkguardsman.interview.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ChargeCrafterEntity extends BlockEntity
{
    private static final String NBT_INVENTORY = "Inventory";

    private final ItemStackHandler itemHandler = new ChargeItemHandler(this);
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public ChargeCrafterEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CHARGER.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains(NBT_INVENTORY)) {
            itemHandler.deserializeNBT(tag.getCompound(NBT_INVENTORY));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put(NBT_INVENTORY, itemHandler.serializeNBT());
    }
}
