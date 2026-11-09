package inaki.sw.lib.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class TreeNodeTest {

	@Test
	void rootNodeStartsAsRootAndLeaf() {
		TreeNode<String> root = new TreeNode<>("root");

		assertTrue(root.isRoot());
		assertTrue(root.isLeaf());
		assertEquals(0, root.getLevel());
		assertTrue(root.getChildren().isEmpty());
		assertEquals("root", root.toString());
	}

	@Test
	void addChildCreatesParentChildRelationship() {
		TreeNode<String> root = new TreeNode<>("root");
		TreeNode<String> child = root.addChild("child");
		TreeNode<String> grandChild = child.addChild("grand-child");

		assertFalse(root.isLeaf());
		assertEquals(root, child.getParent());
		assertEquals(child, grandChild.getParent());
		assertEquals(1, child.getLevel());
		assertEquals(2, grandChild.getLevel());
		assertTrue(root.getChildren().contains(child));
		assertTrue(child.getChildren().contains(grandChild));
	}

	@Test
	void findTreeNodeReturnsMatchingDescendant() {
		TreeNode<Integer> root = new TreeNode<>(1);
		TreeNode<Integer> left = root.addChild(2);
		TreeNode<Integer> right = root.addChild(3);
		TreeNode<Integer> target = left.addChild(4);
		right.addChild(5);

		Comparable<Integer> comparable = other -> Integer.compare(other, 4);

		assertEquals(target, root.findTreeNode(comparable));
	}

	@Test
	void iteratorVisitsNodeBeforeDescendants() {
		TreeNode<String> root = new TreeNode<>("root");
		TreeNode<String> left = root.addChild("left");
		root.addChild("right");
		left.addChild("left-child");

		Iterator<TreeNode<String>> iterator = root.iterator();

		assertTrue(iterator.hasNext());
		assertEquals("root", iterator.next().getData());
		assertTrue(iterator.hasNext());
		assertEquals("left", iterator.next().getData());
		assertTrue(iterator.hasNext());
		assertEquals("left-child", iterator.next().getData());
		assertTrue(iterator.hasNext());
		assertEquals("right", iterator.next().getData());
		assertFalse(iterator.hasNext());
	}

	@Test
	void iteratorRemoveThrowsUnsupportedOperationException() {
		Iterator<TreeNode<String>> iterator = new TreeNode<>("root").iterator();

		assertThrows(UnsupportedOperationException.class, iterator::remove);
	}

	@Test
	void iteratorNextThrowsNoSuchElementExceptionWhenExhausted() {
		Iterator<TreeNode<String>> iterator = new TreeNode<>("root").iterator();
		iterator.next();

		assertThrows(NoSuchElementException.class, iterator::next);
	}
}
