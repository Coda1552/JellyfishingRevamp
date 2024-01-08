package blueduck.jellyfishing.client.models;

// Made with Blockbench 4.9.2
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

public class CowJellyfishModel<T extends AbstractJellyfishEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Jellyfishing.MOD_ID, "cow_jellyfish"), "main");
	private final ModelPart body;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;
	private final ModelPart leg4;

	public CowJellyfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leg1 = root.getChild("leg1");
		this.leg2 = root.getChild("leg2");
		this.leg3 = root.getChild("leg3");
		this.leg4 = root.getChild("leg4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.9F, -12.0F, 14.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(13, 27).addBox(1.0F, -1.1F, -8.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 24.9F, 5.0F));

		PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-1.5F, -0.1F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 24.0F, 4.5F));

		PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -0.1F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 24.0F, -4.5F));

		PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -0.1F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 24.0F, 4.5F));

		PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-1.5F, -0.1F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.5F, 24.0F, -4.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float rotateVal = ageInTicks * 0.05F - 20;

		leg1.xRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;
		leg1.zRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;

		leg2.xRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
		leg2.zRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;

		leg3.xRot = Math.abs(Mth.sin(rotateVal)) * 0.5F;
		leg3.zRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;

		leg4.xRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
		leg4.zRot = Math.abs(Mth.sin(rotateVal)) * -0.5F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}