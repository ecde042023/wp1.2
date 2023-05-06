/*
package com.company.variantcomparatorviewer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VariantParser {
	public static Variant parseVariant(Document document) {
		Variant variant = new Variant();
		NodeList moduleProperties = document.getElementsByTagName("MODULE_PROPERTIES");

		if (moduleProperties.getLength() > 0) {
			Element propertiesElement = (Element) moduleProperties.item(0);
			variant.setVariantName(propertiesElement.getElementsByTagName("VARIANT").item(0).getTextContent());
		}

		NodeList objects = document.getElementsByTagName("OBJECT");
		for (int i = 0; i < objects.getLength(); i++) {
			Node objectNode = objects.item(i);
			if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
				Element objectElement = (Element) objectNode;
				Requirement requirement = parseRequirement(objectElement);
				variant.addRequirement(requirement);
			}
		}

		return variant;
	}

	private static Requirement parseRequirement(Element objectElement) {
		Requirement requirement = new Requirement();
		NodeList objectChildNodes = objectElement.getChildNodes();

		for (int j = 0; j < objectChildNodes.getLength(); j++) {
			Node attributeNode = objectChildNodes.item(j);
			if (attributeNode.getNodeType() == Node.ELEMENT_NODE) {
				String attributeName = attributeNode.getNodeName();
				String attributeValue = attributeNode.getTextContent();
				requirement.setAttribute(attributeName, attributeValue);
			}
		}

		return requirement;
	}
}*/

package com.company.variantcomparatorviewer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VariantParser {
	public static Variant parseVariant(Document document) {
		Variant variant = new Variant();
		NodeList moduleProperties = document.getElementsByTagName("MODULE_PROPERTIES");

		if (moduleProperties.getLength() > 0) {
			Element propertiesElement = (Element) moduleProperties.item(0);
			variant.setVariantName(propertiesElement.getElementsByTagName("VARIANT").item(0).getTextContent());
		}

		NodeList objects = document.getElementsByTagName("OBJECT");
		for (int i = 0; i < objects.getLength(); i++) {
			Node objectNode = objects.item(i);
			if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
				Element objectElement = (Element) objectNode;
				Requirement requirement = parseRequirement(objectElement);
				variant.addRequirement(requirement);
			}
		}

		return variant;
	}

	private static Requirement parseRequirement(Element objectElement) {
		String requirementId = "";
		String variant1Value = "";
		String variant2Value = "";

		NodeList objectChildNodes = objectElement.getChildNodes();

		for (int j = 0; j < objectChildNodes.getLength(); j++) {
			Node attributeNode = objectChildNodes.item(j);
			if (attributeNode.getNodeType() == Node.ELEMENT_NODE) {
				String attributeName = attributeNode.getNodeName();
				String attributeValue = attributeNode.getTextContent();
				if ("REQUIREMENT_ID".equals(attributeName)) {
					requirementId = attributeValue;
				} else if ("VARIANT1_VALUE".equals(attributeName)) {
					variant1Value = attributeValue;
				} else if ("VARIANT2_VALUE".equals(attributeName)) {
					variant2Value = attributeValue;
				}
			}
		}

		return new Requirement(requirementId, variant1Value, variant2Value);
	}
}