package bag;
import java.io.*;
import java.util.*;
import java.util.logging.*;

 class Node{
	 Node next;
	 Node prev;
	 int value;
	 Node(int value){
		 this.value=value;
		 next=null;
		 prev=null;
	 }
}
 
public class MyLinkedList {
	private static Logger logDoc= Logger.getLogger(MyLinkedList.class.getName());
	Node head;
	Node tail;
	MyLinkedList(){
		head=null;
		tail=null;
	}
	
	public void pushBeginning(int value){
		Node push = new Node(value);
	    if(tail==null){
	    	head=push;	
	    }
	    else{
	    	tail.prev=push;
	    	push.next=tail;
	        tail=push;
	    }
	}
	
	public void pushEnd(int value){
		Node push=new Node(value);
		if(tail==null){
			tail=push;
		}
		else{
		    head.next=push;
		    push.prev=head;
		}
		 head=push;
	}
	
	public void popBeginning(){
		if(tail.next==null){
			head=null;
		}	
		else
			tail.next.prev=null;
		tail=tail.next;	
	}
	
	public void popEnd(){
		if(tail.next==null){
			tail=null;
		}
		else
		    head.prev.next=null;
		head=head.prev;
	}
	
    public void show(){
     	Node show=tail;
     	while(show!=null){
     		System.out.print(show.value + " ");
     		show=show.next;
     	}
    }
    
    public void pushList(int value,MyLinkedList mylist)throws WrongNumberException{
    	assert(mylist!=null);
    	Node push=tail;
    	if(push!=null){
    		Node push1=null;
    		while(push.value!=value){
    			push=push.next;
    		}
    		push1=push.next;
    		push.next=mylist.tail;
    		mylist.tail.prev=push;
    		mylist.tail=tail;
    		while(push.next!=null)
    			push=push.next;
    		push.next=push1;
    		if(push1!=null){
    			push1.prev=push;
    			head=push1;
    			mylist.head=head;
    		}
    		else{
    			head=push;
    			mylist.head=head;
    		}
    	}else throw new WrongNumberException("No number to add after!");
        
    }
    
    
    public void popList(int value)throws WrongNumberException{
    	Node push=head;
    	if(push!=null){
    		Node push1=null;
    		if(head.value==value){}
    		else{	
    			while(push.prev.value!=value){
    				push1=push;
    				push=push.prev;
    			}
    			if(push1!=null){
    				push1.prev=push.prev;
    				push.prev.next=push1;
    				push.prev=null;
    				push.next=null;
    			}
    			else{
    				head=push.prev;
    				head.next=null;
    			}
    		}
    	}else throw new WrongNumberException("No number to delete after!");
    }
    
