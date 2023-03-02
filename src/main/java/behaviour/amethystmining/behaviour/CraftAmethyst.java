package behaviour.amethystmining.behaviour;

import api.ReactionGenerator;
import api.data.Data;
import api.framework.Leaf;
import api.handlers.LocalPlayer;
import behaviour.amethystmining.data.AmethystData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class CraftAmethyst extends Leaf
{
	private boolean shouldCraft = false;
	private long lastAnimation = 0;
	private int timeout = 3000;

	AmethystData.Craftables craftable;

	@Override
	public boolean isValid()
	{
		return Inventory.isFull() || shouldCraft;
	}

	@Override
	public int onLoop()
	{

		if (Inventory.stream().id(AmethystData.AMETHYST).count() > 0)
		{
			shouldCraft = true;
		} else {
			shouldCraft = false;
		}

		if (!LocalPlayer.isAnimating() && (System.currentTimeMillis() - lastAnimation) > timeout)
		{
			Data.scriptStatus = "Crafting Amethyst";
			craftable = AmethystData.Craftables.getBestCraftable();
			Widget craftWidget = Widgets.widget(AmethystData.PRODUCTION_PARENT_WIDGET);
			if (craftWidget.valid())
			{
				int widgetChild = craftable.getWidgetChild();
				craftWidget.component(widgetChild).interact("Make");
				Condition.wait(LocalPlayer::isAnimating, 100, 25);
				return ReactionGenerator.getPredictable();
			}

			Item chisel = Inventory.stream().id(AmethystData.CHISEL).first();
			Item amethyst = Inventory.stream().id(AmethystData.AMETHYST).first();
			if (Inventory.selectedItem().id() == -1)
			{
				Data.scriptStatus = "Selecting Chisel";
				chisel.interact("Use");
			}
			else if (Inventory.selectedItem().id() == chisel.id())
			{
				Data.scriptStatus = "Using Chisel on Amethyst";
				amethyst.interact("Use");
				Condition.wait(Chat::pendingInput, 100, 10);
			}
			return ReactionGenerator.getPredictable();
		}

		Data.scriptStatus = "Idle while Crafting";
		if (lastAnimation == 0) lastAnimation = System.currentTimeMillis();
		return ReactionGenerator.getPredictable();
	}
}
