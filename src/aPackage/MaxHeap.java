package aPackage;

import java.util.Arrays;

public final class MaxHeap<T extends Comparable<? super T>>
implements MaxHeapInterface<T>
 {
	private T[] heap;      
	private int lastIndex; 
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
		
	public MaxHeap(int initialCapacity)
	{
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else 
		    checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		    T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
		    heap = tempHeap;
		    lastIndex = 0;
		    integrityOK = true;
	}  

	public MaxHeap()
	   {
	      this(DEFAULT_CAPACITY); 
	   }
	
	@Override
	public void add(T newEntry) {
		checkIntegrity();        
		   int newIndex = lastIndex + 1;
		   int parentIndex = newIndex / 2;
		   while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
		   {
		      heap[newIndex] = heap[parentIndex];
		      newIndex = parentIndex;
		      parentIndex = newIndex / 2;
		   } // end while

		   heap[newIndex] = newEntry;
		   lastIndex++;
		   ensureCapacity();
	}

	@Override
	public T removeMax() {
		checkIntegrity();             
		   T root = null;

		   if (!isEmpty())
		   {
		      root = heap[1];            // Return value
		      heap[1] = heap[lastIndex]; // Form a semiheap
		      lastIndex--;               // Decrease size
		      reheap(1);                 // Transform to a heap
		   } // end if

		   return root;
	}

	@Override
	public T getMax() {
		checkIntegrity();
	      T root = null;
	      if (!isEmpty())
	         root = heap[1];
	      return root;

	}

	@Override
	public boolean isEmpty() {return lastIndex < 1;}

	@Override
	public int getSize() {return lastIndex;}

	@Override
	public void clear() {
		checkIntegrity();
	      while (lastIndex > -1)
	      {
	         heap[lastIndex] = null;
	         lastIndex--;
	      } 
	      lastIndex = 0;

	}
	
	private void checkIntegrity() {
		if (!integrityOK) {
            throw new SecurityException("ArrayStack object is corrupt.");
        }
	}
	
	private void ensureCapacity() {
	    if (lastIndex >= heap.length - 1) { // heap[0] is unused
	        int newLength = 2 * heap.length;
	        checkCapacity(newLength);
	        heap = Arrays.copyOf(heap, newLength);
	    }
	}
	
	private void checkCapacity(int capacity) {
	    if (capacity > MAX_CAPACITY) {
	        throw new IllegalStateException("Attempt to create a "
	        		+ "heap whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
	    }
	}
	
	
	private void reheap(int rootIndex)
	{
	   boolean done = false;
	   T orphan = heap[rootIndex];
	   int leftChildIndex = 2 * rootIndex;

	   while (!done && (leftChildIndex <= lastIndex) )
	   {
	      int largerChildIndex = leftChildIndex; // Assume larger
	      int rightChildIndex = leftChildIndex + 1;

	      if ( (rightChildIndex <= lastIndex) &&
	            heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
	      {
	         largerChildIndex = rightChildIndex;
	      } 

	      if (orphan.compareTo(heap[largerChildIndex]) < 0)
	      {
	         heap[rootIndex] = heap[largerChildIndex];
	         rootIndex = largerChildIndex;
	         leftChildIndex = 2 * rootIndex;
	      }
	      else
	         done = true;
	   } 
	  heap[rootIndex] = orphan;
	} 

	public MaxHeap(T[] entries) //create heap
	{
	   this(entries.length); // Call other constructor
	   lastIndex = entries.length;
	   for (int index = 0; index < entries.length; index++)
	      heap[index + 1] = entries[index];
	   // Create heap
	   for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
	      reheap(rootIndex);
	} 

	
	private static <T extends Comparable<? super T>> //reheap
    void reheap(T[] heap, int rootIndex, int lastIndex)
	{
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex + 1;
		
		while (!done && (leftChildIndex <= lastIndex))
		{
		  int largerChildIndex = leftChildIndex;
		  int rightChildIndex = leftChildIndex + 1;
		
		  if ( (rightChildIndex <= lastIndex) &&
		        heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
		  {
		     largerChildIndex = rightChildIndex;
		  } // end if
		
		  if (orphan.compareTo(heap[largerChildIndex]) < 0)
		  {
		     heap[rootIndex] = heap[largerChildIndex];
		     rootIndex = largerChildIndex;
		     leftChildIndex = 2 * rootIndex + 1;
		  }
		  else
		     done = true;
		}
		heap[rootIndex] = orphan;
	} 

}
