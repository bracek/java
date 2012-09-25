package com.javapapers.designpattern.composite;

public final class ImplementComposite {
	public static void main(String[] args) {
		// Initialize three blocks
        final Block block1 = new Block();
        final Block block2 = new Block();
        final Block block3 = new Block();

		// Initialize three structure
        final Structure structure = new Structure();
        final Structure structure1 = new Structure();
        final Structure structure2 = new Structure();

		// Composes the groups
		structure1.add(block1);
		structure1.add(block2);

		structure2.add(block3);

		structure.add(structure1);
		structure.add(structure2);

		structure.assemble();
	}
}