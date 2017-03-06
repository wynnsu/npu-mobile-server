package edu.npu.cs595.crawler;

import java.text.ParseException;
import java.util.List;

import org.jsoup.nodes.Document;

public interface Parser<E> {

	public List<E> parseDocument(Document doc) throws ParseException;
}
