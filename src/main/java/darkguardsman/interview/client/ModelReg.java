package darkguardsman.interview.client;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.ghoul.EntityGhoul;
import darkguardsman.interview.content.ghoul.render.RenderGhoul;
import darkguardsman.interview.content.reg.BlockReg;
import darkguardsman.interview.content.reg.ItemReg;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = InterviewMod.ID, value = Side.CLIENT)
public class ModelReg {

    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        //items
        newItemModel(ItemReg.blueMetal, 0, "inventory", "");
        newItemModel(ItemReg.redMetal, 0, "inventory", "");
        newItemModel(ItemReg.greenMetal, 0, "inventory", "");
        newItemModel(ItemReg.purpleMetal, 0, "inventory", "");
        newItemModel(ItemReg.charge, 0, "inventory", "");

        //blocks
        newBlockModel(BlockReg.charger, 0, "inventory", "");

        //Entity
        RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class, RenderGhoul::new);
    }

    protected static void newItemModel(Item item, int meta, String varient, String sub) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName() + sub, varient));
    }


    protected static void newBlockModel(Block block, int meta, String varient, String sub)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName() + sub, varient));
    }

}
