package com.company.variantcomparatorviewer;

import java.util.List;

public interface ComparisonStrategy {
    List<String> findDifferences(String attributeName, String variantName1, String variantName2, List<Variant> variants);
}