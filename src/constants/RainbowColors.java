package constants;

import utility.LoggerUtility;

public enum RainbowColors {
	VIOLET (1),
	INDIGO (2),
	BLUE (3),
	GREEN (4),
	YELLOW (5),
	ORANGE (6),
	RED (7);

	public int colorCode;
	
	RainbowColors(int colorCode) {
		this.colorCode = colorCode;
	}
	
	public int getColorCode() {
		return colorCode;
	}
	
	public static void main(String ...args) {
		listColors();
	}
	
	public static void listColors() {
		for(RainbowColors color : RainbowColors.values()) {
			LoggerUtility.DEFAULT_LOGGER.info("Color "+color.getColorCode()+" is "+color);
		}
	}
}
