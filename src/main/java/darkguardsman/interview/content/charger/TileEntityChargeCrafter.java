package darkguardsman.interview.content.charger;

import darkguardsman.interview.content.charger.gui.ContainerChargeCrafter;
import darkguardsman.interview.content.charger.gui.GuiChargeCrafter;
import darkguardsman.interview.content.prefab.IGuiTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class TileEntityChargeCrafter extends TileEntity implements IGuiTile {
    private static final String NBT_INVENTORY = "Inventory";

    public final InventoryChargeCrafter inventory = new InventoryChargeCrafter(this); //intentionally not exposed via cap system

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey(NBT_INVENTORY, 10)) {
            inventory.deserializeNBT(tag.getCompoundTag(NBT_INVENTORY));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setTag(NBT_INVENTORY, inventory.serializeNBT());
        return super.writeToNBT(tag);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player) {
        return new ContainerChargeCrafter(player, this);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player) {
        return new GuiChargeCrafter(player, this);
    }
}
