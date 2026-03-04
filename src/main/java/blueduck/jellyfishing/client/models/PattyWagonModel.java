package blueduck.jellyfishing.client.models;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.entity.PattyWagonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PattyWagonModel<T extends PattyWagonEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Jellyfishing.MOD_ID, "pattywagonmodel"), "main");
	private final ModelPart root;
	private final ModelPart front;
	private final ModelPart back;
	private final ModelPart right;
	private final ModelPart left;
	private final ModelPart base;
	private final ModelPart plate;
	private final ModelPart wheel1;
	private final ModelPart wheel2;
	private final ModelPart wheel3;
	private final ModelPart wheel4;

	public PattyWagonModel(ModelPart root) {
		this.root = root;

		this.front = root.getChild("front");
		this.back = root.getChild("back");
		this.right = root.getChild("right");
		this.left = root.getChild("left");
		this.base = root.getChild("base");
		this.plate = this.base.getChild("plate");
		this.wheel1 = root.getChild("wheel1");
		this.wheel2 = root.getChild("wheel2");
		this.wheel3 = root.getChild("wheel3");
		this.wheel4 = root.getChild("wheel4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create().texOffs(0, 47).addBox(-8.0F, -10.0F, 8.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 18).addBox(-7.0F, -14.0F, 4.0F, 14.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(8, 40).addBox(4.0F, -9.0F, 10.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-7.0F, -9.0F, 10.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(35, 63).addBox(-2.5F, -11.0F, 6.9F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create().texOffs(44, 0).addBox(-8.0F, -10.0F, 8.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 50).addBox(7.0F, -24.0F, 7.0F, 0.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create().texOffs(26, 37).addBox(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create().texOffs(26, 27).addBox(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -8.0F, 0.0F, 20.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 47).addBox(-2.0F, -6.0F, 2.0F, 10.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(5.0F, -6.0F, 4.0F, 3.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition plate = base.addOrReplaceChild("plate", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -3.5F, -2.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.4F, 0.0F, 1.0F, 0.0F, 0.1745F, 0.0F));

//		PartDefinition wheel1 = partdefinition.addOrReplaceChild("wheel1", CubeListBuilder.create().texOffs(58, 15).addBox(8.0F, -4.0F, -8.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//		PartDefinition wheel2 = partdefinition.addOrReplaceChild("wheel2", CubeListBuilder.create().texOffs(14, 57).addBox(8.0F, -4.0F, 4.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//		PartDefinition wheel3 = partdefinition.addOrReplaceChild("wheel3", CubeListBuilder.create().texOffs(55, 56).addBox(-10.0F, -4.0F, -8.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
//
//		PartDefinition wheel4 = partdefinition.addOrReplaceChild("wheel4", CubeListBuilder.create().texOffs(49, 10).addBox(-10.0F, -4.0F, 4.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition wheel1 = partdefinition.addOrReplaceChild(
				"wheel1",
				CubeListBuilder.create()
						.texOffs(58, 15)
						.addBox(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F),
				// wheel center pivot (front-right)
				PartPose.offset(9.0F, 22.0F, -6.0F)
		);

		PartDefinition wheel2 = partdefinition.addOrReplaceChild(
				"wheel2",
				CubeListBuilder.create()
						.texOffs(14, 57)
						.addBox(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F),
				// wheel center pivot (back-right)
				PartPose.offset(9.0F, 22.0F, 6.0F)
		);

		PartDefinition wheel3 = partdefinition.addOrReplaceChild(
				"wheel3",
				CubeListBuilder.create()
						.texOffs(55, 56)
						.addBox(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F),
				// wheel center pivot (front-left)
				PartPose.offset(-9.0F, 22.0F, -6.0F)
		);

		PartDefinition wheel4 = partdefinition.addOrReplaceChild(
				"wheel4",
				CubeListBuilder.create()
						.texOffs(49, 10)
						.addBox(-1.0F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F),
				// wheel center pivot (back-left)
				PartPose.offset(-9.0F, 22.0F, 6.0F)
		);

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(PattyWagonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// horizontal speed (blocks per tick)
		double dx = entity.getDeltaMovement().x;
		double dz = entity.getDeltaMovement().z;
		double horizSpeed = Math.sqrt(dx * dx + dz * dz);

		// Wheel radius in blocks: wheel modeled as depth 5 px -> radius ~2.5 px -> in blocks = 2.5/16
		// Adjust this if your wheel size is different
		final float wheelRadius = 2.5f / 16f;

		// Theta (radians) = distance / r
		float rotationDelta = 0f;
		if (wheelRadius > 0.0001f) {
			rotationDelta = (float)(horizSpeed / wheelRadius);
		}

		// Optionally scale rotation speed down if it looks too fast:
		rotationDelta *= 0.125f; // reduce if needed, e.g. 0.6f

		// Rotate wheels around X axis (forward axis).
		// Depending on your model orientation you may need to flip sign (use -rotationDelta).
		this.wheel1.xRot += rotationDelta;
		this.wheel2.xRot += rotationDelta;
		this.wheel3.xRot += rotationDelta;
		this.wheel4.xRot += rotationDelta;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

//		front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		base.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		wheel1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		wheel2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		wheel3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
//		wheel4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}