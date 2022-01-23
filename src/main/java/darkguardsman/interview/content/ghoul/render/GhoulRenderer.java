package darkguardsman.interview.content.ghoul.render;

import darkguardsman.interview.InterviewMod;
import darkguardsman.interview.content.ghoul.Ghoul;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Created by Robin Seifert on 1/22/2022.
 */
public class GhoulRenderer extends MobRenderer<Ghoul, GhoulModel>
{
    private static final ResourceLocation SKIN = new ResourceLocation(InterviewMod.ID, "textures/entity/ghoul.png");

    public GhoulRenderer(EntityRendererProvider.Context context)
    {
        super(context, new GhoulModel(context.bakeLayer(GhoulModel.LAYER_LOCATION)), 0.5f);
        //AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(p_102001_), this.attackTime, p_102004_);
    }

    @Override
    public ResourceLocation getTextureLocation(Ghoul p_114482_)
    {
        return SKIN;
    }
}
