/*The BinaryTree class extends the AbstractTree class
That means that the methods which AbstractTree class implements from
the Tree interface must be implemented here.
A little detail: instead of null we are using A NULL_GUARD node.
A NULL_GUARD node it's a node with the same fields of a normal node but it acts
as a null. When we want to indicate that a node is null, we are using the NULL_GUARD instead.
This help us to implement more easily the functions without having to check for border conditions
all the time. In addition most of the methods are protected instead of private because the AVLTree
which extends BinaryTree will need to call these methods in order to work.*/

package dataStructures;

import java.util.Objects;
import java.util.function.Consumer;

import exceptions.ClassNotComparableException;
import utilities.DoubleIterator;

@SuppressWarnings("unchecked")
public class BinaryTree<K> extends AbstractTree<K>
{
	/* A simple inner class with getter and setters which desribes the node*/
	protected class TreeNode extends Object
	{
		private TreeNode parent;
		private TreeNode left;
		private TreeNode right;
		private K key;
		
		protected TreeNode(K key)
		{
			parent = NULL_GUARD;
			left = NULL_GUARD;
			right = NULL_GUARD;
			this.key = key;
		}
		
		protected void setParent(TreeNode parent)
		{
			this.parent = parent;
		}
		
		protected TreeNode getParent()
		{
			return parent;
		}
		
		protected void setLeft(TreeNode left)
		{
			this.left = left;
		}
		
		protected TreeNode getLeft()
		{
			return left;
		}
		
		protected void setRight(TreeNode right)
		{
			this.right = right;
		}
		
		protected TreeNode getRight()
		{
			return right;
		}
		
		protected void setKey(K key)
		{
			this.key = key;
		}
		
		protected K getKey()
		{
			return key;
		}
	}
	
	private TreeNode root;
	private TreeNode NULL_GUARD;
	private int size;
	
	protected void setRoot(TreeNode root)
	{
		this.root = root;
	}
	
	protected TreeNode getRoot()
	{
		return root;
	}
	
	protected void setNULL_GUARD(TreeNode NULL_GUARD)
	{
		this.NULL_GUARD = NULL_GUARD;
	}
	
	protected TreeNode getNULL_GUARD()
	{
		return NULL_GUARD;
	}
	
	protected void incrementSize()
	{
		size += 1;
	}
	
	protected void decrementSize()
	{
		size -= 1;
	}
	
	protected void initializeSize()
	{
		size = 0;
	}
	
	/* this method must mandatory to be implemented. Otherwise the tree will have undefined behaviours */
	@Override
	protected void initializeTree()
	{
		TreeNode NG =  new TreeNode(null);
		NG.parent = NG;
		NG.left = NG;
		NG.right = NG;
		NULL_GUARD = NG;
		root = NULL_GUARD;
		size = 0;
	}
	
	/* we implemented the add function as a protected because BinaryTree add TreeNodes and
	AVLTree add AVLNodes. If we add into the AVLTree a TreeNode a ClassCastException will be thrown. */
	protected TreeNode add(TreeNode node)
	{
		TreeNode root = this.root;
		TreeNode parent = NULL_GUARD;
		boolean isLeftChild = false;
		
		while(root != NULL_GUARD)
		{
			parent = root;
			
			if(((Comparable<K>)node.key).compareTo(root.key) < 0)
			{
				root = root.left;
				isLeftChild = true;
			}
			else
			{
				root = root.right;
				isLeftChild = false;
			}
		}
		
		node.parent = parent;
		if(parent == NULL_GUARD)
			this.root = node;
		else if(isLeftChild)
			parent.left = node;
		else
			parent.right = node;
		
		return node;
	}
	
	protected TreeNode searchNode(Object key)
	{
		TreeNode root = this.root;
		while(root != NULL_GUARD && !key.equals(root.key))
		{
			if(((Comparable<K>)key).compareTo(root.key) < 0)
				root = root.left;
			else
				root = root.right;
		}
		return root;
	}
	
	protected TreeNode first(TreeNode root)
	{
		while(root.left != NULL_GUARD)
			root = root.left;
		return root;
	}
	
	protected TreeNode last(TreeNode root)
	{
		while(root.right != NULL_GUARD)
			root = root.right;
		return root;
	}
	
