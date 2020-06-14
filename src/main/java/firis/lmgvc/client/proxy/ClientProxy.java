package firis.lmgvc.client.proxy;

import firis.lmgvc.client.event.RendererAvatarAddLayerEventHandler;
import firis.lmgvc.client.event.RendererLittleMaidAddLayerEventHandler;
import firis.lmgvc.common.proxy.IProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;

public class ClientProxy implements IProxy {

	
	@Override
	public void registerClientEvent() {
		
		//GVCLivのlayer登録
		//メイドさんのLayer追加
		if (Loader.isModLoaded("lmreengaged")) {
			MinecraftForge.EVENT_BUS.register(new RendererLittleMaidAddLayerEventHandler());
		}
		
		//LMアバターのLayer追加
		if (Loader.isModLoaded("lmavatar")) {
			MinecraftForge.EVENT_BUS.register(new RendererAvatarAddLayerEventHandler());
		}
		
	}

}
