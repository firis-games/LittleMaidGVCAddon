package firis.lmgvc.client.event;

import firis.lmgvc.client.layer.LMAvatarLayerHeldItem_GvcGun;
import net.firis.lmt.client.event.ClientEventLMAvatar;
import net.firis.lmt.client.renderer.RendererMaidPlayerMultiModel;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererAvatarAddLayerEventHandler {

	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void doRendererAvatarAddLayerEvent(ClientEventLMAvatar.RendererAvatarAddLayerEvent event) {
		
		RendererMaidPlayerMultiModel renderer = event.getRenderer();
		
		//GVC用Layer登録
		renderer.addLayer(new LMAvatarLayerHeldItem_GvcGun(renderer));
		
	}
	
}
