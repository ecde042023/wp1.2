package com.company.variantcomparatorviewer;

public class VariantFactory {
	public static Variant createVariant() {
		return new Variant();
	}

	public static Requirement createRequirement(String requirementId, String variant1Value, String variant2Value) {
		return new Requirement(requirementId, variant1Value, variant2Value);
	}
}