package blueduck.jellyfishing.client.models;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.entity.AbstractJellyfishEntity;
import blueduck.jellyfishing.entity.CowJellyfishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class TwoFistedJumperModel<T extends AbstractJellyfishEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Jellyfishing.MOD_ID, "two_fisted_jumper"), "main");
	private final ModelPart bone;

	public TwoFistedJumperModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 0).mirror().addBox(-0.25F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(18, 0).addBox(5.25F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition tentacle1 = bone.addOrReplaceChild("tentacle1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 1.5F));

		PartDefinition tentacle2 = bone.addOrReplaceChild("tentacle2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 1.5F));

		PartDefinition tentacle3 = bone.addOrReplaceChild("tentacle3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, -1.5F));

		PartDefinition tentacle4 = bone.addOrReplaceChild("tentacle4", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, -1.5F));

		PartDefinition arm1 = bone.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 12).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 0.0F, 0.0F));

		PartDefinition arm2 = bone.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(4, 12).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float rotateVal = ageInTicks * 0.05F - 20;

		bone.getChild("tentacle1").xRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;
		bone.getChild("tentacle1").zRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;

		bone.getChild("tentacle2").xRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;
		bone.getChild("tentacle2").zRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;

		bone.getChild("tentacle3").xRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
		bone.getChild("tentacle3").zRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;

		bone.getChild("tentacle4").xRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
		bone.getChild("tentacle4").zRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;

		bone.getChild("arm1").zRot = Math.abs(Mth.sin(rotateVal)) * -0.35F;
		bone.getChild("arm2").zRot = Math.abs(Mth.sin(rotateVal)) * 0.35F;


//		bone.getChild("arm1").y = Math.abs(Mth.sin(rotateVal)) * -2F;
//		bone.getChild("arm2").y = Math.abs(Mth.sin(rotateVal)) * -2F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}