package darkguardsman.interview.content.charger.gui;

import darkguardsman.interview.api.ChargeFuelReg;
import darkguardsman.interview.content.charger.InventoryChargeCrafter;
import darkguardsman.interview.content.charger.ItemCharge;
import darkguardsman.interview.content.charger.TileEntityChargeCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ContainerChargeCrafter extends Container
{
    private final EntityPlayer player;
    private final TileEntityChargeCrafter chargeCrafter;

    protected final ItemStackHandler resultInv = new ItemStackHandler();

    public ContainerChargeCrafter(EntityPlayer player, TileEntityChargeCrafter chargeCrafter)
    {
        this.player = player;
        this.chargeCrafter = chargeCrafter;

        this.addSlotToContainer(new InputSlot(chargeCrafter.inventory, InventoryChargeCrafter.SLOT_CHARGE_ITEM, 24, 35, this::updateOutput));
        this.addSlotToContainer(new InputSlot(chargeCrafter.inventory, InventoryChargeCrafter.SLOT_FUEL_ITEM, 58, 35, this::updateOutput));
        this.addSlotToContainer(new OutputSlot(chargeCrafter.inventory, resultInv, 0, 130, 35));

        layoutPlayerInventorySlots(8, 84);
    }

    private void updateOutput()
    {
        //Reset inventory
        resultInv.setStackInSlot(0, ItemStack.EMPTY);

        //Generate new result
        final int fuelValue = ChargeFuelReg.getFuel(chargeCrafter.inventory.getStackInSlot(InventoryChargeCrafter.SLOT_FUEL_ITEM));
        final ItemStack chargeStack = chargeCrafter.inventory.getStackInSlot(InventoryChargeCrafter.SLOT_CHARGE_ITEM);
        if (chargeStack.getItem() instanceof ItemCharge)
        {
            final int currentCharge = ((ItemCharge) chargeStack.getItem()).getCharge(chargeStack);
            if (currentCharge + fuelValue < ItemCharge.MAX_CHARGE)
            {
                final ItemStack newStack = chargeStack.copy();
                ((ItemCharge) chargeStack.getItem()).setCharge(newStack, currentCharge + fuelValue);
                resultInv.setStackInSlot(0, newStack);
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        if (playerIn.world.getTileEntity(chargeCrafter.getPos()) != chargeCrafter)
        {
            return false;
        }
        else
        {
            return playerIn.getDistanceSq(chargeCrafter.getPos().getX() + 0.5D, chargeCrafter.getPos().getY() + 0.5D, chargeCrafter.getPos().getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        final int playerInvStartIndex = InventoryChargeCrafter.INVENTORY_SIZE;
        final int playerInvEndIndex = InventoryChargeCrafter.INVENTORY_SIZE + 27;
        final int playerBarEndIndex = playerInvEndIndex + 9;

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();

            //block -> inventory/hotbar
            if (index < InventoryChargeCrafter.INVENTORY_SIZE)
            {
                if (!this.mergeItemStack(stack, playerInvStartIndex, playerBarEndIndex, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            }
            else
            {
                //inventory/hotbar -> block
                if (!this.mergeItemStack(stack, 0, InventoryChargeCrafter.INVENTORY_SIZE, false))
                {
                    return ItemStack.EMPTY;
                }
                //inventory -> hotbar
                else if (index <= playerInvEndIndex)
                {
                    if (!this.mergeItemStack(stack, playerInvEndIndex, playerBarEndIndex, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                //hotbar -> inventory
                else if (index < playerBarEndIndex)
                {
                    if (!this.mergeItemStack(stack, playerInvStartIndex, playerBarEndIndex, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }


    private int addSlotRange(IInventory handler, int index, int x, int y, int amount, int dx)
    {
        for (int i = 0; i < amount; i++)
        {
            this.addSlotToContainer(new Slot(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
    {
        for (int j = 0; j < verAmount; j++)
        {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow)
    {
        // Player inventory
        addSlotBox(player.inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(player.inventory, 0, leftCol, topRow, 9, 18);
    }
}
