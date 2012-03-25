package com.smud.model;

public interface ResetAction<T extends Resetable> {

	public void execute(T target);
}
