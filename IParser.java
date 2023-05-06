package com.company.variantcomparatorviewer;

import java.util.List;

public interface IParser {
	List<Variant> parseFiles(List<String> filePaths) throws ParsingException;
}