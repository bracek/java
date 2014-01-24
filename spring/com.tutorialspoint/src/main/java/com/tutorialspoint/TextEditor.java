package com.tutorialspoint;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
	@Autowired
	private SpellChecker spellChecker;

	// @Autowired
	// public void setSpellChecker(final SpellChecker spellChecker) {
	// System.out.println("Inside TextEditor constructor.");
	// this.spellChecker = spellChecker;
	// }

	public SpellChecker getSpellChecker() {
		return spellChecker;
	}

	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
