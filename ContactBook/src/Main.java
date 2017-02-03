import java.util.Scanner;

public class Main {
	
	private static final String SUCCESS = "Sucesso!";
	private static final String NON_EXISTANT_NAME = "Nome nao existente.";
	private static final String ASK_FOR_NAME = "Nome: ";
	private static final String ASK_FOR_PHONE = "Numero: ";
	private static final String ASK_FOR_EMAIL = "Email: ";
	
	private static final String ADD_CONTACT = "AC";
	private static final String REMOVE_CONTACT = "RC" ;
	private static final String GET_CONTACT_PHONE = "GP";
	private static final String GET_CONTACT_EMAIL = "GE";
	private static final String UPDATE_CONTACT_PHONE = "SP";
	private static final String UPDATE_CONTACT_EMAIL = "SE";
	private static final String LIST_ALL_CONTACTS = "LC";
	private static final String HELP = "H";
	private static final String EXIT = "Q";
	
	
	
	private static void printMenu(){
		System.out.println(ADD_CONTACT + " ­ Adiciona um contacto.");
		System.out.println(REMOVE_CONTACT + " ­ Remove um contacto.");
		System.out.println(GET_CONTACT_PHONE + " ­ Consulta o telefone de um contacto.");
		System.out.println(GET_CONTACT_EMAIL + " ­ Consulta o email de um contacto.");
		System.out.println(UPDATE_CONTACT_PHONE + " ­ Atualiza o telefone de um contacto.");
		System.out.println(UPDATE_CONTACT_EMAIL + " ­ Atualiza o email de um contacto");
		System.out.println(LIST_ALL_CONTACTS  + " – Lista todos os contactos existentes na agenda.");
		System.out.println(HELP  + " – Ajuda.");
		System.out.println(EXIT + " ­ Sair.");
	}
	
	//Metodo auxiliar para ler as opções do menu.
	private static String readOption(Scanner in) {
			String option;
			System.out.println("> ");
			option = in.nextLine();
			return option;
	}
		
	//Metodo auxiliar para pedir inteiros. Pre: req terá de ser um pedido.
	private static int requestInt(String req, Scanner in){
			System.out.println(req);
			int userInput;
			userInput = in.nextInt();
			in.nextLine();
			return userInput;
	}
	
	//Metodo auxiliar para pedir strings. Pre: req terá de ser um pedido.
	private static String requestString(String req, Scanner in){
			System.out.println(req);
			return in.nextLine();
	}
	
	private static void addContact(Scanner in, ContactBook cb){
		cb.addContact(requestString(ASK_FOR_NAME, in), requestInt(ASK_FOR_PHONE,in), requestString(ASK_FOR_EMAIL, in));
	}
	
	private static void deleteContact(Scanner in, ContactBook cb){
		String name = requestString(ASK_FOR_NAME, in);
		if(cb.hasContact(name)){
			cb.deleteContact(name);
			System.out.println(SUCCESS);
		}else{
			System.out.println(NON_EXISTANT_NAME);
		}
	}
	
	private static int getPhone(Scanner in, ContactBook cb){
		String name = requestString(ASK_FOR_NAME, in);
		int result;
		if(cb.hasContact(name)){
			result = cb.getPhone(name);
		}else{
			result = -1;
		}
		return result;
	}
	
	private static String getEmail(Scanner in, ContactBook cb){
		String name = requestString(ASK_FOR_NAME, in);
		String result;
		if(cb.hasContact(name)){
			result = cb.getEmail(name);
		}else{
			result = "";
		}
		return result;
	}
	
	private static void setPhone(Scanner in, ContactBook cb){
		String name = requestString(ASK_FOR_NAME, in);
		if(cb.hasContact(name)){
			cb.setPhone(name, requestInt(ASK_FOR_PHONE, in));
		}else{
			System.out.println(NON_EXISTANT_NAME);
		}		
	}
	
	private static void setEmail(Scanner in, ContactBook cb){
		String name = requestString(ASK_FOR_NAME, in);
		if(cb.hasContact(name)){
			cb.setEmail(name, requestString(ASK_FOR_EMAIL, in));
		}else{
			System.out.println(NON_EXISTANT_NAME);
		}		
	}
	
	private static void listAllContacts (Scanner in, ContactBook cb){
		cb.initializeIterator();
		while(cb.hasNext()){
			Contact c = cb.next();
			System.out.println(c.getName() + ";" + c.getEmail() + ";" + c.getPhone());
		}
	}
	
	public static void main(String [] args){
		boolean finished = false; 
		String option;
		Scanner in = new Scanner(System.in);
		printMenu();
		
		ContactBook cb = new ContactBook();
		
		while(!finished){
			option = readOption(in);
			switch(option.toUpperCase()){
				case ADD_CONTACT:
					addContact(in, cb);
					break;
				case REMOVE_CONTACT:
					deleteContact(in, cb);
					break;
				case GET_CONTACT_PHONE:
					System.out.println("\n");
					System.out.println(getPhone(in,cb));
					System.out.println("\n");
					break;
				case GET_CONTACT_EMAIL:
					System.out.println("\n");
					System.out.println(getEmail(in,cb));
					System.out.println("\n");
					break;
				case UPDATE_CONTACT_PHONE:
					setPhone(in, cb);
					break;
				case UPDATE_CONTACT_EMAIL:
					setEmail(in, cb);
					break;
				case LIST_ALL_CONTACTS:
					System.out.println("\n");
					listAllContacts(in, cb);
					System.out.println("\n");
				case HELP:
					System.out.println("\n");
					printMenu();
					System.out.println("\n");
					break;
				case EXIT:
					finished = true;
					System.out.println("Aplicacao terminada. Ate a proxima.");
					break ;
				default:	
					System.out.println("Opçao inexistente");
					break;
			}
		}

	}
}
