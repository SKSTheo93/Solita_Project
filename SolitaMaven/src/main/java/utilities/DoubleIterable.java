package utilities;

public interface DoubleIterable<T> extends Iterable<T>
{
	@Override
	public abstract DoubleIterator<T> iterator();
}