package bag;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyLinkedListTest {
	
	@Test
	public void pushDeleteEndTest() {
		MyLinkedList listTest=new MyLinkedList();
		Node test;
		listTest.pushEnd(3);
		listTest.pushEnd(5);
		test=listTest.head;
		assertTrue(test.value==5);
		listTest.popEnd();
		test=listTest.head;
		if(test!=null)
			assertTrue(test.value!=5);
		
	}
	
	@Test
	public void pushDeleteBeginningTest(){
		MyLinkedList listTest=new MyLinkedList();
		Node test;
		listTest.pushBeginning(7);
		listTest.pushBeginning(9);
		test=listTest.tail;
		assertTrue(test.value==9); 
		listTest.popBeginning();
		test=listTest.tail;
		if(test!=null)
			assertTrue(test.value!=7);//Let's generate assertion error
		
	}
}
