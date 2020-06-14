package firis.lmgvc.client.event;

import firis.lmavatar.api.client.event.ClientEventLMAvatar;
import firis.lmavatar.client.renderer.RendererLMAvatar;
import firis.lmgvc.client.layer.LMAvatarLayerHeldItem_GvcGun;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererAvatarAddLayerEventHandler {

	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void doRendererAvatarAddLayerEvent(ClientEventLMAvatar.RendererLMAvatarAddLayerEvent event) {
		
		RendererLMAvatar renderer = event.getRenderer();
		
		//GVC用Layer登録
		renderer.addLayer(new LMAvatarLayerHeldItem_GvcGun(renderer));
		
	}
	
}
