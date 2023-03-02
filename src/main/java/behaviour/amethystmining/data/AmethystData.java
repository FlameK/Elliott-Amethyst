package behaviour.amethystmining.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.powbot.api.rt4.GrandExchange;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;


public class AmethystData
{

	public static int AMETHYST = 21347;
	public static final int CHISEL = 1755;
	public static final int PRODUCTION_PARENT_WIDGET = 270;


	@Getter
	@AllArgsConstructor
	public enum Craftables
	{
		AMETHYST_BOLT_TIPS(21338, 0, 83, 14),
		AMETHYST_ARROWTIPS(21350, 0, 85, 15),
		AMETHYST_JAVELIN_HEADS(21352, 0, 87, 16),
		AMETHYST_DART_TIP(25853, 0, 89, 17);

		int itemID;
		int itemPrice;
		int craftingLevelRequired;
		int widgetChild;

		public static void getItemPrices()
		{
			for (Craftables c : Craftables.values())
			{
				c.itemPrice = GrandExchange.getItemPrice(c.itemID);
			}
		}

		public static Craftables getBestCraftable()
		{
			Craftables bestCraftable = Craftables.AMETHYST_BOLT_TIPS;
			int profitablility = 0;
			for (Craftables c : Craftables.values())
			{
				if (Skills.level(Skill.Crafting) >= c.craftingLevelRequired)
				{
					if (c.itemPrice > profitablility)
					{
						bestCraftable = c;
					}
				}
			}
			return bestCraftable;
		}
	}


}
