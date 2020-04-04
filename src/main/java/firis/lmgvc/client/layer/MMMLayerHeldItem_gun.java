package firis.lmgvc.client.layer;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import gvclib.entity.living.EntityGVCLivingBase;
import gvclib.event.gun.Layermat;
import gvclib.item.ItemGunBase;
import net.blacklab.lmr.client.renderer.entity.RenderLittleMaid;
import net.blacklab.lmr.entity.littlemaid.EntityLittleMaid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * 手持ちアイテムレイヤー
 * RenderLittleMaidから分離
 */
@Deprecated
public class MMMLayerHeldItem_gun extends LayerHeldItem {

	//レイヤーと化したアイテム描画
	protected final RenderLittleMaid renderer;
	
	/**
	 * コンストラクタ
	 * @param rendererIn
	 */
	public MMMLayerHeldItem_gun(RenderLivingBase<?> rendererIn) {
		super(rendererIn);
		renderer = (RenderLittleMaid) rendererIn;
	}

	/**
	 * アイテムを描画する
	 */
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
	
		EntityLittleMaid lmm = (EntityLittleMaid) entitylivingbaseIn;

		if(!lmm.isMaidWait()){
			
			Iterator<ItemStack> heldItemIterator = lmm.getHeldEquipment().iterator();
			int i = 0, handindexes[] = {lmm.getDominantArm(), lmm.getDominantArm() == 1 ? 0 : 1};

			while (heldItemIterator.hasNext()) {
				ItemStack itemstack = (ItemStack) heldItemIterator.next();

				if (!itemstack.isEmpty())
				{
					GlStateManager.pushMatrix();

					// Use dominant arm as mainhand.
					this.renderer.modelMain.model.Arms[handindexes[i]].postRender(0.0625F);

					if (lmm.isSneaking()) {
						GlStateManager.translate(0.0F, 0.2F, 0.0F);
					}
					boolean flag = handindexes[i] == 1;

					GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
	//				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
					/* 初期モデル構成で
					 * x: 手の甲に垂直な方向(-で向かって右に移動)
					 * y: 体の面に垂直な方向(-で向かって背面方向に移動)
					 * z: 腕に平行な方向(-で向かって手の先方向に移動)
					 */
					/*GlStateManager.translate(flag ? -0.0125F : 0.0125F, 0.05f, -0.15f);
					Minecraft.getMinecraft().getItemRenderer().renderItemSide(lmm, itemstack,
							flag ?
									ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND :
									ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND,
							flag);
					GlStateManager.popMatrix();*/
					//GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
					float xx = 0.32F*(flag ? -1 : 1);//0.32
					float yy = -0.64F;//-0.64
					float zz = -2.24F;//-2.24
					
					
		            GL11.glScalef(0.1875F, 0.1875F, 0.1875F);
		            GlStateManager.translate((float)((flag ? -1 : 1) / 16.0F) * -5.33F + xx, 0.125F * 1.33F + yy, -0.625F * -4.5F + zz);//-5.33,-3.33,-4.5
		            //GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
		            //Minecraft.getMinecraft().getItemRenderer().renderItemSide(p_188358_1_, p_188358_2_, p_188358_3_, flag);
		            if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
						ItemGunBase gun = (ItemGunBase) itemstack.getItem();
						if (entitylivingbaseIn instanceof EntityGVCLivingBase && entitylivingbaseIn != null) {
			             	EntityGVCLivingBase en = (EntityGVCLivingBase) entitylivingbaseIn;
			             	if (!en.getattacktask())
			                {
			             		if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemGunBase) {//item
			             			//ItemGunBase gun = (ItemGunBase) itemstack.getItem();
			             			if(gun != null && gun.arm_l_posz > -1.0F) {
			             				GlStateManager.rotate(80.0F, 0.0F, 1.0F, 0.0F);
			             				GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
			             			}
			             		}
			                }
			            }
						{
							//GL11.glNewList(gllist, GL11.GL_COMPILE);
							Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
							gun.obj_model.renderPart("mat1");
							gun.obj_model.renderPart("mat100");
							gun.obj_model.renderPart("mat2");
							gun.obj_model.renderPart("mat3");
							Layermat.rendersight( entitylivingbaseIn,  itemstack,  gun);
							Layermat.renderattachment( entitylivingbaseIn,  itemstack,  gun);
							gun.obj_model.renderPart("mat25");
							gun.obj_model.renderPart("mat31");
							gun.obj_model.renderPart("mat32");/**/
							//GL11.glEndList();
						}
		            }
		            GlStateManager.popMatrix();
		        }
				}

				i++;
			}
		}	
}