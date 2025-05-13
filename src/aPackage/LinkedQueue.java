package aPackage;

public class LinkedQueue<T> implements QueueInterface<T> {

	private Node firstNode; // References node at front of queue
	private Node lastNode;  // References node at back of queue
	 private int size;
	   
	public LinkedQueue()
	{
		firstNode = null;
		lastNode = null;
		size = 0;
	} 

	public class Node {
		T data;
		Node  next;
		public Node(T data, Node next) {
			this.data = data;
			this.next = null;
		}
		public T getData() {return data;}

		public void setData(T data) {this.data = data;}

		public Node getNext() {return next;}

		public void setNext(Node next) {this.next = next;}	
	}
	
	@Override
	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry, null);
		   if (isEmpty())
		      firstNode = newNode;
		   else
		      lastNode.setNext(newNode);
		    
		   lastNode = newNode;
		   size++;
	}

	@Override
	public T dequeue() {
		T front = getFront();  
		   firstNode.setData(null);
		   firstNode = firstNode.getNext();
		   if (firstNode == null)
		      lastNode = null;
		   size--;
		   return front;
	}

	@Override
	public T getFront() {
		if (isEmpty())
		    throw new EmptyQueueException();
		else
		    return firstNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

}