    public void sortList(){
    	if(tail==null) 
    		return;
    	if(tail.next==null) 
    		return;
    	Node sort1=tail;
    	while(sort1.next!=null)
    		sort1=sort1.next;
    	while(sort1!=null){
    		Node sort2=tail;
    		while(sort1!=sort2){
    			if(sort2.value>sort2.next.value){
    				int sortValue=sort2.value;
    				sort2.value=sort2.next.value;
    				sort2.next.value=sortValue;	
    			}
    			sort2=sort2.next;
    		}
    		sort1=sort1.prev;
    	}
    	return;
    }
    
    
    public static void main(String[] args){
    	try {
            LogManager.getLogManager().readConfiguration(MyLinkedList.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    	int numElem,elem,elem2;
    	int pick=1;
    	MyLinkedList list=new MyLinkedList();
    	MyLinkedList list1=new MyLinkedList();
    	Scanner scan=new Scanner(System.in);
    	while(pick!=0){
    		System.out.println("Select an action:");
    		System.out.println("_________________");
    		System.out.println("1.Sozdat' spisok");
    		System.out.println("2.Dobavit' element v nachalo");
    		System.out.println("3.Dobavit' element v konec");
    		System.out.println("4.Udalit' element iz nachala");
    		System.out.println("5.Udalit' element iz konca");
    		System.out.println("6.Vstavit' podspisok");
    		System.out.println("7.Udalit' podspisok");
    		System.out.println("8.Otsortirovat' spisok");
    		System.out.println("9.Pokazat' spisok");
    		System.out.println("0.Exit");
    		pick=scan.nextInt();
    		assert(pick>=0);
    		assert(pick<=9);
    		switch(pick){
    			case 1:{
    				System.out.print("Vvedite kolichestvo elementov spiska:");
    				numElem=scan.nextInt();
    				assert(numElem>=0);
    				logDoc.fine("Assert numElem>=0 passed succesfully");
    				for(int i=0;i<numElem;i++){
    					System.out.print("Vvedite"+ " " + (i+1) + " " + "element spiska:");
    					elem=scan.nextInt();
    					list.pushEnd(elem);
    				}
    				list.show();
    				logDoc.info("List initialized with " +numElem + " elements");
    				System.out.println();
    				break;
    			}
    			case 2:{
    				System.out.print("Vvedite element dlya dobavleniya v nachalo:");
    				elem=scan.nextInt();
    				list.pushBeginning(elem);
    				list.show();
    				logDoc.info(elem + " added to the beggining");
    				System.out.println();
    				break;
    				}
    			case 3:{
    				System.out.print("Vvedite element dlya dobavleniya v konec:");
    				elem=scan.nextInt();
    				list.pushEnd(elem);
    				list.show();
    				logDoc.info(elem + " added to the end");
    				System.out.println();
    				break;
    	        }
    			case 4:{
    				try{
    					System.out.print("Udalyaem element:");
    					System.out.println();
    					list.popBeginning();
    					logDoc.info("First element deleted");
    				}catch(NullPointerException e){
    					logDoc.log(Level.SEVERE,"Exception:",e);
    					System.out.println("Nechego udalyat'");
    				}
    				list.show();
    				System.out.println();
    				break;
    		    }
    		    case 5:{
    		    	try{
    		    		System.out.print("Udalyaem element:");
    		    		System.out.println();
    		    		list.popEnd();
    		    		logDoc.info("Last element deleted");
    		    	}catch(NullPointerException e){
    		    		logDoc.log(Level.SEVERE,"Exception:",e);
    					System.out.println("Nechego udalyat'");
    				}
    				list.show();
    				System.out.println();
    				break;
    		    }
    		    case 6:{
    		    	System.out.print("Vvedite kolichestvo elementov,kotoroe hotite dobavit':");
    		    	numElem=scan.nextInt();
    		    	assert(numElem>=0);
    		    	logDoc.fine("Assert numElem>=0 passed succesfully");
    		    	System.out.print("Vvedite cifru,posle kotoroy hotite vstavit' podstoku:");
    		    	elem2=scan.nextInt();
    		    	for(int i=0;i<numElem;i++){
    		    		System.out.print("Vvedite"+ " " + (i+1) + " " + "element spiska:");
    					elem=scan.nextInt();
    					list1.pushEnd(elem);
    		    	}
    		    	list1.show();
    		    	System.out.println();
    		    	try{
    		    		list.pushList(elem2,list1);
    		    		logDoc.info(numElem + " elements added after element " + elem2 );
    		    	}catch(WrongNumberException e){
    		    		logDoc.log(Level.SEVERE,"Exception:",e);
    					System.err.println(e.toString());	
    				}
    		    	list.show();
    		    	System.out.println();
    		    	break;
    		    }
    		    case 7:{
    		    	System.out.print("Vvedite kolichestvo elementov,kotoroe hotite udalit':");
    		    	numElem=scan.nextInt();
    		    	assert(numElem>=0);
    		    	logDoc.info("Assert numElem>=0 passed succesfully");
    		    	System.out.print("Vvedite cifru,posle kotoroy hotite udalit' podstoku:");
    		    	elem2=scan.nextInt();
    		    	try{
    		    		for(int i=0;i<numElem;i++)
    		    			list.popList(elem2);
    		    		logDoc.info(numElem + " elements deleted after element " + elem2 );
    		    	}catch(WrongNumberException e){
    		    		logDoc.log(Level.SEVERE,"Exception:",e);
    					System.err.println(e.toString());
    		    	}
    		    	list.show();
    		    	System.out.println();
    		    	break;
    		    }
    		    case 8:{
    				list.sortList();
    				logDoc.info("List sorting done");
    				list.show();
    				System.out.println();
    		    	break;
    		    }
    		    case 9:{
    		    	list.show();
    		    	logDoc.info("Showing list");
    		    	System.out.println();
    		    	break;
    		    }
    		    case 0:{
    		    	logDoc.finest("Closing programm.Good Job!");
    		    	break;
    		    }	
    	   }       
       }
    	scan.close();
    }
}    
