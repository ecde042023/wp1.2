package com.company.variantcomparatorviewer;

import java.util.List;
import javax.swing.*;

public class PopupManager {

	public static void showDifferencesPopup(JFrame frame, String reqId, String attribute, List<String> selectedVariants, List<Variant> variants) {
		// showDifferencesPopup method code will remain unchanged
		JDialog differencesDialog = new JDialog(frame, "Differences: " + reqId + " - " + attribute, true);
		differencesDialog.setSize(800, 400);
		differencesDialog.setLocationRelativeTo(frame);

		JTabbedPane tabbedPane = new JTabbedPane();

		for (String variantName1 : selectedVariants) {
			for (String variantName2 : selectedVariants) {
				if (!variantName1.equals(variantName2)) {
					Requirement req1 = findRequirementById(getVariantByName(variantName1, variants).getRequirements(), reqId);
					Requirement req2 = findRequirementById(getVariantByName(variantName2, variants).getRequirements(), reqId);
					if (req1 != null && req2 != null) {
						String value1 = req1.getAttributes().get(attribute);
						String value2 = req2.getAttributes().get(attribute);
						if (value1 != null && !value1.equals(value2)) {
							String tabTitle = variantName1 + " | " + variantName2;
							JTextArea differencesTextArea = new JTextArea();
							differencesTextArea.setEditable(false);
							differencesTextArea.setText("Differences between " + variantName1 + " and " + variantName2 + ":\n\n" + value1 + "\n\n" + value2);
							differencesTextArea.setCaretPosition(0);
							JScrollPane scrollPane = new JScrollPane(differencesTextArea);
							tabbedPane.addTab(tabTitle, scrollPane);
						}
					}
				}
			}
		}

		differencesDialog.add(tabbedPane);
		differencesDialog.setVisible(true);
	}

	public static void showComparisonPopup(JFrame frame, List<Variant> selectedVariants, String reqId, String attribute) {
		// showComparisonPopup method code will remain unchanged
		JDialog comparisonDialog = new JDialog(frame, "Comparison of " + reqId + " - " + attribute, true);
		comparisonDialog.setSize(800, 600);
		comparisonDialog.setLocationRelativeTo(frame);

		JTabbedPane tabbedPane = new JTabbedPane();

		for (int i = 0; i < selectedVariants.size(); i++) {
			for (int j = i + 1; j < selectedVariants.size(); j++) {
				Variant variant1 = selectedVariants.get(i);
				Variant variant2 = selectedVariants.get(j);

				String value1 = getAttributeValue(variant1, reqId, attribute);
				String value2 = getAttributeValue(variant2, reqId, attribute);

				JTextPane textPane = new JTextPane();
				textPane.setContentType("text/html");
				textPane.setText(HtmlDiffGenerator.generateHtmlDiff(value1, value2));
				textPane.setEditable(false);

				JScrollPane scrollPane = new JScrollPane(textPane);
				tabbedPane.addTab(variant1.getVariantName() + " vs " + variant2.getVariantName(), scrollPane);
			}
		}

		comparisonDialog.add(tabbedPane);
		comparisonDialog.setVisible(true);
	}

/*
	public static Requirement findRequirementById(List<Requirement> requirements, String reqId) {
		return requirements.stream().filter(req -> req.getAttributes().get("REQ_ID").equals(reqId)).findFirst().orElse(null);
	}
*/
	
	
	public static Requirement findRequirementById(List<Requirement> requirements, String reqId) {
	    if (reqId == null) {
	        return null;
	    }
	    return requirements.stream()
	            .filter(r -> reqId.equals(r.getAttributes().get("REQ_ID")))
	            .findFirst()
	            .orElse(null);
	}

	
	public static Variant getVariantByName(String variantName, List<Variant> variants) {
		return variants.stream().filter(v -> v.getVariantName().equals(variantName)).findFirst().orElse(null);
	}

	public static String getAttributeValue(Variant variant, String reqId, String attribute) {
		for (Requirement req : variant.getRequirements()) {
			String reqIdAttributeValue = req.getAttributes().get("REQ_ID");
			if (reqIdAttributeValue != null && reqIdAttributeValue.equals(reqId)) {
				String attributeValue = req.getAttributes().get(attribute);
				return attributeValue != null ? attributeValue : "";
			}
		}
		return "";
	}
}