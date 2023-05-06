package com.company.variantcomparatorviewer;

import java.util.LinkedList;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

public class HtmlDiffGenerator {

	public static String generateHtmlDiff(String text1, String text2) {
		if (text1 == null || text2 == null) {
			return "<html><span style='color:red;'>Error: One or both inputs are null.</span></html>";
		}

		diff_match_patch dmp = new diff_match_patch();
		LinkedList<Diff> diffs = dmp.diff_main(text1, text2);
		dmp.diff_cleanupSemantic(diffs);
		return diffsToHtml(diffs);
	}

	private static String diffsToHtml(LinkedList<Diff> diffs) {
		StringBuilder html = new StringBuilder("<html>");
		for (Diff diff : diffs) {
			switch (diff.operation) {
			case INSERT:
				html.append("<span style='color:green;'>").append(diff.text).append("</span>");
				break;
			case DELETE:
				html.append("<span style='color:red;'>").append(diff.text).append("</span>");
				break;
			case EQUAL:
				html.append(diff.text);
				break;
			}
		}
		html.append("</html>");
		return html.toString();
	}
}