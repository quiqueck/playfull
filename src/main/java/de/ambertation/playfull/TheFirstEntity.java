package de.ambertation.playfull;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.IronGolemWanderAroundGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.WanderNearTargetGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TheFirstEntity extends PathAwareEntity implements IAnimatable
{
	private AnimationFactory factory = new AnimationFactory(this);
	
	public TheFirstEntity(World worldIn){
		this(EntityType.GIANT, worldIn);
	}
	
	public TheFirstEntity(EntityType<? extends PathAwareEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.ignoreCameraFrustum = true;
	}
	
	AnimationBuilder IDLE = new AnimationBuilder().addAnimation("animation.the_first.idle", true);
	AnimationBuilder WALK = new AnimationBuilder().addAnimation("animation.the_first.walk", true);
	
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
	{
		if (this.getVelocity().horizontalLengthSquared() < 2.51E-7D) {
			event.getController()
				 .setAnimation(IDLE);
		} else {
			event.getController()
				 .setAnimation(WALK);
		}
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData data)
	{
		data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}
	
	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
	
	@Override
	public boolean canImmediatelyDespawn(double distanceSquared) {
		return false;
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(5, new LookAroundGoal(this));
		this.goalSelector.add(7, new WanderNearTargetGoal(this, 0.9D, 48.0F));
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 64.0F));

		this.targetSelector.add(4, new ActiveTargetGoal(this, PlayerEntity.class, 10, true, false, (entity) -> {
			return ((LivingEntity)entity).getType() == EntityType.PLAYER;
		}));
		this.targetSelector.add(3, new ActiveTargetGoal(this, MobEntity.class, 5, false, false, (entity) -> {
			return  (entity instanceof CreeperEntity);
		}));
		super.initGoals();
	}
	
	public boolean canTarget(EntityType<?> type) {
		if (type == EntityType.PLAYER || type == EntityType.CREEPER) {
			return true;
		} else {
			return super.canTarget(type);
		}
	}
	
	public boolean canTarget(LivingEntity target) {
		return canTarget(target.getType());
	}
	
	
	public void tickMovement() {
		super.tickMovement();
		
		if (this.getVelocity().horizontalLengthSquared() > 2.500000277905201E-7D && this.random.nextInt(5) == 0) {
			int i = MathHelper.floor(this.getX());
			int j = MathHelper.floor(this.getY() - 0.2);
			int k = MathHelper.floor(this.getZ());
			BlockState blockState = this.world.getBlockState(new BlockPos(i, j, k));
			if (!blockState.isAir()) {
				this.world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getWidth(), this.getY() + 0.1D, this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getWidth(), 4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
			}
		}
	}
	
	public boolean canSpawn(WorldView world) {
		BlockPos blockPos = this.getBlockPos();
		BlockPos blockPos2 = blockPos.down();
		BlockState blockState = world.getBlockState(blockPos2);
		if (!blockState.hasSolidTopSurface(world, blockPos2, this)) {
			return false;
		} else {
			return true;
		}
	}
}
