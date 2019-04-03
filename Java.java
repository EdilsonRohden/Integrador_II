public class Java{
	public static void main(String[] args){
		try{
			if(Integer.parseInt(args[0]) > 0){
				System.out.println("Usando metodos de contancao de ecessoes.");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Parece que nao deu certo... ;(");
		}
	}

}
