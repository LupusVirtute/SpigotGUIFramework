import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.lupus.gui.SelectableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This Test tests literally everything that needs to be tested
// more tests is basically useless
public class TestPaginator {
	public static ServerMock mock;
	static Material[] materials = Material.values();
	static Material[] illegalMaterials = {
		Material.BOOK,
		Material.ENCHANTED_BOOK,
		Material.KNOWLEDGE_BOOK,
		Material.LEATHER_BOOTS,
		Material.LEATHER_CHESTPLATE,
		Material.LEATHER_HELMET,
		Material.LEATHER_LEGGINGS,
		Material.MAP,
		Material.POTION,
		Material.LINGERING_POTION,
		Material.SPLASH_POTION,
		Material.EGG,
		Material.DRAGON_EGG,
		Material.KNOWLEDGE_BOOK,
	};
	@BeforeAll
	public static void beforeAll(){
		mock = MockBukkit.isMocked() ? MockBukkit.getMock() : MockBukkit.mock();
		List<Material> materialList = new LinkedList<>(Arrays.asList(materials));
		List<Material> illegalMaterialsList = new LinkedList<>(Arrays.asList(illegalMaterials));
		materialList.removeIf(illegalMaterialsList::contains);
		materials = materialList.toArray(new Material[0]);

	}
	public Material chooseRandomMaterial(){
		Material material = materials[new Random().nextInt(materials.length-5)+1];
		if(material == null){
			return chooseRandomMaterial();
		}
		return material;
	}
	public MockPaginator fillMockPaginator(){
		MockPaginator mock = new MockPaginator();
		Random rnd = new Random();
		int max = rnd.nextInt(30)+70;
		for (int i=0;i<max;i++){
			Material material = chooseRandomMaterial();
			mock.addItemStack(
					new MockSelectable(
							new ItemStack(
									material
							)
					)
			);
		}
		return mock;
	}
}
