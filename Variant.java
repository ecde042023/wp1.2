package com.company.variantcomparatorviewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Variant {
	private String variantName;
	private final List<Requirement> requirements;

	public Variant() {
		requirements = new ArrayList<>();
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public List<Requirement> getRequirements() {
		return Collections.unmodifiableList(requirements);
	}

	public void addRequirement(Requirement requirement) {
		this.requirements.add(requirement);
	}

	public boolean removeRequirement(Requirement requirement) {
		return requirements.remove(requirement);
	}

	public void clearRequirements() {
		requirements.clear();
	}

	@Override
	public String toString() {
		return "Variant{" +
				"variantName='" + variantName + '\'' +
				", requirements=" + requirements +
				'}';
	}
}