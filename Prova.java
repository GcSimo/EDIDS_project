import java.util.HashMap;
import java.util.Map;

public class Prova {
	public static void main(String[] args) {
		Map<Integer, Integer> map1 = new HashMap<>();
		map1.put(1, 1);
		map1.put(2, 2);

		map1.put(3, 2);
		map1.put(4, 3);
		map1.put(5, 4);

		Map<Integer, Integer> map2 = new HashMap<>();
		map2.put(2, 2);
		map2.put(4, 4);

		System.out.println("Map1: " + map1.toString());
		System.out.println("Map2: " + map2.toString());

		map1.values().retainAll(map2.values());

		System.out.println("Map1: " + map1.toString());
		System.out.println("Map2: " + map2.toString());
	}
}
