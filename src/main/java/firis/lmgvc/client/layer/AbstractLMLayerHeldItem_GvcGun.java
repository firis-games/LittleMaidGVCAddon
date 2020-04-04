package firis.lmgvc.client.layer;

import org.lwjgl.opengl.GL11;

import gvclib.entity.living.EntityGVCLivingBase;
import gvclib.event.gun.Layermat;
import gvclib.item.ItemGunBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractLMLayerHeldItem_GvcGun extends LayerHeldItem {

	/**
	 * コンストラクタ
	 * @param rendererIn
	 */
	public AbstractLMLayerHeldItem_GvcGun(RenderLivingBase<?> rendererIn) {
		super(rendererIn);
	}

	/**
	 * アイテムを描画する
	 * 実実装はdoRenderLayerItemで行う
	 */
	@Override
	abstract public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale);
	
	/**
	 * アイテムを描画する
	 * @param entitylivingbaseIn
	 * @param stack
	 * @param handSide
	 */
	protected void doRenderLayerItem(EntityLivingBase entitylivingbaseIn, ItemStack stack, EnumHandSide handSide, boolean isSneak) {
		
		if (stack.isEmpty()) return;
		
		GlStateManager.pushMatrix();
		
		//腕の位置に調整する
		this.setArmPostRenderer(entitylivingbaseIn, handSide);
		
		//メイドモデル用位置調整
		this.setPositionLittleMaidModel(isSneak, handSide == EnumHandSide.RIGHT);
		
        //GVC銃描画
        this.renderGvcGun(entitylivingbaseIn, stack);
        
        GlStateManager.popMatrix();
		
	}
	
	/**
	 * 指定の腕の位置へ描画位置を変更する
	 */
	abstract protected void setArmPostRenderer(EntityLivingBase entityLiving, EnumHandSide handSide);
	
	/**
	 * リトルメイド向けの位置調整
	 */
	protected void setPositionLittleMaidModel(boolean isSneak, boolean isRight) {
		
		//if (isSneak) {
		//	GlStateManager.translate(0.0F, 0.2F, 0.0F);
		//}
		
		boolean flag = isRight;

		GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
//				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		
		/* 初期モデル構成で
		 * x: 手の甲に垂直な方向(-で向かって右に移動)
		 * y: 体の面に垂直な方向(-で向かって背面方向に移動)
		 * z: 腕に平行な方向(-で向かって手の先方向に移動)
		 */
		float xx = 0.32F*(flag ? -1 : 1);//0.32
		float yy = -0.64F;//-0.64
		float zz = -2.24F;//-2.24
		
		
        GL11.glScalef(0.1875F, 0.1875F, 0.1875F);
        GlStateManager.translate((float)((flag ? -1 : 1) / 16.0F) * -5.33F + xx, 0.125F * 1.33F + yy, -0.625F * -4.5F + zz);//-5.33,-3.33,-4.5
        
	}
	
	/**
	 * GVC銃を描画する
	 */
	protected void renderGvcGun(EntityLivingBase entityliving, ItemStack stack) {
		if (!stack.isEmpty() && stack.getItem() instanceof ItemGunBase) {//item
			ItemGunBase gun = (ItemGunBase) stack.getItem();
			
			//GVC系モブの調整用(未使用)
			if (entityliving instanceof EntityGVCLivingBase && entityliving != null) {
             	EntityGVCLivingBase en = (EntityGVCLivingBase) entityliving;
             	if (!en.getattacktask())
                {
             		if (!stack.isEmpty() && stack.getItem() instanceof ItemGunBase) {//item
             			//ItemGunBase gun = (ItemGunBase) itemstack.getItem();
             			if(gun != null && gun.arm_l_posz > -1.0F) {
             				GlStateManager.rotate(80.0F, 0.0F, 1.0F, 0.0F);
             				GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
             			}
             		}
                }
            }
			
			GunRender : {
            	
				//GL11.glNewList(gllist, GL11.GL_COMPILE);
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(gun.obj_tex));
				gun.obj_model.renderPart("mat1");
				gun.obj_model.renderPart("mat100");
				gun.obj_model.renderPart("mat2");
				gun.obj_model.renderPart("mat3");
				Layermat.rendersight(entityliving, stack, gun);
				Layermat.renderattachment( entityliving, stack, gun);
				gun.obj_model.renderPart("mat25");
				gun.obj_model.renderPart("mat31");
				gun.obj_model.renderPart("mat32");/**/
				//GL11.glEndList();
				
				break GunRender;
			}
        }
	}
	
}