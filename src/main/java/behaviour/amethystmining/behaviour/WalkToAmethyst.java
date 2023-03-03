package behaviour.amethystmining.behaviour;

import api.ReactionGenerator;
import api.data.Data;
import api.framework.Leaf;
import behaviour.amethystmining.data.AmethystData;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class WalkToAmethyst extends Leaf
{
	@Override
	public boolean isValid()
	{
		return !AmethystData.AMETHYST_AREA.contains(Players.local());
	}

	@Override
	public int onLoop()
	{

		Data.scriptStatus = "Walking to Amethyst Mine";
		Movement.builder(AmethystData.AMETHYST_AREA.getRandomTile())
				.setRunMin(15)
				.setRunMax(80)
				.setWalkUntil(() -> AmethystData.AMETHYST_AREA.contains(Players.local()))
				.move();

		return ReactionGenerator.getPredictable();
	}
}
