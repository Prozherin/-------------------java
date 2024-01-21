class Laptop {
	String title;
	int ram;
	int hdd;
	String os;
	String color;

	public Laptop(String title, int ram, int hdd, String os, String color) {
		this.title = title;
		this.ram = ram;
		this.hdd = hdd;
		this.os = os;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Laptop{" +
				"brand='" + title + '\'' +
				", ram=" + ram +
				", hdd=" + hdd +
				", os='" + os + '\'' +
				", color='" + color + '\'' +
				'}';
	}
}