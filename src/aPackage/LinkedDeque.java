package aPackage;

public class LinkedDeque<T> implements DequeInterface<T> {
	private DLNode firstNode; // References node at front of deque
	   private DLNode lastNode;  // References node at back of deque
		
		public LinkedDeque()
		{
			firstNode = null;
			lastNode = null;
		}
		
	@Override
	public void addToFront(T newEntry) {
		DLNode newNode = new DLNode(null, (LinkedDeque<T>.DLNode) newEntry, firstNode);
		   if (isEmpty())
		      lastNode = newNode;
		   else
		      firstNode.setPrevious(newNode);
		    
		   firstNode = newNode;
	}

	@Override
	public void addToBack(T newEntry) {
		DLNode newNode = new DLNode(newEntry, lastNode, null);

		   if (isEmpty())
		      firstNode = newNode;
		   else
		      lastNode.setNext(newNode);
		    
		   lastNode = newNode;
	}

	@Override
	public T removeFront() {
		T front = getFront(); 
		   firstNode = firstNode.getNext();

		   if (firstNode == null)
		      lastNode = null;
		   else
		      firstNode.setPrevious(null);

		   return front;
	}

	@Override
	public T removeBack() {
		T back = getBack();  
		   lastNode = lastNode.getPrevious();

		   if (lastNode == null)
		      firstNode = null;
		   else {
		      lastNode.setNext(null);
		   }

		   return back;
	}

	@Override
	public T getFront() {
		if (isEmpty())
		    throw new EmptyQueueException();
		else
		    return firstNode.getData();
	}

	@Override
	public T getBack() {
		if (isEmpty())
		    throw new EmptyQueueException();
		else
		    return lastNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
	}
	
	private class DLNode
	   {
			private T      data;  	 // Deque entry
			private DLNode next;  	 // Link to next node
			private DLNode previous; // Link to previous node
			public DLNode(T data, LinkedDeque<T>.DLNode next, LinkedDeque<T>.DLNode previous) {
				super();
				this.data = data;
				this.next = next;
				this.previous = previous;
			}
			
			public T getData() {
				return data;
			}
			public DLNode getNext() {
				return next;
			}
			public void setNext(DLNode next) {
				this.next = next;
			}
			public DLNode getPrevious() {
				return previous;
			}
			public void setPrevious(DLNode previous) {
				this.previous = previous;
			}
			
			
	   }

}
