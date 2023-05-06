package com.company.variantcomparatorviewer;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser implements IParser {

	@Override
	public List<Variant> parseFiles(List<String> filePaths) throws ParsingException {
		List<Variant> variants = new ArrayList<>();

		for (String path : filePaths) {
			try {
				File file = new File(path);
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
				document.getDocumentElement().normalize();

				Variant variant = VariantParser.parseVariant(document);
				variants.add(variant);
			} catch (ParserConfigurationException | SAXException | IOException e) {
				throw new ParsingException("Error parsing file: " + path, e);
			}
		}
		return variants;
	}
}