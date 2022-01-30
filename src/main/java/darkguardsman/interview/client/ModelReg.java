package darkguardsman.interview.client;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.ModBlocks;
import darkguardsman.interview.content.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = InterviewMod.ID, value = Side.CLIENT)
public class ModelReg {

    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        //items
        newItemModel(ModItems.blueMetal, 0, "inventory", "");
        newItemModel(ModItems.redMetal, 0, "inventory", "");
        newItemModel(ModItems.greenMetal, 0, "inventory", "");
        newItemModel(ModItems.purpleMetal, 0, "inventory", "");
        newItemModel(ModItems.charge, 0, "inventory", "");

        //blocks
        newBlockModel(ModBlocks.charger, 0, "inventory", "");
    }

    protected static void newItemModel(Item item, int meta, String varient, String sub) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName() + sub, varient));
    }


    protected static void newBlockModel(Block block, int meta, String varient, String sub)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName() + sub, varient));
    }

}
