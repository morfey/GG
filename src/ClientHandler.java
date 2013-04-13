import java.util.LinkedList;

public class ClientHandler {

	private String username;
	private String password;
	public LinkedList<ClientHandler> users;
	ClientHandler(){
		
	}
	ClientHandler(String username, String password){
		this.username=username;
		this.password=password;
		
	}
	public String getUsername() {
		return username;
	}
	public void add(ClientHandler e){
		users.add(e);
	}
	public void init(){
		users = new LinkedList<ClientHandler>();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
