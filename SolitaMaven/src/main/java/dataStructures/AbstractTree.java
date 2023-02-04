package dataStructures;

import utilities.Tree;

public abstract class AbstractTree<K> extends Object implements Tree<K>
{
	public AbstractTree()
	{
		//Everytime when we create a tree, the initializeTree function is called from the child trees
		initializeTree();
	}
	
	protected abstract void initializeTree();
}