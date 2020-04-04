package firis.lmgvc.client.layer;

import net.firis.lmt.client.event.LittleMaidAvatarClientTickEventHandler;
import net.firis.lmt.client.renderer.RendererMaidPlayerMultiModel;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHandSide;

/**
 * 手持ちアイテムレイヤー
 * RenderLittleMaidから分離
 */
public class LMAvatarLayerHeldItem_GvcGun extends AbstractLMLayerHeldItem_GvcGun {

	//レイヤーと化したアイテム描画
	protected final RendererMaidPlayerMultiModel renderer;
	
	/**
	 * コンストラクタ
	 * @param rendererIn
	 */
	public LMAvatarLayerHeldItem_GvcGun(RenderLivingBase<?> rendererIn) {
		super(rendererIn);
		renderer = (RendererMaidPlayerMultiModel) rendererIn;
	}

	/**
	 * アイテムを描画する
	 */
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
	
		EntityPlayer player = (EntityPlayer) entitylivingbaseIn;
		boolean isWait = LittleMaidAvatarClientTickEventHandler.lmAvatarWaitAction.getStat(player);
		boolean isSneak = player.isSneaking();
		
		//待機状態の場合は描画しない
		if(isWait) return;
		
		EnumHandSide mainHandSide = player.getPrimaryHand() == EnumHandSide.RIGHT ? EnumHandSide.RIGHT : EnumHandSide.LEFT;
		EnumHandSide offHandSide = player.getPrimaryHand() == EnumHandSide.RIGHT ? EnumHandSide.LEFT : EnumHandSide.RIGHT;
		
		//メインハンド描画
		this.doRenderLayerItem(player, player.getHeldItemMainhand(), mainHandSide, isSneak);
		
		//オフハンド描画
		this.doRenderLayerItem(player, player.getHeldItemOffhand(), offHandSide, isSneak);
		
	}
	
	/**
	 * 指定の腕の位置へ描画位置を変更する
	 */
	protected void setArmPostRenderer(EntityLivingBase entityLiving, EnumHandSide handSide) {
		this.renderer.getLittleMaidMultiModel().armsPostRenderer(handSide, 0.0625F);
	}
}