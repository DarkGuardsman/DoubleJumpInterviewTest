package darkguardsman.interview.content.charger.gui;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.charger.TileEntityChargeCrafter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Created by Robin Seifert on 1/21/2022.
 */
public class GuiChargeCrafter extends GuiContainer
{
    private final ResourceLocation GUI = new ResourceLocation(InterviewMod.ID, "textures/gui/charge_gui.png");
    private final EntityPlayer player;

    public GuiChargeCrafter(EntityPlayer player, TileEntityChargeCrafter chargeCrafter)
    {
        super(new ContainerChargeCrafter(player, chargeCrafter));
        this.player = player;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        final String inventoryName = new TextComponentTranslation("container.charger").getUnformattedText();
        this.fontRenderer.drawString(inventoryName, this.xSize / 2 - this.fontRenderer.getStringWidth(inventoryName) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.player.inventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(GUI);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
