package darkguardsman.interview.content.ghoul.ai;

import darkguardsman.interview.content.ghoul.EntityGhoul;
import net.minecraft.entity.ai.EntityAIBase;

/**
 * Created by Robin Seifert on 1/22/2022.
 */
public class EntityAISleeping extends EntityAIBase
{
    protected final EntityGhoul ghoul;

    public EntityAISleeping(EntityGhoul mob)
    {
        this.ghoul = mob;
        this.setMutexBits(3);
    }

    @Override
    public void startExecuting()
    {
        ghoul.setSleeping(true);
        this.ghoul.getNavigator().clearPath();
    }

    @Override
    public void resetTask()
    {
        ghoul.setSleeping(false);
        ghoul.sleepCooldown = EntityGhoul.SLEEP_TIMER;
    }

    @Override
    public boolean shouldExecute()
    {
        return ghoul.sleepCooldown <= 0 && !hasValidTarget();
    }

    private boolean hasValidTarget()
    {
        return ghoul.getAttackTarget() != null && !ghoul.getAttackTarget().isDead;
    }
}
