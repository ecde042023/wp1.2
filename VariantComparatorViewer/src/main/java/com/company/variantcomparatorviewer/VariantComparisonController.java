package com.company.variantcomparatorviewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VariantComparisonController {

	private VariantComparisonModel model;
	private VariantComparisonView view;

	public VariantComparisonController(VariantComparisonModel model, VariantComparisonView view) {
		this.model = model;
		this.view = view;

		initComponents();
	}

	private void initComponents() {
		for (Variant variant : model.getVariants()) {
			JButton button = new JButton(variant.getVariantName());
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showDifferences(variant);
				}
			});
			view.addLeftPanelComponent(button);
		}
	}

	private void showDifferences(Variant variant) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Requirement ID");
		tableModel.addColumn("Variant 1");
		tableModel.addColumn("Variant 2");

		List<Requirement> differences = findDifferences(variant, model.getVariants());
		for (Requirement difference : differences) {
			tableModel.addRow(new Object[]{
					difference.getRequirementId(),
					difference.getVariant1Value(),
					difference.getVariant2Value()
			});
		}

		view.updateRightPanel(tableModel);
	}

	private List<Requirement> findDifferences(Variant variant, List<Variant> otherVariants) {
		List<Requirement> differences = new ArrayList<>();

		for (Variant otherVariant : otherVariants) {
			if (otherVariant != variant) {
				for (Requirement requirement1 : variant.getRequirements()) {
					Requirement requirement2 = findRequirementById(requirement1.getRequirementId(), otherVariant.getRequirements());
					if (requirement2 != null && !requirement1.getVariant1Value().equals(requirement2.getVariant2Value())) {
						differences.add(new Requirement(
								requirement1.getRequirementId(),
								requirement1.getVariant1Value(),
								requirement2.getVariant2Value()
								));
					}
				}
			}
		}

		return differences;
	}

	private Requirement findRequirementById(String id, List<Requirement> requirements) {
		for (Requirement requirement : requirements) {
			if (requirement.getRequirementId().equals(id)) {
				return requirement;
			}
		}
		return null;
	}
}