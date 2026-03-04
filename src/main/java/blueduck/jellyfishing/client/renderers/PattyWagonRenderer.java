package blueduck.jellyfishing.client.renderers;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.models.PattyWagonModel;
import blueduck.jellyfishing.entity.PattyWagonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class PattyWagonRenderer extends EntityRenderer<PattyWagonEntity> {

    private final PattyWagonModel<PattyWagonEntity> model;

    public PattyWagonRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model = new PattyWagonModel<>(ctx.bakeLayer(PattyWagonModel.LAYER_LOCATION));
    }

    @Override
    public void render(PattyWagonEntity entity, float yaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int light) {

        model.setupAnim(entity, 0, 0, entity.tickCount + partialTicks, 0, 0);

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(- yaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, -1.6250, 0);
        // Render actual model
        model.renderToBuffer(
                poseStack,
                buffer.getBuffer(RenderType.entityTranslucentCull(getTextureLocation(entity))),
                light,
                OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1
        );

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(PattyWagonEntity entity) {
        return new ResourceLocation(Jellyfishing.MOD_ID, "textures/entity/patty_wagon.png");
    }
}