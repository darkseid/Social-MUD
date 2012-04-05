package com.smud.model.character;

import java.util.List;

public interface AttributePriorityStrategy {

	void distributeAttributes(Player player, List<Integer> attributes);
}
