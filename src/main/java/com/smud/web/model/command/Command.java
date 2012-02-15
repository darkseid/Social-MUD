package com.smud.web.model.command;

import java.util.Map;

public interface Command {

	CommandResponse execute(Map<String, String> parameters);
}
