package darkguardsman.interview.content.charger.gui;

import darkguardsman.interview.content.ModBlocks;
import darkguardsman.interview.content.ModMenu;
import darkguardsman.interview.content.charger.TileEntityChargeCrafter;
import darkguardsman.interview.api.ChargeFuelReg;
import darkguardsman.interview.content.charger.ItemCharge;
import darkguardsman.interview.content.charger.InventoryChargeCrafter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ChargeCrafterMenu extends AbstractContainerMenu
{
    private final BlockEntity blockEntity;
    private final Player playerEntity;
    private final IItemHandler playerInventory;
    private IItemHandler inventory;

    protected final ItemStackHandler resultInv = new ItemStackHandler();

    public ChargeCrafterMenu(int windowId, BlockPos pos, Inventory playerInventory, Player player)
    {
        super(ModMenu.CHARGER.get(), windowId);
        this.blockEntity = player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity instanceof TileEntityChargeCrafter tile)
        {
            this.inventory = tile.inventory;
            addSlot(new InputSlot(inventory, InventoryChargeCrafter.SLOT_CHARGE_ITEM, 24, 35, this::updateOutput));
            addSlot(new InputSlot(inventory, InventoryChargeCrafter.SLOT_FUEL_ITEM, 58, 35, this::updateOutput));
            addSlot(new OutputSlot(inventory, resultInv, 0, 130, 35));
        }
        layoutPlayerInventorySlots(8, 84);
    }

    @Override
    public void slotsChanged(Container container)
    {
        super.slotsChanged(container);
        updateOutput();

    }

    private void updateOutput()
    {
        //Reset inventory
        resultInv.setStackInSlot(0, ItemStack.EMPTY);

        //Generate new result
        if (inventory != null)
        {
            final int fuelValue = ChargeFuelReg.getFuel(inventory.getStackInSlot(InventoryChargeCrafter.SLOT_FUEL_ITEM));
            final ItemStack chargeStack = inventory.getStackInSlot(InventoryChargeCrafter.SLOT_CHARGE_ITEM);
            if (chargeStack.getItem() instanceof ItemCharge item)
            {
                final int currentCharge = item.getCharge(chargeStack);
                if (currentCharge + fuelValue < ItemCharge.MAX_CHARGE)
                {
                    final ItemStack newStack = chargeStack.copy();
                    item.setCharge(newStack, currentCharge + fuelValue);
                    resultInv.setStackInSlot(0, newStack);
                }
            }
        }
    }

    @Override
    public boolean stillValid(Player playerIn)
    {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, ModBlocks.CHARGER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index)
    {
        final int playerInvStartIndex = InventoryChargeCrafter.INVENTORY_SIZE;
        final int playerInvEndIndex = InventoryChargeCrafter.INVENTORY_SIZE + 27;
        final int playerBarEndIndex = playerInvEndIndex + 9;

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem())
        {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            //block -> inventory/hotbar
            if (index < InventoryChargeCrafter.INVENTORY_SIZE)
            {
                if (!this.moveItemStackTo(stack, playerInvStartIndex, playerBarEndIndex, true))
                {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            }
            else
            {
                //inventory/hotbar -> block
                if (!this.moveItemStackTo(stack, 0, InventoryChargeCrafter.INVENTORY_SIZE, false))
                {
                    return ItemStack.EMPTY;
                }
                //inventory -> hotbar
                else if (index <= playerInvEndIndex)
                {
                    if (!this.moveItemStackTo(stack, playerInvEndIndex, playerBarEndIndex, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                //hotbar -> inventory
                else if (index < playerBarEndIndex)
                {
                    if (!this.moveItemStackTo(stack, playerInvStartIndex, playerBarEndIndex, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx)
    {
        for (int i = 0; i < amount; i++)
        {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
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
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}
