package com.company.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;

public class BufferReader {

	@SneakyThrows
	public static List<String> splitTheCSV(String pathToCSV) {
		String delimiter = ";";
		List<List<String>> cardsFromTheCSV = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(pathToCSV))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(delimiter);
				cardsFromTheCSV.add(Arrays.asList(values));
			}
		}
		return cardsFromTheCSV.get(1);
	}
}
