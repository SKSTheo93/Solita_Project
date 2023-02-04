/*An interface that extends the Interface Iterator<E>
we added new abstract methods */

package utilities;

import java.util.Iterator;

public interface DoubleIterator<E> extends Iterator<E>
{
	public abstract boolean hasPrevious();
	public abstract E previous();
	public abstract DoubleIterator<E> resetToBegin();
	public abstract DoubleIterator<E> resetToEnd();
}