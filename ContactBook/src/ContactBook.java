
public class ContactBook {
	
	private static final int DEFAULT_ARRAY_SIZE = 100;
	private static final int GROWTH = 2;
	
	private Contact[] contacts;
	private int counter;
	private int currentContact;
	
	public ContactBook(){
		counter = 0;
		contacts = new Contact[DEFAULT_ARRAY_SIZE];
		currentContact = -1;
	}
	
	public void initializeIterator(){
		currentContact = 0;
	}
	
	public boolean hasNext(){
		return ((currentContact >= 0 ) &&
		(currentContact < counter));
	}
	
	public Contact next(){
		return contacts[currentContact++];
	}
	
	public boolean hasContact(String name){
		return searchIndex(name) != -1;
	}
	
	public int getNumberOfContacts(){
		return counter;
	}
	
	public void addContact(String name, int phone, String email){
		if (counter == contacts.length)
			resize();
		contacts[counter++] = new Contact(name,phone,email);
	}

	public int getPhone(String name){
		return contacts[searchIndex(name)].getPhone();
	}
	
	public String getEmail(String name){
		return contacts[searchIndex(name)].getEmail();
	}
	
	public void deleteContact(String name){
		contacts[searchIndex(name)] = contacts[counter-1];
		counter--;
	}
	
	public void setPhone(String name, int phone){
		contacts[searchIndex(name)].setPhone(phone);
	}
	
	public void setEmail(String name, String email){
		contacts[searchIndex(name)].setEmail(email);
	}
	
	private int searchIndex(String name){
		int result = -1;
		for(int i = 0; i < counter; i++){
			if((contacts[i]).getName().equals(name)){
				result = i;
			}
		}
		return result;
	}
	
	private void resize() {
		Contact[] temp = new Contact[GROWTH * contacts.length];
		
		for(int i = 0; i < contacts.length; i++){
			temp[i] = contacts[i];
		}
		contacts = temp;
	}
}