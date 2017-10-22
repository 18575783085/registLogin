package ou.exception;
/**
 * 自定义异常
 * @author Administrator
 *
 */
public class MsgException extends Exception {
	public  MsgException(){
		super();
	}
	
	public MsgException(String message){
		super(message);
	}
	
	
}
