/*An interface which extends the interface Iterable<T>
The method we override already exists but it returns Iterator<T> instead
of DoubleIterator<T>*/

package utilities;

public interface DoubleIterable<T> extends Iterable<T>
{
	@Override
	public abstract DoubleIterator<T> iterator();
}