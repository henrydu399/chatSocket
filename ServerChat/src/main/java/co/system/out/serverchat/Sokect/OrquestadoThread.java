package co.system.out.serverchat.Sokect;

public class OrquestadoThread implements Runnable{
	
	private static AppServer app ;
	private static  BrocastClientMessage broadcast; 

	@Override
	public void run() {
		new Thread(){
		    public void run(){
		    	Run1();
		    }
		}.start();
		
		new Thread(){
		    public void run(){
		    	Run2();
		    }
		}.start();
		
		
		
		
	}
	

	public static   void  Run1() {
		 app = new AppServer();
		 app.run();
	}
	
	
	public static   void  Run2() {
		broadcast = new BrocastClientMessage(app );
		broadcast.start();
		}
	
}
