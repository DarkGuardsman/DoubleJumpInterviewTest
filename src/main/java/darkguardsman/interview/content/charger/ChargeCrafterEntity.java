package darkguardsman.interview.content.charger;

import darkguardsman.interview.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ChargeCrafterEntity extends BlockEntity
{
    private static final String NBT_INVENTORY = "Inventory";

    public final ItemStackHandler inventory = new ChargeItemHandler(this); //intentionally not exposed via cap system

    public ChargeCrafterEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.CHARGER.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains(NBT_INVENTORY)) {
            inventory.deserializeNBT(tag.getCompound(NBT_INVENTORY));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put(NBT_INVENTORY, inventory.serializeNBT());
    }
}
