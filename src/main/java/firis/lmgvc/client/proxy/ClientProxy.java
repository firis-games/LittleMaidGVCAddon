package firis.lmgvc.client.proxy;

import firis.lmgvc.client.event.RendererAvatarAddLayerEventHandler;
import firis.lmgvc.client.event.RendererLittleMaidAddLayerEventHandler;
import firis.lmgvc.common.proxy.IProxy;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements IProxy {

	
	@Override
	public void registerClientEvent() {
		
		//GVCLivのlayer登録
		
		//メイドさんのLayer追加
		MinecraftForge.EVENT_BUS.register(new RendererLittleMaidAddLayerEventHandler());
		
		//LMアバターのLayer追加
		MinecraftForge.EVENT_BUS.register(new RendererAvatarAddLayerEventHandler());
		
	}

}
