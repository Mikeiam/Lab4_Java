package bag;

public class WrongNumberException  extends Exception{
	private String myMsg;
	WrongNumberException(){};
	WrongNumberException(String msg){
		myMsg=msg;
	}
    public String toString(){
    	return "WrongNumberException:" + myMsg ;
    }
}
