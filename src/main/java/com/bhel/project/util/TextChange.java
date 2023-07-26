package com.bhel.project.util;

public class TextChange {
	public String textChange(String sentence) {
		// String sentence = "geeks For . geeks Internship!";
//		String modifiedSentence = sentence.replaceAll("\\s+", " ").trim();
//		String[] words = modifiedSentence.toLowerCase().split(" ");
//		StringBuilder result = new StringBuilder();
//
//		boolean capitalizeNext = true;
//		for (String word : words) {
//			if (!word.isEmpty()) {
//				if (capitalizeNext) {
//					result.append(Character.toUpperCase(word.charAt(0)));
//					result.append(word.substring(1));
//					capitalizeNext = false;
//				} else {
//					result.append(word);
//				}
//
//				if (word.contains(".")) {
//					capitalizeNext = true;
//				}
//
//				result.append(" ");
//			}
//		}
//
//		String finalSentence = result.toString().trim();
//		System.out.println(finalSentence);

		String modifiedSentence = sentence.replaceAll("\\s+", " ").trim();
        String[] words = modifiedSentence.toLowerCase().split(" ");
        StringBuilder result = new StringBuilder();

        boolean capitalizeNext = true;
        for (String word : words) {
            if (!word.isEmpty()) {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(word.charAt(0)));
                    result.append(word.substring(1));
                    capitalizeNext = false;
                } else {
                    result.append(word);
                }

                if (word.contains(".")) {
                    capitalizeNext = true;
                }

                result.append(" ");
            }
        }

        String finalSentence = result.toString().trim();
        if (!finalSentence.endsWith(".")) {
            finalSentence += ".";
        }
        System.out.println(finalSentence);
		return finalSentence;
	}
}
