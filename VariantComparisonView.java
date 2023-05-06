package com.company.variantcomparatorviewer;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VariantComparisonView {

	private JFrame frame;
	private JPanel leftPanel;
	private JPanel rightPanel;

	public VariantComparisonView() {
		frame = new JFrame("Variant Comparator Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);

		JSplitPane splitPane = new JSplitPane();
		frame.add(splitPane);

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JScrollPane leftScrollPane = new JScrollPane(leftPanel);
		splitPane.setLeftComponent(leftScrollPane);

		rightPanel = new JPanel(new BorderLayout());
		JScrollPane rightScrollPane = new JScrollPane(rightPanel);
		splitPane.setRightComponent(rightScrollPane);
	}

	public void display() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void addLeftPanelComponent(Component component) {
		leftPanel.add(component);
	}

	public void updateRightPanel(DefaultTableModel tableModel) {
		rightPanel.removeAll();
		JTable differencesTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(differencesTable);
		rightPanel.add(scrollPane, BorderLayout.CENTER);
		rightPanel.revalidate();
		rightPanel.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}
}