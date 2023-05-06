package com.company.variantcomparatorviewer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class VariantComparatorViewer {
	private JFrame frame;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private List<Variant> variants;
	private List<JCheckBox> variantCheckBoxes;
	private List<JCheckBox> attributeCheckBoxes;
	private JCheckBox onlyShowDifferencesCheckBox;
	private DefaultTableModel tableModel;

	public VariantComparatorViewer(List<Variant> variants) {
		this.variants = variants;
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		frame = new JFrame("Variant Comparator Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1400, 900));
		frame.setLocationRelativeTo(null);

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JScrollPane leftScrollPane = new JScrollPane(leftPanel);
		leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		leftScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		leftScrollPane.setPreferredSize(new Dimension(300, 600));

		rightPanel = new JPanel(new BorderLayout());
		JScrollPane rightScrollPane = new JScrollPane(rightPanel);
		rightScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rightScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		rightScrollPane.setPreferredSize(new Dimension(900, 600));

		populateLeftPanel();

		//2 add initialize
	    // Initialize tableModel
	    tableModel = new DefaultTableModel();
	    tableModel.addColumn("REQ_ID");
	    for (Variant variant : variants) {
	        tableModel.addColumn(variant.getVariantName());
	    }
		
		
		List<Variant> selectedVariants = getSelectedVariants();
		List<String> selectedAttributes = getSelectedAttributes();
		boolean onlyShowDifferences = onlyShowDifferencesCheckBox.isSelected();
		//2
		//compareVariants(selectedVariants, selectedAttributes, onlyShowDifferences);
	    compareVariants(selectedVariants, selectedAttributes, onlyShowDifferences, tableModel);


		frame.getContentPane().add(leftScrollPane, BorderLayout.WEST);
		frame.getContentPane().add(rightScrollPane, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	private List<Variant> getSelectedVariants() {
		List<Variant> selectedVariants = new ArrayList<>();
		for (int i = 0; i < variantCheckBoxes.size(); i++) {
			JCheckBox checkBox = variantCheckBoxes.get(i);
			if (checkBox.isSelected()) {
				selectedVariants.add(variants.get(i));
			}
		}
		return selectedVariants;
	}

	private List<String> getSelectedAttributes() {
		List<String> selectedAttributes = new ArrayList<>();
		for (int i = 0; i < attributeCheckBoxes.size(); i++) {
			JCheckBox checkBox = attributeCheckBoxes.get(i);
			if (checkBox.isSelected()) {
				selectedAttributes.add(checkBox.getText());
			}
		}
		return selectedAttributes;
	}

	private List<String> getAttributeNames() {
		return Arrays.asList(
				"PARENT_REQ_ID", "TYPE", "TITLE", "OBJECT_HEADING", "OBJECT_TEXT", "PICTURE_PATH", "INPUT_DETAIL", "OUTPUT_DETAIL", "NOTES", "RESPONSE_TIME", "UPDATE_RATE", "ACCURACY", "FUNCTIONAL_AREA", "L2_TRACEABILITY", "L2_TRACEABILITY_CONFIG", "STM_UNIQUE_ID", "SECURITY_CLASSIFICATION", "ITAR_LICENSE", "MEANS_OF_VERIFICATION", "SCA_HISTORY", "LAST_MODIFIED"
				);
	} 

	private void populateLeftPanel() {
		// Variants checkboxes
		JPanel variantsPanel = new JPanel();
		variantsPanel.setLayout(new BoxLayout(variantsPanel, BoxLayout.Y_AXIS));
		variantsPanel.setBorder(BorderFactory.createTitledBorder("Variants"));

		variantCheckBoxes = new ArrayList<>();
		for (Variant variant : variants) {
			JCheckBox checkBox = new JCheckBox(variant.getVariantName());
			checkBox.setSelected(true);
			checkBox.putClientProperty("variant", variant); // Set the client property
			variantCheckBoxes.add(checkBox);
			variantsPanel.add(checkBox);

			// Add ItemListener to the variantCheckBox
			checkBox.addItemListener(e -> {
				List<Variant> selectedVariants = getSelectedVariants();
				List<String> selectedAttributes = getSelectedAttributes();
				boolean onlyShowDifferences = onlyShowDifferencesCheckBox.isSelected();
				
				//2
			    compareVariants(selectedVariants, selectedAttributes, onlyShowDifferences, tableModel);
				
				//
				//ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
				
			    /*2
				//ComparisonHelper helper = new ComparisonHelper();
				Map<String, Map<String, List<String>>> comparisonResults = ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
*/
				
			});
		}

		// Attributes checkboxes
		JPanel attributesPanel = new JPanel();
		attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));
		attributesPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));

		attributeCheckBoxes = new ArrayList<>();
		List<String> attributeNames = getAttributeNames();
		for (String attributeName : attributeNames) {
			JCheckBox checkBox = new JCheckBox(attributeName);

			if (attributeName.equals("LAST_MODIFIED")) {
				checkBox.setSelected(false);
			} else {
				checkBox.setSelected(true);
			}

			attributeCheckBoxes.add(checkBox);
			attributesPanel.add(checkBox);

			// Add ItemListener to the attributeCheckBox
			checkBox.addItemListener(e -> {
				List<Variant> selectedVariants = getSelectedVariants();
				List<String> selectedAttributes = getSelectedAttributes();
				boolean onlyShowDifferences = onlyShowDifferencesCheckBox.isSelected();
				
				//2
			    compareVariants(selectedVariants, selectedAttributes, onlyShowDifferences, tableModel);
				
				//
				//ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
				
				/*2
				//ComparisonHelper helper = new ComparisonHelper();
				Map<String, Map<String, List<String>>> comparisonResults = ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
*/
				
				
			});
		}

		// "Only show differences" checkbox
		onlyShowDifferencesCheckBox = new JCheckBox("Only show differences");
		onlyShowDifferencesCheckBox.setSelected(true);
		onlyShowDifferencesCheckBox.addItemListener(e -> {
			List<Variant> selectedVariants = getSelectedVariants();
			List<String> selectedAttributes = getSelectedAttributes();
			boolean onlyShowDifferences = onlyShowDifferencesCheckBox.isSelected();
			
			//2
		    compareVariants(selectedVariants, selectedAttributes, onlyShowDifferences, tableModel);

			
			//			
			//ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
			
			/*2
			//ComparisonHelper helper = new ComparisonHelper();
			Map<String, Map<String, List<String>>> comparisonResults = ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
*/
			
		});

		// Add components to the left panel
		leftPanel.add(variantsPanel);
		leftPanel.add(attributesPanel);
		leftPanel.add(onlyShowDifferencesCheckBox);
	}

	private void populateRightPanel(Map<String, Map<String, List<String>>> comparisonResults) {

		rightPanel.removeAll();

		// Create a table model for the comparison results
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("REQ_ID");
		tableModel.addColumn("Attribute");

		for (Map.Entry<String, Map<String, List<String>>> entry : comparisonResults.entrySet()) {
			String reqId = entry.getKey();
			Map<String, List<String>> attributeDifferences = entry.getValue();

			for (String attribute : attributeDifferences.keySet()) {
				tableModel.addRow(new Object[]{reqId, attribute});
			}
		}

		// Create the table and set the table model
		JTable table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(800, 500));
		table.setFillsViewportHeight(true);

		// Add a mouse listener to handle row clicks
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = table.rowAtPoint(e.getPoint());
					String selectedReqId = (String) table.getValueAt(row, 0);
					String selectedAttribute = (String) table.getValueAt(row, 1);

					List<Variant> selectedVariants = new ArrayList<>();
					for (JCheckBox cb : variantCheckBoxes) {
						if (cb.isSelected()) {
							selectedVariants.add((Variant) cb.getClientProperty("variant"));
						}
					}

					//showComparisonPopup(selectedVariants, selectedReqId, selectedAttribute);
					PopupManager.showComparisonPopup(frame, selectedVariants, selectedReqId, selectedAttribute);
				}
			}
		});

		// Add the table to a scroll pane and add it to the right panel
		JScrollPane scrollPane = new JScrollPane(table);
		rightPanel.add(scrollPane, BorderLayout.CENTER);

		rightPanel.revalidate();
		rightPanel.repaint();
	}

	@SuppressWarnings("unused")
	private void updateRightPanel() {
		// Clear the right panel and repopulate based on the selected checkboxes
		rightPanel.removeAll();

		// Get the selected checkboxes for variants and attributes
		List<String> selectedVariants = new ArrayList<>();
		List<String> selectedAttributes = new ArrayList<>();
		boolean onlyShowDifferences = false;

		for (Component component : leftPanel.getComponents()) {
			if (component instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) component;
				if (checkBox.isSelected()) {
					if (checkBox.getText().equals("Only show differences")) {
						onlyShowDifferences = true;
					} else {
						// Add the selected variants and attributes to their respective lists
						boolean isVariant = variants.stream().anyMatch(v -> v.getVariantName().equals(checkBox.getText()));
						if (isVariant) {
							selectedVariants.add(checkBox.getText());
						} else {
							selectedAttributes.add(checkBox.getText());
						}
					}
				}
			}
		}

		// Generate the right panel based on the selected variants, attributes, and "onlyShowDifferences" setting
		DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"REQ_ID", "Attribute"}, 0);

		JTable differencesTable = new JTable(tableModel);

		if (onlyShowDifferences) {
			//
	        ComparisonHelper helper = new ComparisonHelper(); // Create an instance of ComparisonHelper

			for (String attributeName : selectedAttributes) {
				for (String variantName1 : selectedVariants) {
					for (String variantName2 : selectedVariants) {
						if (!variantName1.equals(variantName2)) {
							//List<String> reqIdsWithDifferences = findDifferences(attributeName, variantName1, variantName2);
	                        List<String> reqIdsWithDifferences = helper.findDifferences(attributeName, variantName1, variantName2, variants); // Use the instance to call findDifferences
							for (String reqId : reqIdsWithDifferences) {
								tableModel.addRow(new Object[]{reqId, attributeName});
							}
						}
					}
				}
			}
		}

		differencesTable.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int row = differencesTable.getSelectedRow();
				if (row != -1) {
					String reqId = (String) tableModel.getValueAt(row, 0);
					String attribute = (String) tableModel.getValueAt(row, 1);
					//showDifferencesPopup(reqId, attribute, selectedVariants);
					PopupManager.showDifferencesPopup(frame, reqId, attribute, selectedVariants, variants);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(differencesTable);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		rightPanel.revalidate();
		rightPanel.repaint();
	}

	//2
	private void updateTableModel(DefaultTableModel tableModel, Map<String, Map<String, List<String>>> comparisonResults) {
	    // Remove all rows from the table model
	    tableModel.setRowCount(0);

	    // Add rows with the new data
	    for (Map.Entry<String, Map<String, List<String>>> entry : comparisonResults.entrySet()) {
	        String reqId = entry.getKey();
	        Map<String, List<String>> attributeComparisons = entry.getValue();
	        for (Map.Entry<String, List<String>> attributeComparison : attributeComparisons.entrySet()) {
	            String attributeName = attributeComparison.getKey();
	            List<String> variantNames = attributeComparison.getValue();
	            tableModel.addRow(new Object[]{reqId, attributeName, variantNames.get(0), variantNames.get(1)});
	        }
	    }
	}

	/*2
	private void compareVariants(List<Variant> selectedVariants, List<String> selectedAttributes, boolean onlyShowDifferences) {
		// Clear the right panel
		rightPanel.removeAll();

		for (JCheckBox cb : variantCheckBoxes) {
			if (cb.isSelected()) {
				selectedVariants.add((Variant) cb.getClientProperty("variant"));
			}
		}

		for (JCheckBox cb : attributeCheckBoxes) {
			if (cb.isSelected()) {
				selectedAttributes.add(cb.getText());
			}
		}

		//
		//Map<String, Map<String, List<String>>> comparisonResults = ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);
		
		
		//ComparisonHelper helper = new ComparisonHelper();
		Map<String, Map<String, List<String>>> comparisonResults = ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);

		
		populateRightPanel(comparisonResults);
	}
	*/
	
	
	private void compareVariants(List<Variant> selectedVariants, List<String> selectedAttributes, boolean onlyShowDifferences, DefaultTableModel tableModel) {
	    // Clear the right panel
	    rightPanel.removeAll();

	    Map<String, Map<String, List<String>>> comparisonResults = ComparisonHelper.performComparison(selectedVariants, selectedAttributes, onlyShowDifferences);

	    // Update the table model with the new data
	    updateTableModel(tableModel, comparisonResults);

	    populateRightPanel(comparisonResults);
	}


	public static void main(String[] args) {
		String folderPath = "K:\\Muenchen\\AIRBUS_DEFENCE_AND_S\\A024100131984_SOFTWARE_AUTOMATION_EUROFIGHT\\02_Projektdaten\\01_Eingang\\WP_1-2";
		File folder = new File(folderPath);
		File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
		List<String> filePaths = new ArrayList<>();
		if (files != null) {
			for (File file : files) {
				filePaths.add(file.getPath());
			}
		}

		IParser parser = new XmlParser();
		AtomicReference<List<Variant>> variantsRef = new AtomicReference<>();
		try {
			variantsRef.set(parser.parseFiles(filePaths));
		} catch (ParsingException e) {
			e.printStackTrace();
			System.exit(1);
		}

		//
		//SwingUtilities.invokeLater(() -> new VariantComparatorViewerSINDIVIDIR(variantsRef.get()));
		
		//quit the line before and add all this
        SwingUtilities.invokeLater(() -> {
            List<Variant> variants = variantsRef.get();
            if (variants != null && !variants.isEmpty()) {
                new VariantComparatorViewer(variants);
            } else {
                System.err.println("Error: No valid XML files were found.");
                System.exit(1);
            }
        });
	}
}