	protected TreeNode previous(TreeNode root)
	{
		if(root.left != NULL_GUARD)
			return last(root.left);
		else
		{
			TreeNode parent = root.parent;
			while(parent != NULL_GUARD && root == parent.left)
			{
				root = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	
	protected TreeNode next(TreeNode root)
	{
		if(root.right != NULL_GUARD)
			return first(root.right);
		else
		{
			TreeNode parent = root.parent;
			while(parent != NULL_GUARD && root == parent.right)
			{
				root = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}
	
	protected TreeNode remove(TreeNode root)
	{
		TreeNode current = NULL_GUARD;
		TreeNode child = NULL_GUARD;
		
		if(root.left == NULL_GUARD || root.right == NULL_GUARD)
			current = root;
		else
			current = first(root.right);
		
		if(current.left == NULL_GUARD)
			child = current.right;
		else
			child = current.left;
		
		/*if(child != NULL_GUARD) since we are using null guard
		we don't need this if*/
			child.parent = current.parent;
			
		if(current.parent == NULL_GUARD)
			this.root = child;
		else if(current == current.parent.left)
			current.parent.left = child;
		else
			current.parent.right = child;
		
		if(root != current)
			root.key = current.key;
		
		TreeNode node = current.parent;
		
		current.key = null;
		current = current.parent = current.left = current.right = null;
		
		return node;
	}
	
	protected void clear(TreeNode root)
	{
		if(root == NULL_GUARD)
			return;
		clear(root.left);
		clear(root.right);
		root.key = null;
		root = root.parent = root.left = root.right = null;
	}
	
	/* we created this method for testing purposes to demostrate how to
	implement a function searching for the height of the tree without initially having
	the height information of every node */
	protected int height(TreeNode root)
	{
		if(root == NULL_GUARD)
			return -1;
		else
		{
			int hLeft = height(root.left);
			int hRight = height(root.right);
			return (hLeft > hRight ? hLeft : hRight) + 1;
		}
	}
	
	protected int balanceFactor(TreeNode root)
	{
		return (height(root.right) + 1) - (height(root.left) + 1);
	}
	
	/* In the orderDisplay methods we have to pass an object of Consumer interface.
	We can display the information we want. Also since in the Consumer Interface
	exists only one function, labda expressions are supported */
	private void preOrderDisplay(TreeNode root, Consumer<? super K> action)
	{
		if(root == NULL_GUARD)
			return;
		action.accept(root.key);
		preOrderDisplay(root.left, action);
		preOrderDisplay(root.right, action);
	}
	
	private void inOrderDisplay(TreeNode root, Consumer<? super K> action)
	{
		if(root == NULL_GUARD)
			return;
		inOrderDisplay(root.left, action);
		action.accept(root.key);
		inOrderDisplay(root.right, action);
	}
	
	private void postOrderDisplay(TreeNode root, Consumer<? super K> action)
	{
		if(root == NULL_GUARD)
			return;
		postOrderDisplay(root.left, action);
		postOrderDisplay(root.right, action);
		action.accept(root.key);
	}
	
	public BinaryTree()
	{
		super();
	}
	
	/*The add(K key) methods calls the add(TreeNode node) method. In
	case the key does not support comparisons a ClassNotComparableException object will
	be thrown */
	@Override
	public boolean add(K key)
	{
		if(!(key instanceof Comparable))
			throw new ClassNotComparableException("Class " +
					key.getClass().getCanonicalName() +
					" must implement the interface Comparable");
		add(new TreeNode(key));
		size += 1;
		return true;
	}
	
	@Override
	public boolean search(Object key)
	{
		return searchNode(key) == NULL_GUARD ? false : true;
	}
	
	@Override
	public boolean remove(Object key)
	{
		TreeNode root = searchNode(key);
		if(root == NULL_GUARD)
			return false;
		else
		{
			remove(root);
			size -= 1;
			return true;
		}
	}
	
	@Override
	public K first()
	{
		return first(root).key;
	}
	
	@Override
	public K last()
	{
		return last(root).key;
	}
	
	@Override
	public K previous(K key)
	{
		TreeNode root = searchNode(key);
		return root == NULL_GUARD ? null : previous(root).key;
	}
	
	@Override
	public K next(K key)
	{
		TreeNode root = searchNode(key);
		return root == NULL_GUARD ? null : next(root).key;
	}
	
	@Override
	public K popFirst()
	{
		TreeNode root = first(this.root);
		K key = root.key;
		
		remove(root);
		size -= 1;
		
		return key;
	}
	
	@Override
	public K popLast()
	{
		TreeNode root = last(this.root);
		K key = root.key;
		
		remove(root);
		size -= 1;
		
		return key;
	}
	
	@Override
	public void clear()
	{
		clear(root);
		root = NULL_GUARD;
		size = 0;
	}
	
	@Override
	public int size()
	{
		return size;
	}
	
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	@Override
	public int height()
	{
		return height(root);
	}
	
	@Override
	public int balanceFactor()
	{
		return balanceFactor(root);
	}
	
	@Override
	public K getTreeRoot()
	{
		return root.key;
	}
	
	/* Every OrderDisplay method checks if the action parameter of type Consumer
	is null or not. In case it is null, a NullPointerException will be thrown*/
	@Override
	public void preOrderDisplay(Consumer<? super K> action)
	{
		Objects.requireNonNull(action);
		preOrderDisplay(root, action);
		System.out.println();
	}
	
	@Override
	public void inOrderDisplay(Consumer<? super K> action)
	{
		Objects.requireNonNull(action);
		inOrderDisplay(root, action);
		System.out.println();
	}
	
	@Override
	public void postOrderDisplay(Consumer<? super K> action)
	{
		Objects.requireNonNull(action);
		postOrderDisplay(root, action);
		System.out.println();
	}
	
	@Override
	public DoubleIterator<K> iterator()
	{
		return new DoubleIterator<K>() {
			private TreeNode iterator = first(root);
			
			@Override
			public boolean hasNext()
			{
				return iterator == NULL_GUARD ? false : true;
			}
			
			@Override
			public K next()
			{
				K key = iterator.key;
				iterator = BinaryTree.this.next(iterator);
				return key;
			}
			
			@Override
			public boolean hasPrevious()
			{
				return iterator == NULL_GUARD ? false : true;
			}
			
			@Override
			public K previous()
			{
				K key = iterator.key;
				iterator = BinaryTree.this.previous(iterator);
				return key;
			}
			
			@Override
			public DoubleIterator<K> resetToBegin()
			{
				iterator = first(root);
				return this;
			}
			
			@Override
			public DoubleIterator<K> resetToEnd()
			{
				iterator = last(root);
				return this;
			}
		};
	}
	
	@Override
	public String toString()
	{
		if(size == 0)
			return "{}";
		else if(size == 1)
			return "{" + root.key + "}";
		else
		{
			StringBuilder sb = new StringBuilder("{");
			for(K key : this)
				sb.append(key + ", ");
			sb.setLength(sb.length() - 2);
			sb.append("}");
			return sb.toString();
		}
	}
}