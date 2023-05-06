package com.company.variantcomparatorviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class ComparisonHelper {
public class ComparisonHelper implements ComparisonStrategy {

	@Override
	public List<String> findDifferences(String attributeName, String variantName1, String variantName2, List<Variant> variants) {
		List<String> reqIdsWithDifferences = new ArrayList<>();
		Variant variant1 = PopupManager.getVariantByName(variantName1, variants);
		Variant variant2 = PopupManager.getVariantByName(variantName2, variants);

		if (variant1 != null && variant2 != null) {
			for (Requirement req1 : variant1.getRequirements()) {
				Requirement req2 = PopupManager.findRequirementById(variant2.getRequirements(), req1.getAttributes().get("REQ_ID"));
				if (req2 != null) {
					String value1 = req1.getAttributes().get(attributeName);
					String value2 = req2.getAttributes().get(attributeName);

					// Check if the values are different, considering null or empty as different
					boolean isDifferent = (value1 == null && value2 != null) || (value1 != null && !value1.equals(value2));

					if (isDifferent) {
						reqIdsWithDifferences.add(req1.getAttributes().get("REQ_ID"));
					}
				}
			}
		}

		return reqIdsWithDifferences;
	}
/*
	public static Map<String, Map<String, List<String>>> performComparison(List<Variant> selectedVariants, List<String> selectedAttributes, boolean onlyShowDifferences) {
		Map<String, Map<String, List<String>>> comparisonResults = new HashMap<>();

		for (Variant variant : selectedVariants) {
			for (Requirement requirement : variant.getRequirements()) {
				String reqId = requirement.getAttributes().get("REQ_ID");

				if (!comparisonResults.containsKey(reqId)) {
					comparisonResults.put(reqId, new HashMap<>());
				}

				Map<String, List<String>> attributeDifferences = comparisonResults.get(reqId);

				for (String attribute : selectedAttributes) {
					if (!attributeDifferences.containsKey(attribute)) {
						attributeDifferences.put(attribute, new ArrayList<>());
					}

					String value = requirement.getAttributes().get(attribute);

					if (onlyShowDifferences) {
						if (!attributeDifferences.get(attribute).contains(value) || attribute.equals("LAST_MODIFIED")) {
							attributeDifferences.get(attribute).add(value);
						}
					} else {
						if (!attributeDifferences.get(attribute).contains(value)) {
							attributeDifferences.get(attribute).add(value);
						}
					}
				}
			}
		}
		return comparisonResults;
	}

	*/
	
	public static Map<String, Map<String, List<String>>> performComparison(List<Variant> selectedVariants, List<String> selectedAttributes, boolean onlyShowDifferences) {
	    Map<String, Map<String, List<String>>> comparisonResults = new HashMap<>();

	    // Create an instance of ComparisonHelper
	    ComparisonHelper helper = new ComparisonHelper();

	    // Call the `findDifferences` method for each pair of selected variants and selected attributes
	    for (Variant variant1 : selectedVariants) {
	        for (Variant variant2 : selectedVariants) {
	            if (!variant1.getVariantName().equals(variant2.getVariantName())) {
	                for (String attribute : selectedAttributes) {
	                    List<String> reqIdsWithDifferences = helper.findDifferences(attribute, variant1.getVariantName(), variant2.getVariantName(), selectedVariants);
	                    for (String reqId : reqIdsWithDifferences) {
	                        if (!comparisonResults.containsKey(reqId)) {
	                            comparisonResults.put(reqId, new HashMap<>());
	                        }
	                        comparisonResults.get(reqId).put(attribute, Arrays.asList(variant1.getVariantName(), variant2.getVariantName()));
	                    }
	                }
	            }
	        }
	    }

	    return comparisonResults;
	}
}