package darkguardsman.interview.content.ghoul.ai;

import darkguardsman.interview.content.ghoul.EntityGhoul;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class EntityAIDash extends EntityAIBase {

    /**
     * minimal distance in blocks to prevent dashing
     */
    public final double DASH_MIN_DISTANCE = 3;

    /**
     * Number of blocks to dash
     */
    public final double DASH_DISTANCE = 5;

    protected final EntityGhoul ghoul;

    public EntityAIDash(EntityGhoul ghoul) {
        this.ghoul = ghoul;
    }

    @Override
    public void startExecuting() {
        final EntityLivingBase target = ghoul.getAttackTarget();

        //Get delta and distance
        final double deltaX = target.posX - ghoul.posX;
        final double deltaY = target.posY - ghoul.posY;
        final double deltaZ = target.posZ - ghoul.posZ;
        final double distance = Math.sqrt(deltaX * deltaY + deltaY * deltaY + deltaZ * deltaZ);

        //Get vector towards target
        final double vecX = deltaX / distance;
        final double vecY = deltaY / distance;
        final double vecZ = deltaZ / distance;

        //Reduce dash to 1 block away from player to avoid killing them too fast
        final double dashDistance = Math.min(distance - 1, DASH_DISTANCE);

        //Build dash vector, direction * distance
        final Vec3d dashVector = new Vec3d(vecX * dashDistance, vecY * dashDistance, vecZ * dashDistance);

        //Raytrace to see if we will hit blocks while dashing, don't want to dash inside a wall... we also want to stop dashing through fluids
        final RayTraceResult result = ghoul.world.rayTraceBlocks(ghoul.getPositionVector(), ghoul.getPositionVector().add(dashVector), true, true, true);

        //Nothing is the way so move full dash distance
        if (result == null || result.typeOfHit == RayTraceResult.Type.MISS) {
            ghoul.setPosition(ghoul.posX + dashVector.x, ghoul.posY + dashVector.y, ghoul.posZ + dashVector.z);
        } else {
            ghoul.setPosition(result.hitVec.x, result.hitVec.y, result.hitVec.z);
        }

    }

    @Override
    public void resetTask() {
        ghoul.dashCooldown = EntityGhoul.DASH_TIMER;
    }

    @Override
    public boolean shouldExecute() {
        return ghoul.dashCooldown <= 0 && hasValidTarget();
    }

    private boolean hasValidTarget() {
        return ghoul.getAttackTarget() != null
                && !ghoul.getAttackTarget().isDead
                && ghoul.getAttackTarget().getDistance(ghoul) > DASH_MIN_DISTANCE;
    }
}
