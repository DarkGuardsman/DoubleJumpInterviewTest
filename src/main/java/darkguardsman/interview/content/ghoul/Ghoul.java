package darkguardsman.interview.content.ghoul;

import darkguardsman.interview.content.ghoul.ai.SleepGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Created by Robin Seifert on 1/22/2022.
 */
public class Ghoul extends Monster
{
    private static final String NBT_AWAKE = "Awake";
    public static final EntityDataAccessor<Boolean> AWAKE = SynchedEntityData.defineId(Ghoul.class, EntityDataSerializers.BOOLEAN);

    private static final int TICKS_PER_SECOND = 20;
    public static final int SLEEP_TIMER = TICKS_PER_SECOND * 20;

    public int sleepCooldown = 0;

    public Ghoul(EntityType<? extends Ghoul> type, Level level)
    {
        super(type, level);
        this.blocksBuilding = true;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SleepGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void tick()
    {
        super.tick();
        if(sleepCooldown > 0) {
            sleepCooldown--;
        }
    }


    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.getEntityData().define(AWAKE, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag data)
    {
        super.readAdditionalSaveData(data);
        entityData.set(AWAKE, data.getBoolean(NBT_AWAKE));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag data)
    {
        super.addAdditionalSaveData(data);
        data.putBoolean(NBT_AWAKE, entityData.get(AWAKE));
    }
}
