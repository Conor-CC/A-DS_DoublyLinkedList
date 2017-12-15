import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Conor Clery, Student Number: 16320175
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,3);
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(0,1);  
        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() ); 
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );       
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-1, 1);
        testDLL.deleteAt(0);
        testDLL.insertBefore(100, 2);
        testDLL.insertBefore(-1, 1);
        testDLL.deleteAt(0);
    }
    
    @Test
    public void testIsEmpty() {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertTrue("List is Empty! Should be true!", testDLL.isEmpty());
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        assertFalse("List not empty. Should be false.", testDLL.isEmpty());
    }
    
    
    @Test
    public void testGetMethod() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
         
        int data = testDLL.get(0);
        assertEquals( "Checking non empty list at position 0 - expected the element at the head of the list", 4, data );   
        assertNull( "Checking non empty list at position 8 - expected the element at the head of the list", testDLL.get(8));
        assertNull( "Checking null gets returned for negative position", testDLL.get(-2) );
        data = testDLL.get(2);
        assertEquals( "Checking non empty list at position 2 - expected the element at the head of the list", 2, data );
    }
    
    @Test
    public void testGetListSize() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	int result = testDLL.getMaxIndex();
    	assertEquals("Check list size is 0 when list is empty.", -1, result);
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        result = testDLL.getMaxIndex();
    	assertEquals("Check max index when list is size 4.", 3, result);
    }
    
    @Test
    public void testDelete() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	assertFalse("Cant delete from an empty list", testDLL.deleteAt(0));
    	assertFalse("Cant delete from an empty list", testDLL.deleteAt(12));
    	assertFalse("Cant delete a negative index from an empty list", testDLL.deleteAt(-40));
    	testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.deleteAt(3);
        assertEquals("List should now have 3 elements now", "4,1,2", testDLL.toString());
        testDLL.deleteAt(-2);
        assertEquals("Cant delete a negative index", "4,1,2", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.deleteAt(0);
        assertEquals("List should now have 1 element now", "2", testDLL.toString());
        testDLL.deleteAt(0);
    }
    
    @Test
    public void testReverse() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.reverse();
        assertEquals( "Checking reverse with 0 elements", "", testDLL.toString() );
    	testDLL.insertBefore(0,1);
        assertEquals( "Checking reverse with 1 element", "1", testDLL.toString() );
        testDLL.insertBefore(1,2);
    	testDLL.reverse();
        assertEquals( "Checking reverse with 2 elements", "2,1", testDLL.toString() );
        testDLL.reverse();
        testDLL.insertBefore(5,3);
        testDLL.insertBefore(6,4);
        testDLL.reverse();
        assertEquals( "Checking reverse with 4 elements", "4,3,2,1", testDLL.toString() );
    }
    
    @Test
    public void testPushAndPop() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.push(7);
        assertEquals( "Checking push with 0 elements", "7", testDLL.toString() );
        testDLL.push(5);
        testDLL.push(9);
        testDLL.push(11);
        assertEquals( "Checking push of 2 elems onto list with with 1 element", "7,5,9,11", testDLL.toString() );
        assertEquals( "Output data from pop equals 11", 11, (int) testDLL.pop() );
        assertEquals( "Checking push with 0 elements", "7,5,9", testDLL.toString() );
        testDLL.pop();
        testDLL.pop();
        testDLL.pop();
        assertEquals( "Cant pop from empty list", "", testDLL.toString() );
        testDLL.push(3);
        testDLL.pop();
        testDLL.pop();
    }
    
    @Test
    public void testEnqueueAndDeque() {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	testDLL.enqueue(24);
        assertEquals( "Queue to empty list", "24", testDLL.toString() );
    	testDLL.enqueue(38);
    	testDLL.enqueue(5);
    	testDLL.enqueue(84);
    	testDLL.enqueue(4);
        assertEquals( "Queue to list with previously 1 element", "24,38,5,84,4", testDLL.toString() );
        assertEquals( "Dequeue from semi full list", 24, (int) testDLL.dequeue());
        assertEquals( "Dequeue from semi full list", "38,5,84,4", testDLL.toString() );
        testDLL.dequeue();
        testDLL.dequeue();
        testDLL.dequeue();
        assertEquals( "Dequeue from semi full list down to one elem", "4", testDLL.toString() );
        testDLL.dequeue();
        assertEquals( "List should be emoty. Last element gone", "", testDLL.toString() );
        testDLL.dequeue();
        assertEquals( "Cant dequeue an empty queue", "", testDLL.toString() );
    }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
//    public static void main (String args[]) {
//        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
//        testDLL.insertBefore(0,1);
//        testDLL.insertBefore(1,2);
//        testDLL.insertBefore(2,3);
//        testDLL.insertBefore(0,4);
//        testDLL.insertBefore(0,7);
//        testDLL.insertBefore(5,9);
//        testDLL.insertBefore(3,2);
//        testDLL.insertBefore(10,14);
//      
//
//
//        testDLL.toString();
//        System.out.println(testDLL.toString());
//        System.out.println("REVERSAL TRIAL");
//        testDLL.reverse();
//        System.out.println(testDLL.toString());
//        System.out.println("DELETION TRIAL");
//        testDLL.deleteAt(5);
//        System.out.println(testDLL.toString());
//        testDLL.deleteAt(2);
//        System.out.println(testDLL.toString());
//        System.out.println("PUSH TEST");
//        testDLL.push(40);
//        System.out.println(testDLL.toString());
//        testDLL.push(60);
//        System.out.println(testDLL.toString());
//        System.out.println("POP TEST");
//        int data = testDLL.pop();
//    	System.out.println(testDLL.toString() + ": " + data);
//    	data = testDLL.pop();
//    	System.out.println(testDLL.toString() + ": " + data);
//    	
//    	
//        System.out.println("DEQUEUE TEST");
//    	testDLL = new DoublyLinkedList();
//    	testDLL.enqueue(4);
//        System.out.println(testDLL.toString());
//        testDLL.enqueue(9);
//        System.out.println(testDLL.toString());
//    	testDLL.enqueue(7);
//        System.out.println(testDLL.toString());
//        
//        System.out.println("ENQUEUE TEST");
//        data = testDLL.dequeue();
//        System.out.println(testDLL.toString() + ": " + data);
//        data = testDLL.dequeue();
//        System.out.println(testDLL.toString() + ": " + data);
//    	data = testDLL.dequeue();
//        System.out.println(testDLL.toString() + ": " + data);
//    	
//    }
}
