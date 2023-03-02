import api.ContinueDialogue;
import api.ZoomOut;
import api.framework.Tree;
import api.data.Data;
import behaviour.amethystmining.behaviour.*;
import behaviour.amethystmining.data.AmethystData;
import lombok.Getter;
import org.powbot.api.Condition;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackInventoryOption;

@ScriptManifest(name = "eAmethyst", description = "Mines Amethyst.", version = "v0.1")
public class eAmethyst extends AbstractScript
{

	@Getter
	private static final Tree tree = new Tree();

	@Override
	public void onStart()
	{

		AmethystData.Craftables.getItemPrices();

		addPaint(PaintBuilder.newBuilder()
				.x(40)
				.y(45)
				.trackSkill(Skill.Mining)
				.addString("Status: ", () -> Data.scriptStatus)
				.addString("Current Branch: ", () -> Tree.currentBranch)
				.addString("Current Leaf: ", () -> Tree.currentLeaf)
				.trackInventoryItem(AmethystData.AMETHYST, "Amethyst Mined: ", TrackInventoryOption.QuantityChangeIncOny)
				.trackInventoryItem(AmethystData.Craftables.AMETHYST_BOLT_TIPS.getItemID(), "Amethyst Bolt Tips Made: ", TrackInventoryOption.QuantityChangeIncOny)
				.trackInventoryItem(AmethystData.Craftables.AMETHYST_ARROWTIPS.getItemID(), "Amethyst Arrow Tips Made: ", TrackInventoryOption.QuantityChangeIncOny)
				.trackInventoryItem(AmethystData.Craftables.AMETHYST_JAVELIN_HEADS.getItemID(), "Amethyst Javelin Heads Made: ", TrackInventoryOption.QuantityChangeIncOny)
				.trackInventoryItem(AmethystData.Craftables.AMETHYST_DART_TIP.getItemID(), "Amethyst Dart Tips Made: ", TrackInventoryOption.QuantityChangeIncOny)
				.build());


		tree.addBranches(
				new ZoomOut(),
				new ContinueDialogue(),
				new MissingItems(),
				new CraftAmethyst(),
				new MineAmethyst()
		);
	}

	@Override
	public void poll()
	{
		Condition.sleep(tree.onLoop());
	}

	public static void main(String[] args)
	{
		// Start your script with this function. Make sure your device is connected via ADB, and only one is connected
		new eAmethyst().startScript();
	}



}
