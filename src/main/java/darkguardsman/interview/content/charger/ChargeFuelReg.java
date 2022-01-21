package darkguardsman.interview.content.charger;

import darkguardsman.interview.content.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class ChargeFuelReg //TODO implement as recipe system
{
    //Can easily be expanded to also account for item data and tags
    private static final Map<RegistryObject<Item>, Integer> FUEL_VALUES = new HashMap();

    static
    {
        addItem(ModItems.BLUE_METAL, 1); //Can be config driven but no config was requested
        addItem(ModItems.GREEN_METAL, 5);
        addItem(ModItems.RED_METAL, 30);
        addItem(ModItems.PURPLE_METAL, 100);
    }

    /**
     * Adds a new item to be used as charge fuel
     *
     * @param item registry object to reference
     * @param fuel charge value to use
     */
    public static void addItem(RegistryObject<Item> item, int fuel)
    {
        FUEL_VALUES.put(item, fuel);
    }

    /**
     * Checks if the item is valid fuel
     *
     * @param item to check
     * @return true if fuel
     */
    public static boolean isFuel(Item item)
    {
        return FUEL_VALUES.keySet().stream().anyMatch(entry -> entry.get() == item);
    }
}
