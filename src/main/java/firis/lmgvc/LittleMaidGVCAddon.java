package firis.lmgvc;

import org.apache.logging.log4j.Logger;

import firis.lmgvc.common.proxy.IProxy;
import gvclib.item.ItemMagazine;
import net.blacklab.lmr.entity.littlemaid.mode.EntityMode_Archer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
		modid = LittleMaidGVCAddon.MODID, 
		name = LittleMaidGVCAddon.NAME,
		version = LittleMaidGVCAddon.VERSION,
		dependencies = LittleMaidGVCAddon.MOD_DEPENDENCIES,
		acceptedMinecraftVersions = LittleMaidGVCAddon.MOD_ACCEPTED_MINECRAFT_VERSIONS
)
@EventBusSubscriber
public class LittleMaidGVCAddon
{
    public static final String MODID = "lmgvc_addon";
    public static final String NAME = "LittleMaidGVCAddon";
    public static final String VERSION = "0.5";
    public static final String MOD_DEPENDENCIES = "required-after:forge@[1.12.2-14.23.5.2768,);" 
    		+ "required-after:lmlibrary@[1.0.0,);"
    		+ "after:lmreengaged@[9.0.0,);"
    		+ "after:lmavatar@[1.0.0,);"
    		+ "required-after:gvclib@[1.12.2,);";
    public static final String MOD_ACCEPTED_MINECRAFT_VERSIONS = "[1.12.2]";

    public static Logger logger;
    
    @Instance(LittleMaidGVCAddon.MODID)
    public static LittleMaidGVCAddon INSTANCE;
    
    @SidedProxy(serverSide = "firis.lmgvc.common.proxy.CommonProxy", 
    		clientSide = "firis.lmgvc.client.proxy.ClientProxy")
    public static IProxy proxy;
    
    /**
     * アイテムインスタンス保持用
     */
    @ObjectHolder(LittleMaidGVCAddon.MODID)
    public static class LMItems{
    }
    
    /**
     * ブロックインスタンス保持用
     */
    @ObjectHolder(LittleMaidGVCAddon.MODID)
    public static class LMBlocks{
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        
        //ClientEvent登録
        proxy.registerClientEvent();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//GVC系の弾薬を登録
    	if (Loader.isModLoaded("lmreengaged")) {
    		EntityMode_Archer.arrowClassList.add(ItemMagazine.class);
    	}
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
    
    /**
     * ブロックを登録するイベント
     */
    @SubscribeEvent
    protected static void registerBlocks(RegistryEvent.Register<Block> event)
    {
    }
    
    /**
     * アイテムを登録するイベント
     */
    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event)
    {
    }
    
    /**
     * モデル登録イベント
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    protected static void registerModels(ModelRegistryEvent event)
    {
    }
    
    /**
     * Entity登録イベント
     * @param event
     */
    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityEntry> event) 
    {
    }
}
