import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This Test tests literally everything that needs to be tested
// more tests is basically useless
public class TestPaginator {
	public static ServerMock mock;
	static Material[] materials = Material.values();
	static Material[] illegalMaterials = {
		Material.BANNER,
		Material.STANDING_BANNER,
		Material.WALL_BANNER,
		Material.BOOK,
		Material.ENCHANTED_BOOK,
		Material.FIREWORK,
		Material.KNOWLEDGE_BOOK,
		Material.LEATHER_BOOTS,
		Material.LEATHER_CHESTPLATE,
		Material.LEATHER_HELMET,
		Material.LEATHER_LEGGINGS,
		Material.MAP,
		Material.POTION,
		Material.LINGERING_POTION,
		Material.SPLASH_POTION,
		Material.SKULL,
		Material.SKULL_ITEM,
		Material.EGG,
		Material.DRAGON_EGG,
		Material.KNOWLEDGE_BOOK,
		Material.MONSTER_EGG,
		Material.MONSTER_EGGS,
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
	@Test
	void TestIfItemsExist_ForwardScanTheyAllExist_Success(){
		MockPaginator mock = fillMockPaginator();
		int i=0,j=0;
		ItemStack[] itemStacks = mock.getItemStacks();
		mock.setPage(0);
		while (i<itemStacks.length) {
			ItemStack itemToCompare = itemStacks[i];
			ItemStack item = mock.getInventory().getItem(j);
			assertEquals(itemToCompare, item);
			if (j >= 35){
				j=0;
				mock.nextPage();
			}else
				j++;
			i++;
		}
	}
	@Test
	void TestIfItemsExist_BackwardsScanTheyAllExist_Success(){
		MockPaginator mock = fillMockPaginator();
		ItemStack[] itemStacks = mock.getItemStacks();
		int i=itemStacks.length-1,j;
		int maxPage = (int)Math.ceil(itemStacks.length/36d);
		j = i%36;
		mock.setPage(maxPage-1);
		while (i>0) {
			ItemStack itemToCompare = itemStacks[i];
			ItemStack item = mock.getInventory().getItem(j);
			assertEquals(itemToCompare, item);
			if (j <= 0){
				j=35;
				mock.previousPage();
			}else
				j--;
			i--;
		}
	}
}
