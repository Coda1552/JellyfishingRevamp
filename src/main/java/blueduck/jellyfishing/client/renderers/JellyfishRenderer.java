package blueduck.jellyfishing.client.renderers;

import blueduck.jellyfishing.Jellyfishing;
import blueduck.jellyfishing.client.models.JellyfishModel;
import blueduck.jellyfishing.entity.AbstractJellyfishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JellyfishRenderer extends MobRenderer<AbstractJellyfishEntity, JellyfishModel<AbstractJellyfishEntity>> {
    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishModel<>(context.bakeLayer(JellyfishModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractJellyfishEntity pEntity) {
        return new ResourceLocation(Jellyfishing.MOD_ID, "textures/entity/jellyfish.png");
    }

    protected void setupRotations(AbstractJellyfishEntity pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pEntityLiving.xBodyRotO, pEntityLiving.xBodyRot);
        float f1 = Mth.lerp(pPartialTicks, pEntityLiving.zBodyRotO, pEntityLiving.zBodyRot);
        //pPoseStack.translate(0.0F, 0.1F, 0.0F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - pRotationYaw));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(f));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(f1));
        //pPoseStack.translate(0.0F, -.1F, 0.0F);
    }


}