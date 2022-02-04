package darkguardsman.interview.content.ghoul.render;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.ghoul.EntityGhoul;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by Robin Seifert on 2/4/2022.
 */
@SideOnly(Side.CLIENT)
public class RenderGhoul extends RenderLiving<EntityGhoul>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(InterviewMod.ID, "textures/entity/ghoul.png");

    public RenderGhoul(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelGhoul(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGhoul entityGhoul)
    {
        return TEXTURE;
    }
}
