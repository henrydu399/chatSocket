package co.system.out.serverchat.Sokect;



public class RunServe  {

	
	static OrquestadoThread orquestado;
	
	public static void main(String[] args) {
		
		 orquestado  = new OrquestadoThread();
		 orquestado.run();
			

	}
	



}
