package darkguardsman.interview.content.ghoul;

import darkguardsman.interview.content.ghoul.ai.EntityAISleeping;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityGhoul extends EntityMob
{

    private static final String NBT_SLEEPING = "IsSleeping";
    private static final String NBT_SLEEP_DELAY = "SleepDelay";

    private static final DataParameter<Boolean> IS_SLEEPING = EntityDataManager.createKey(EntityGhoul.class, DataSerializers.BOOLEAN);

    private static final int TICKS_PER_SECOND = 20;
    public static final int SLEEP_TIMER = TICKS_PER_SECOND * 20;

    public int sleepCooldown = 0;

    public EntityGhoul(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAISleeping(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.getDataManager().register(IS_SLEEPING, true);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (sleepCooldown > 0)
        {
            sleepCooldown--;
        }
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    public boolean isSleeping()
    {
        return this.getDataManager().get(IS_SLEEPING);
    }

    public void setSleeping(boolean state)
    {
        this.getDataManager().set(IS_SLEEPING, state);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        nbt.setBoolean(NBT_SLEEPING, isSleeping());
        nbt.setInteger(NBT_SLEEP_DELAY, sleepCooldown);

    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey(NBT_SLEEPING))
        {
            setSleeping(nbt.getBoolean(NBT_SLEEPING));
        }
        if (nbt.hasKey(NBT_SLEEP_DELAY))
        {
            sleepCooldown = nbt.getInteger(NBT_SLEEP_DELAY);
        }
    }
}
