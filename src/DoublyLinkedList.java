import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Conor CLery, Student NO. 16320175
 *  @version 2/11/17 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    
    public DoublyLinkedList() 				
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >The conditional check of the head and the tail is constant as the List object contains a reference to
     *  both the head and the tail of the list.
     * >The return true statement and the return false statement are constant costs as they return a primitive 
     *  boolean type.
     */
    public boolean isEmpty()				
    {
    	if (head == null)						//O(1)
    		return true;						//O(1)
    	return false;							//O(1)		| TOTAL = O(1)
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  >In the worst case scenario of this algorithm, the code will reach the while loop. In the worst case the 'pos' 
     *   variable is equal to N. Therefore the all code executed in the while loop will run N times.
     *   
     */
    public void insertBefore( int pos, T data ) 		
    {
      //TODO
    	DLLNode node;											//O(1)
    	if (this.isEmpty()) {									//O(1)
    		node = new DLLNode(data, null, null);				//O(1)
    		this.head = node;									//O(1)
    		this.tail = node;									//O(1)
    	}
    	else {
    		if  (pos < 0 ) {									//O(1)
    			node = new DLLNode(data, null , this.head); 	//O(1)
    			this.head = node;								//O(1)
    		}
    		else if (pos > getMaxIndex()) {						//O(N)
    			node = new DLLNode(data, this.tail, null);		//O(1)
    			this.tail.next = node;							//O(1)
    			this.tail = node;								//O(1)
    		}
    		else {
    			DLLNode posNode = this.head;					//O(1)
    			DLLNode prevNode = null;						//O(1)
    			int count = 0;									//O(1)
    			while (count < pos) {							//O(N)
    				posNode = posNode.next;						//O(N)
    				count++;									//O(N)
    			}
    			prevNode = posNode.prev;						//O(1)
    			node = new DLLNode(data, prevNode, posNode);	//O(1)
    			posNode.prev = node;							//O(1)
    			if (prevNode != null) {							//O(1)
    				prevNode.next = node;						//O(1)
    			}												//O(1)
    			else {				
    				prevNode = null;							//O(1)
    			}												
    			
    		}
    		if (pos == 0) {										//O(1)
				this.head = node;								//O(1)		| TOTAL=O(N)
			}
    	}
    }
    
    /**
     * Gets the maximum readable index of the Doubly Linked List.
     * @return highest index of list.
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     *  >The worst case of this algorithm will always be reached due to its for loop. The code will be 
     *   executed in the for loop N times where N is the size of the Doubly Linked List.
     *   
     */
    
    public int getMaxIndex() {								
    	int hiIndex = -1;
    	DLLNode node = this.head;
    	if (!this.isEmpty()) {														//O(1)    		
    		hiIndex = 0;															//O(1)
    		if (this.head == this.tail) {											//O(1)
    			hiIndex = 0;														//O(1)
    		}
    		else {
    			for (node = this.head; (node.next != null); node = node.next) {   	//O(N)
    				hiIndex++;														//O(N)
    			}
    		}
    	}
    	return hiIndex;																//O(1)		| TOTAL=O(N)
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     * >In the worst case scenario the getMaxIndex() function is called and the while loop is executed N times where pos 
     *  is equal to N.
     *
     */
    public T get(int pos) 			
    {
    	T data = null;									//O(1)				
    	if (pos < 0 || pos > this.getMaxIndex()) { 		//O(N) as getMaxIndex() is called
    		return data;								//O(1)
    	}
    	int count = 0;									//O(1)
    	DLLNode node = this.head;						//O(1)
    	while (count < pos) {							//O(N)
    		count++;									//O(N)
    		node = node.next;							//O(N)
    	}
		data = node.data;								//O(1)
    	return data;									//O(1)		| TOTAL=O(N)
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     * >If the pos is equal to N, the worst case scenario ensues. getMaxIndex() is called twice and a while loop is executed N times.
     * 
     */
    public boolean deleteAt(int pos) 		
    {
    	if (!this.isEmpty() && pos <= this.getMaxIndex() && pos >= 0) {	
    		DLLNode tmp;											//O(1)
	    		if (pos == 0) {										//O(1)
	    			tmp = this.head;								//O(1)
	    			if (tmp.next == null) {							//O(1)
	    				this.head = null;							//O(1)
	    			}
	    			else {
	    				tmp = this.head.next;
	    				this.head.next = null;						//O(1)
	    				this.head.prev = null;						//O(1)
	    				this.head = tmp;							//O(1)
	    			}
	    		}
	    		else {
	    			tmp = this.head;								//O(1)
	    			for (int i = 0; i < pos; i++) {					//O(N)
	    				tmp = tmp.next;								//O(N)
	    			}
	    			if (tmp.next == null) {							//O(1)
	    				this.tail = tmp.prev;						//O(1)
	    				this.tail.next = null;						//O(1)
	    				tmp.next = null;							//O(1)
	    				tmp.prev = null;							//O(1)
	    			}
	    			else {
	    				tmp.prev.next = tmp.next;					//O(1)
	    				tmp.next.prev = tmp.prev;					//O(1)
	    				tmp.next = null;							//O(1)
	    				tmp.prev = null;							//O(1)
	    			}
	    		}
	    		return true;  										//O(1)
    	}
    	return false;												//O(1)		| TOTAL=O(N)
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic runtime cost: O(N)
     *
     * Justification:
     * >The worst case for this situation is probably also the most common. The cost of reversing the list depends
     *  on the size N of the list as the reversal is achieved by simply swapping each node's previous reference 
     *  to it's next one and vice versa. 
     *   
     */
    public void reverse() 
    {
    	if (!this.isEmpty() && this.head != this.tail) {		//O(1)		
    		DLLNode node = this.head;							//O(1)
    		DLLNode newTail = this.head;						//O(1)		
    		this.tail = newTail;								//O(1)
    		DLLNode tempNode = null;							//O(1)
    		while (node != null) {								//O(N)
    			tempNode = node.prev;							//O(N)
    			node.prev = node.next;							//O(N)
    			node.next = tempNode;							//O(N)
    			node = node.prev;								//O(N)
    		}													
    		if (node == null) {									//O(1)
    			this.head = tempNode.prev;						//O(1) 		| TOTAL=O(1)
    		}
    	}
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >No line in this function has a worst case cost greater than O(1). Therefore the worst case asymptotic
     * run time cost of this algorithm is O(1).
     */
    public void push(T item)  	
    {
    	DLLNode toAdd;											//O(1)
    	if (this.isEmpty()) {									//O(1)
    		toAdd = new DLLNode(item, null, null);				//O(1)
    		this.head = toAdd;									//O(1)
    		this.tail = toAdd;									//O(1)
    	}
    	else {
    		DLLNode oldTail = this.tail;						//O(1)
    		toAdd = new DLLNode(item, oldTail, null);			//O(1)
    		oldTail.next = toAdd;								//O(1)
    		this.tail = toAdd;									//O(1)		| TOTAL=O(1)
    	}
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >No line in this function has a worst case cost greater than O(1). Therefore the worst case asymptotic
     * run time cost of this algorithm is O(1).
     */
    public T pop()       
    {
    	T data = null; 								//O(1)
    	DLLNode prevNode;							//O(1)
    	DLLNode toPop;								//O(1)
    	if(!this.isEmpty()){						//O(1)
			toPop = this.tail;						//O(1)
    		if (this.head == this.tail) {			//O(1)
    			prevNode = null;					//O(1)
    			this.head = null;					//O(1)
    			this.tail = null;					//O(1)
    		} 
    		else {
    			prevNode = toPop.prev;				//O(1)
    			prevNode.next = null;				//O(1)
    			this.tail = prevNode;				//O(1)
    		}
    		data = toPop.data;						//O(1)
    	}
    	return data;								//O(1)		| TOTAL=O(1)
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * >Justification for this can be found in the push() function as this method reuses that code due to the fact that
     *  in my implementation, they are the exact same actions being performed.
     */
    public void enqueue(T item)
    {
    	this.push(item);
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     * > All costs again here are constant, even with the methods called. Therefore the total asymptotic running time of
     *  this algorithm is O(1)
     *   
     */
    public T dequeue()                          
    {
		T data = null;									//O(1)
    	if (!this.isEmpty()) {							//O(1)
    		if (this.head == this.tail) {				//O(1)
    			data = this.head.data;					//O(1)
    			this.head = null;						//O(1)
    			this.tail = null;						//O(1)
    		}
    		else {
    			DLLNode toDequeue = this.head;			//O(1)
    			data = toDequeue.data;					//O(1)
    			DLLNode newHead = toDequeue.next;		//O(1)
    			newHead.prev = null;					//O(1)
    			this.head = newHead;					//O(1)
    			toDequeue.next = null;					//O(1)
    			toDequeue.prev = null;					//O(1)
    			toDequeue = null;						//O(1)
    		}
    	}
    	return data;									//O(1)		| TOTAL=O(1)
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     * 
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}


