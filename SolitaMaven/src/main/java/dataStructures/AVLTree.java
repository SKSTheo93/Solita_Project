package dataStructures;

import exceptions.ClassNotComparableException;

public class AVLTree<K> extends BinaryTree<K>
{
	protected class AVLNode extends TreeNode
	{
		private int height;
		
		protected AVLNode(K key)
		{
			super(key);
			height = 0;
		}
		
		protected AVLNode(K key, int height)
		{
			super(key);
			this.height = height;
		}
		
		protected void setHeight(int height)
		{
			this.height = height;
		}
		
		protected int getHeight()
		{
			return height;
		}
		
		@Override
		protected AVLNode getParent()
		{
			return (AVLNode)super.getParent();
		}
		
		@Override
		protected AVLNode getLeft()
		{
			return (AVLNode)super.getLeft();
		}
		
		@Override
		protected AVLNode getRight()
		{
			return (AVLNode)super.getRight();
		}
	}
	
	private AVLNode NULL_GUARD;
	
	@Override
	protected void setNULL_GUARD(TreeNode NULL_GUARD)
	{
		super.setNULL_GUARD(NULL_GUARD);
		this.NULL_GUARD = (AVLNode)NULL_GUARD;
	}
	
	@Override
	protected AVLNode getNULL_GUARD()
	{
		return (AVLNode)super.getNULL_GUARD();
	}
	
	@Override
	protected AVLNode getRoot()
	{
		return (AVLNode)super.getRoot();
	}
	
	@Override
	protected void initializeTree()
	{
		AVLNode NG = new AVLNode(null, -1);
		NG.setParent(NG);
		NG.setLeft(NG);
		NG.setRight(NG);
		setNULL_GUARD(NG);
		setRoot(getNULL_GUARD());
		initializeSize();
	}
	
	protected void setHeight(AVLNode root)
	{
		int hLeft = root.getLeft().height;
		int hRight = root.getRight().height;
		root.height = (hLeft > hRight ? hLeft : hRight) + 1;
	}
	
	protected void leftRotation(AVLNode root)
	{
		AVLNode parent = root.getParent();
		
		AVLNode pivot = root.getRight();
		root.setRight(pivot.getLeft());
		if(pivot.getLeft() != NULL_GUARD)
			pivot.getLeft().setParent(root);
		
		pivot.setParent(parent);
		if(parent == NULL_GUARD)
			setRoot(pivot);
		else if(root == parent.getLeft())
			parent.setLeft(pivot);
		else
			parent.setRight(pivot);
		
		pivot.setLeft(root);
		root.setParent(pivot);
		
		setHeight(root);
		setHeight(pivot);
	}
	
	protected void rightRotation(AVLNode root)
	{
		AVLNode parent = root.getParent();
		
		AVLNode pivot = root.getLeft();
		root.setLeft(pivot.getRight());
		if(pivot.getRight() != NULL_GUARD)
			pivot.getRight().setParent(root);
		
		pivot.setParent(parent);
		if(parent == NULL_GUARD)
			setRoot(pivot);
		else if(root == parent.getLeft())
			parent.setLeft(pivot);
		else
			parent.setRight(pivot);
		
		pivot.setRight(root);
		root.setParent(pivot);
		
		setHeight(root);
		setHeight(pivot);
	}
	
	protected void doubleLeftRotation(AVLNode root)
	{
		rightRotation(root.getRight());
		leftRotation(root);
	}
	
	protected void doubleRightRotation(AVLNode root)
	{
		leftRotation(root.getLeft());
		rightRotation(root);
	}
	
	@Override
	protected int balanceFactor(TreeNode node)
	{
		AVLNode root = (AVLNode)node;
		return (root.getRight().height + 1) - (root.getLeft().height + 1);
	}
	
	@Override
	protected int height(TreeNode root)
	{
		return ((AVLNode)root).height;
	}
	
	protected void checkBalance(AVLNode root)
	{
		int bf = balanceFactor(root);
		
		if(bf >= 2)
		{
			if(balanceFactor(root.getRight()) == -1)
				doubleLeftRotation(root);
			else
				leftRotation(root);
		}
		else if(bf <= -2)
		{
			if(balanceFactor(root.getLeft()) == 1)
				doubleRightRotation(root);
			else
				rightRotation(root);
		}
	}
	
	protected void autoBalance(AVLNode root)
	{
		while(root != NULL_GUARD)
		{
			checkBalance(root);
			setHeight(root);
			root = root.getParent();
		}
	}
	
	public AVLTree()
	{
		super();
	}
	
	@Override
	public boolean add(K key)
	{
		if(!(key instanceof Comparable))
			throw new ClassNotComparableException("Class " +
					key.getClass().getCanonicalName() +
					" must implement the interface Comparable");
		autoBalance((AVLNode)add(new AVLNode(key)));
		incrementSize();
		return true;
	}
	
	@Override
	public boolean remove(Object key)
	{
		AVLNode root = (AVLNode)searchNode(key);
		if(root == NULL_GUARD)
			return false;
		else
		{
			autoBalance((AVLNode)remove(root));
			decrementSize();
			return true;
		}
	}
	
	@Override
	public K popFirst()
	{
		AVLNode root = (AVLNode)first(this.getRoot());
		K key = root.getKey();
		
		autoBalance((AVLNode)remove(root));
		decrementSize();
		
		return key;
	}
	
	@Override
	public K popLast()
	{
		AVLNode root = (AVLNode)last(this.getRoot());
		K key = root.getKey();
		
		autoBalance((AVLNode)remove(root));
		decrementSize();
		
		return key;
	}
}