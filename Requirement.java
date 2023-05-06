/*
package com.company.variantcomparatorviewer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Requirement {
	private final Map<String, String> attributes;

	public Requirement() {
		attributes = new HashMap<>();
	}

	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public String getAttribute(String key) {
		return attributes.get(key);
	}

	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}

	public String removeAttribute(String key) {
		return attributes.remove(key);
	}

	public void clearAttributes() {
		attributes.clear();
	}

	@Override
	public String toString() {
		return "Requirement{" +
				"attributes=" + attributes +
				'}';
	}
}
 */

package com.company.variantcomparatorviewer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Requirement {
	private final String requirementId;
	private final String variant1Value;
	private final String variant2Value;
	private final Map<String, String> attributes;

	public Requirement(String requirementId, String variant1Value, String variant2Value) {
		this.requirementId = requirementId;
		this.variant1Value = variant1Value;
		this.variant2Value = variant2Value;
		this.attributes = new HashMap<>();
	}

	public String getRequirementId() {
		return requirementId;
	}

	public String getVariant1Value() {
		return variant1Value;
	}

	public String getVariant2Value() {
		return variant2Value;
	}

	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public String getAttribute(String key) {
		return attributes.get(key);
	}

	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}

	public String removeAttribute(String key) {
		return attributes.remove(key);
	}

	public void clearAttributes() {
		attributes.clear();
	}

	@Override
	public String toString() {
		return "Requirement{" +
				"requirementId='" + requirementId + '\'' +
				", variant1Value='" + variant1Value + '\'' +
				", variant2Value='" + variant2Value + '\'' +
				", attributes=" + attributes +
				'}';
	}
}