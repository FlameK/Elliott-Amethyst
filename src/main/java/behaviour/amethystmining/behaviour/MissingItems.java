package behaviour.amethystmining.behaviour;

import api.CarriedItems;
import api.ReactionGenerator;
import api.data.Data;
import api.framework.Leaf;
import behaviour.amethystmining.data.AmethystData;

public class MissingItems extends Leaf
{
	@Override
	public boolean isValid()
	{
		return !CarriedItems.contains(x -> x.name().contains("pickaxe")) || !CarriedItems.contains(AmethystData.CHISEL);
	}

	@Override
	public int onLoop()
	{

		Data.scriptStatus = "You are missing a pickaxe or chisel.";
		return ReactionGenerator.getAFK();
	}
}
