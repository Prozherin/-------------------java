import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Set<Laptop> laptops = new HashSet<>();
		laptops.add(new Laptop("Asus", 12, 512, "Windows", "Red"));
		laptops.add(new Laptop("Acer", 16, 512, "Windows", "Blue"));
		laptops.add(new Laptop("HP", 8, 256, "Windows", "White"));
		laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
		laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
		laptops.add(new Laptop("Acer", 16, 512, "Windows", "Blue"));

		Map<String, Object> filters = new HashMap<>();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Выберите критерии для фильтрации:");
		System.out.println("1 - ОЗУ");
		System.out.println("2 - Объем ЖД");
		System.out.println("3 - Операционная система");
		System.out.println("4 - Цвет");
		System.out.println("0 - Отфильтровать");

		Map<String, List<String>> options = new HashMap<>();
		options.put("ram", Arrays.asList("4", "8", "12", "16"));
		options.put("hdd", Arrays.asList("256", "512", "1024"));
		options.put("os", Arrays.asList("Windows", "MacOS", "Linux"));
		options.put("color", Arrays.asList("Red", "Blue", "White", "Black", "Silver"));

		int choice;
		while (true) {
			choice = scanner.nextInt();
			if (choice == 0) {
				break;
			}
			switch (choice) {
				case 1:
				case 2:
				case 3:
				case 4:
					String category = getCategory(choice);
					System.out.println("Доступные варианты для " + category + ":");
					for (int i = 0; i < options.get(category).size(); i++) {
						System.out.println((i + 1) + " - " + options.get(category).get(i));
					}
					System.out.println("Введите уникальный ключ для " + category + ":");
					int selectedOptionIndex = scanner.nextInt();
					String selectedOption = options.get(category).get(selectedOptionIndex - 1);
					filters.put(category, selectedOption);
					break;
				default:
					System.out.println("Неверный выбор. Попробуйте снова.");
			}
		}
		scanner.close();

		Set<Laptop> filteredLaptops = laptops.stream()
				.filter(laptop -> filters.getOrDefault("ram", 0) instanceof String &&
						laptop.ram >= Integer.parseInt((String) filters.getOrDefault("ram", "0")))
				.filter(laptop -> filters.getOrDefault("hdd", 0) instanceof String &&
						laptop.hdd >= Integer.parseInt((String) filters.getOrDefault("hdd", "0")))
				.filter(laptop -> filters.getOrDefault("os", "").equals("") ||
						laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
				.filter(laptop -> filters.getOrDefault("color", "").equals("") ||
						laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
				.collect(Collectors.toSet());

		System.out.println("Отфильтрованные ноутбуки:");
		for (Laptop laptop : filteredLaptops) {
			System.out.println(laptop);
		}
	}

	private static String getCategory(int choice) {
		switch (choice) {
			case 1:
				return "ram";
			case 2:
				return "hdd";
			case 3:
				return "os";
			case 4:
				return "color";
			default:
				return "";
		}
	}
}