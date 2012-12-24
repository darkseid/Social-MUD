package com.smud.model.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ItemWearPosition {

	LIGHT,
	FINGER,
	NECK,
	BODY,
	HEAD,
	LEGS,
	FEET,
	HANDS,
	ARMS,
	SHIELD,
	ABOUT,
	WAIST,
	WRIST,
	WIELD,
	HOLD,
	WINGS,
	EAR,
	FACE,
	NOSE,
	INSIGNE;
	
	
	public static List<ItemWearPosition> WEARABLE_POSITIONS = new ArrayList<ItemWearPosition>(Arrays.asList(FINGER,
			NECK, BODY, HEAD, LEGS, FEET, HANDS, ARMS, SHIELD, ABOUT,
			WAIST, WRIST, WINGS, EAR, FACE, NOSE, INSIGNE));
}
