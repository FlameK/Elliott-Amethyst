package behaviour.bloodRunes.behaviour;


import api.MethodProvider;
import api.ReactionGenerator;
import api.data.Data;
import api.framework.Leaf;
import behaviour.bloodRunes.data.BloodRuneData;
import org.powbot.api.rt4.GrandExchange;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;

public class ActivateBloodEssence extends Leaf
{
	@Override
	public boolean isValid()
	{
		return Inventory.get().stream().noneMatch(x -> x.id() == BloodRuneData.BLOOD_ESSENCE_ACTIVE)
				&& Inventory.get().stream().anyMatch(x -> x.id() == BloodRuneData.BLOOD_ESSENCE);
	}

	@Override
	public int onLoop()
	{
		Data.scriptStatus = "Activating Blood Essence";
		Item bloodEssence = Inventory.stream().id(BloodRuneData.BLOOD_ESSENCE).first();

		if (bloodEssence != null)
		{
			bloodEssence.interact("Activate");
			MethodProvider.sleepUntil(() -> Inventory.stream().id(BloodRuneData.BLOOD_ESSENCE_ACTIVE).first() != null, 1000);
			return ReactionGenerator.getPredictable();
		}

		BloodRuneData.totalCosts += GrandExchange.getItemPrice(BloodRuneData.BLOOD_ESSENCE);
		BloodRuneData.bloodEssenceUsed++;

		return ReactionGenerator.getPredictable();
	}
}