package com.company.variantcomparatorviewer;

import java.util.List;

public class VariantComparisonModel {
	private List<Variant> variants;

	public VariantComparisonModel(List<Variant> variants) {
		this.variants = variants;
	}

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}
}