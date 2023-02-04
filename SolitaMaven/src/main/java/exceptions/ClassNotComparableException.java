/*An exception class which extends RuntimeException.
That means it is not mandatory to check for functions that
throw ClassNotComparableException objects. If we want to add an
object of a custom class in the tree, that class
needs to implement the Comparable Interface. */

package exceptions;

public class ClassNotComparableException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ClassNotComparableException()
	{
		super();
	}
	
	public ClassNotComparableException(String message)
	{
		super(message);
	}
}