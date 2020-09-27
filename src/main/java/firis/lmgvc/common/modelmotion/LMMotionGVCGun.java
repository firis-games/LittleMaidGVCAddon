package firis.lmgvc.common.modelmotion;

import firis.lmmm.api.caps.IModelCaps;
import firis.lmmm.api.caps.ModelCapsHelper;
import firis.lmmm.api.model.ModelLittleMaidBase;
import firis.lmmm.api.model.motion.ILMMotion;
import gvclib.item.ItemGunBase;
import net.minecraft.item.Item;

/**
 * GVC銃構え追加モーション
 * @author firis-games
 *
 */
public class LMMotionGVCGun implements ILMMotion {

	/**
	 * メイドさんがGVC銃を持っている動き
	 */
	@Override
	public boolean postRotationAngles(ModelLittleMaidBase model, String motion, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, IModelCaps pEntityCaps) {
		
		// 描画判定
		String rightItemId = ModelCapsHelper.getCapsValueString(pEntityCaps, IModelCaps.caps_currentRightHandItem);
		String leftItemId = ModelCapsHelper.getCapsValueString(pEntityCaps, IModelCaps.caps_currentLeftHandItem);
		Item rightItem = Item.getByNameOrId(rightItemId);
		Item leftItem = Item.getByNameOrId(leftItemId);
		boolean isGunRight = rightItem instanceof ItemGunBase;
		boolean isGunLeft = leftItem instanceof ItemGunBase;
		
		if (model.isWait) {
			//待機モーション
			if (isGunRight || isGunLeft) {
				model.bipedRightArm.setRotateAngleDeg(-15.0F, 0.0F , 10.0F);
				model.bipedLeftArm.setRotateAngleDeg(-15.0F, 0.0F, -10.0F);
				return true;
			}
		} else {
			//銃構えモーション
			if (isGunRight && isGunLeft) {
				//両手持ち
				model.bipedRightArm.setRotateAngleDeg(-90.0F + headPitch, 0.0F + netHeadYaw, 0.0F);
				model.bipedLeftArm.setRotateAngleDeg(-90.0F + headPitch, 0.0F + netHeadYaw, 0.0F);
			} else if (isGunRight) {
				//右手持ち
				model.bipedRightArm.setRotateAngleDeg(-90.0F + headPitch, -5.0F + netHeadYaw, 0.0F);
				model.bipedLeftArm.setRotateAngleDeg(-90.0F + headPitch, 45.0F + netHeadYaw, 0.0F);
			} else if (isGunLeft) {
				//左手持ち
				model.bipedRightArm.setRotateAngleDeg(-90.0F + headPitch, -45.0F + netHeadYaw, 0.0F);
				model.bipedLeftArm.setRotateAngleDeg(-90.0F + headPitch, 5.0F + netHeadYaw, 0.0F);
			}			
			if (isGunRight || isGunLeft) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getMotionId() {
		return null;
	}

}
