package firis.lmgvc.client.event;

import firis.lmgvc.client.layer.MMMLayerHeldItem_gun;
import net.blacklab.lmr.api.client.event.ClientEventLMRE.RendererLittleMaidAddLayerEvent;
import net.blacklab.lmr.client.renderer.entity.RenderLittleMaid;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererLittleMaidAddLayerEventHandler {

	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerEventPost(RendererLittleMaidAddLayerEvent event) {
		
		
		RenderLittleMaid renderer = event.getRenderer();
		
		//GVC用Layer登録
		renderer.addLayer(new MMMLayerHeldItem_gun(renderer));
		
	}
	
}
