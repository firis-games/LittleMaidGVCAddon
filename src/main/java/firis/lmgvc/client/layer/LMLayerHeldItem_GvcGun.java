package firis.lmgvc.client.layer;

import net.blacklab.lmr.client.renderer.entity.RenderLittleMaid;
import net.blacklab.lmr.entity.littlemaid.EntityLittleMaid;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;

public class LMLayerHeldItem_GvcGun extends AbstractLMLayerHeldItem_GvcGun {

	//レイヤーと化したアイテム描画
	protected final RenderLittleMaid renderer;
	
	/**
	 * コンストラクタ
	 * @param rendererIn
	 */
	public LMLayerHeldItem_GvcGun(RenderLivingBase<?> rendererIn) {
		super(rendererIn);
		renderer = (RenderLittleMaid) rendererIn;
	}

	/**
	 * アイテムを描画する
	 */
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
				
		EntityLittleMaid maid = (EntityLittleMaid) entitylivingbaseIn;
		boolean isWait = maid.isMaidWait();
		boolean isSneak = maid.isSneaking();

		//待機状態の場合は描画しない
		if(isWait) return;
		
		EnumHandSide mainHandSide = maid.getPrimaryHand() == EnumHandSide.RIGHT ? EnumHandSide.RIGHT : EnumHandSide.LEFT;
		EnumHandSide offHandSide = maid.getPrimaryHand() == EnumHandSide.RIGHT ? EnumHandSide.LEFT : EnumHandSide.RIGHT;

		//メインハンド描画
		//メイドさんのメインハンドは装備中のアイテムと判断する
		this.doRenderLayerItem(maid, maid.getCurrentEquippedItem(), mainHandSide, isSneak);
		
		//オフハンド描画
		this.doRenderLayerItem(maid, maid.getHeldItemOffhand(), offHandSide, isSneak);
		
	}

	/**
	 * 指定の腕の位置へ描画位置を変更する
	 */
	@Override
	protected void setArmPostRenderer(EntityLivingBase entityLiving, EnumHandSide handSide) {
		
		int armIndex = handSide == EnumHandSide.RIGHT ? 0 : 1;
		
		this.renderer.modelMain.model.Arms[armIndex].postRender(0.0625F);
	}

}
