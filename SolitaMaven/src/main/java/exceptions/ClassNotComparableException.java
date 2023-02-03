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