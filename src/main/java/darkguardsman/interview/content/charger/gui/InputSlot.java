package darkguardsman.interview.content.charger.gui;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class InputSlot extends SlotItemHandler
{
    private final Callback callback;
    public InputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, Callback callback)
    {
        super(itemHandler, index, xPosition, yPosition);
        this.callback = callback;
    }

    @Override
    public void setChanged()
    {
        super.setChanged();
        callback.call();
    }

    @FunctionalInterface
    public static interface Callback {
        void call();
    }
}
