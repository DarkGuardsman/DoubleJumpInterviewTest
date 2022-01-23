package darkguardsman.interview.content.ghoul.ai;

import darkguardsman.interview.content.ghoul.Ghoul;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

/**
 * Created by Robin Seifert on 1/22/2022.
 */
public class SleepGoal extends Goal
{
    protected final Ghoul ghoul;

    public SleepGoal(Ghoul mob)
    {
        this.ghoul = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse()
    {
        return ghoul.sleepCooldown <= 0 && !hasValidTarget();
    }

    @Override
    public boolean canContinueToUse()
    {
        return !hasValidTarget();
    }

    private boolean hasValidTarget()
    {
        return ghoul.getTarget() != null && ghoul.getTarget().isAlive();
    }

    @Override
    public void start()
    {
        ghoul.getEntityData().set(Ghoul.AWAKE, false);
        this.ghoul.setAggressive(false);
        this.ghoul.getNavigation().stop();
    }

    @Override
    public void stop()
    {
        ghoul.getEntityData().set(Ghoul.AWAKE, true);
        this.ghoul.setAggressive(true);
        ghoul.sleepCooldown = Ghoul.SLEEP_TIMER;
    }

    @Override
    public boolean requiresUpdateEveryTick()
    {
        return true;
    }
}
