package aPackage;

import java.util.Arrays;
import java.util.Vector;

public class VectorStack<T> implements StackInterface<T> {
	private Vector<T> stack;   // Last element is the top entry in stack
	   private boolean integrityOK;
		private static final int DEFAULT_CAPACITY = 50;
		private static final int MAX_CAPACITY = 10000;
	  
	   public VectorStack()
	   {
	      this(DEFAULT_CAPACITY);
	   } // end default constructor
	  
	   public VectorStack(int initialCapacity)
	   {
	      integrityOK = false;
	      checkCapacity(initialCapacity);
	      stack = new Vector<>(initialCapacity); // Size doubles as needed
	      integrityOK = true;
	   }
	   
	   private void checkCapacity(int capacity) {
	    	if(capacity > MAX_CAPACITY) {
	    		throw new IllegalStateException("Error.");
	    	}
	    }
	   
	   private void checkIntegrity() {
			if(!integrityOK) {
				throw new SecurityException("ArrayStack object is corrupt.");
			}
		}
	@Override
	public void push(T newEntry) {
		checkIntegrity();
		   stack.add(newEntry);
	}

	@Override
	public T pop() {
		checkIntegrity();
		   if (isEmpty())
		      throw new EmptyStackException();
		   else
		      return stack.remove(stack.size() - 1);
	}

	@Override
	public T peek() {
		checkIntegrity();
		   if (isEmpty())
		      throw new EmptyStackException();
		   else
		      return stack.lastElement();
	}

	@Override
	public boolean isEmpty() {
		checkIntegrity();
		   return stack.isEmpty();
	}

	@Override
	public void clear() {
		checkIntegrity();
		   stack.clear();
	}

}
