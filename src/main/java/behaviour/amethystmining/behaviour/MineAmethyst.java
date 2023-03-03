package behaviour.amethystmining.behaviour;

import api.ReactionGenerator;
import api.data.Data;
import api.framework.Leaf;
import api.handlers.LocalPlayer;
import behaviour.amethystmining.data.AmethystData;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class MineAmethyst extends Leaf
{

	private long lastAnimation = 0;
	private int timeout = 2_500;

	@Override
	public boolean isValid()
	{
		return !Inventory.isFull();
	}

	@Override
	public int onLoop()
	{
		GameObject amethyst = Objects.stream().name("Crystals").within(AmethystData.AMETHYST_AREA).nearest().first();
		Item gem = Inventory.stream().nameContains("Uncut").first();

		if (LocalPlayer.isAnimating())
		{
			lastAnimation = System.currentTimeMillis();
			return ReactionGenerator.getPredictable();
		}

		if ((System.currentTimeMillis() - lastAnimation) > timeout)
		{
			if (gem.valid())
			{
				Data.scriptStatus = "Dropping Uncut Gem";
				gem.interact("Drop");
				return ReactionGenerator.getLowPredictable();
			}
			Data.scriptStatus = "Mining Amethyst";
			if (amethyst.valid())
			{
				amethyst.interact("Mine");
				Condition.wait(LocalPlayer::isAnimating, 100, 25);
			}
			return ReactionGenerator.getPredictable();
		}

		Data.scriptStatus = "Idle while mining...";
		return ReactionGenerator.getNormal();
	}



}
