package utilities;

import java.util.function.Consumer;

public interface Tree<K> extends DoubleIterable<K>
{
	public abstract boolean add(K key);
	public abstract boolean search(Object key);
	public abstract boolean remove(Object key);
	public abstract K first();
	public abstract K last();
	public abstract K previous(K key);
	public abstract K next(K key);
	public abstract K popFirst();
	public abstract K popLast();
	public abstract void clear();
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract int height();
	public abstract int balanceFactor();
	public abstract K getTreeRoot();
	public abstract K select(int index);
	public abstract int getIndex(K key);
	
	public abstract void preOrderDisplay(Consumer<? super K> action);
	public abstract void inOrderDisplay(Consumer<? super K> action);
	public abstract void postOrderDisplay(Consumer<? super K> action);
}