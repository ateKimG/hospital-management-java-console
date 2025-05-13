package aPackage;

public class LinkedStack<T> implements StackInterface<T> {
	private Node topNode;
	
	public LinkedStack()
	   {
	      topNode = null;
	   }
	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
		public Node(T data, LinkedStack<T>.Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		public T getData() {return data;}
		public Node getNext() {return next;}
	}
	
	@Override
	public void push(T newEntry) {
		Node newNode = new Node(newEntry, topNode);
		   topNode = newNode;
	}

	@Override
	public T pop() {
		T top = peek();
		   topNode = topNode.getNext();
		   return top;
	}

	@Override
	public T peek() {
		if (isEmpty())
		      throw new EmptyStackException();
		   else
		      return topNode.getData();
	}

	@Override
	public boolean isEmpty() {return topNode == null;}

	@Override
	public void clear() {topNode = null;}

}
