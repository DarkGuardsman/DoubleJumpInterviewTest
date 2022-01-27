package darkguardsman.interview.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ChargeFuelReg //TODO implement as recipe system
{
    //Can easily be expanded to also account for item data and tags
    private static final Map<Item, Integer> FUEL_VALUES = new HashMap();

    /**
     * Adds a new item to be used as charge fuel
     *
     * @param item registry object to reference
     * @param fuel charge value to use
     */
    public static void addItem(Item item, int fuel)
    {
        FUEL_VALUES.put(item, fuel);
    }

    public static int getFuel(ItemStack stack)
    {
        return FUEL_VALUES.getOrDefault(stack.getItem(), 0);
    }

    /**
     * Checks if the item is valid fuel
     *
     * @param item to check
     * @return true if fuel
     */
    public static boolean isFuel(Item item)
    {
        return FUEL_VALUES.containsKey(item);
    }
}
