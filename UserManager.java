
public class UserManager{	
	private User userArr[] = new User[100];
	private int userCount;
	static UserManager Instance = new UserManager();
	Thread task;
	class Task extends Thread{

		boolean shutdown;

		public Task(){
			shutdown = true;
		}

		public void finalize(){
			shutdown = false;
		}
		public void run(){
			try {
				while(shutdown){
				for (int i = 0 ; i <userArr.length ; i++) {
					if(userArr[i] == null) continue;
						
					
					if(10000 < (System.currentTimeMillis() - userArr[i].GetStartTime())){
						UserManager.GetInstance().DeleteUser(userArr[i].GetId());
					}
				}
				System.out.println("It's been over a second.");
				Thread.sleep(1000);
			}
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}

	private UserManager(){
		userCount = 0;
		task = new Task();
		task.start();
	}
	
	static public UserManager GetInstance() {	
		return Instance;
	}

	
	




	public void CreateUser(String id, String pwd) {
		User temp = new User(id, pwd);//���ο� ���� ����
		if(FindUser(temp.GetId())==null) {
			userArr[userCount] = temp;//���ο� ������ �迭�� �־� ����
			userCount++;//���� ������ 
			//System.out.println("���� ���� �Ϸ�");
		}
		
		else {
			//System.out.println("�̹� ���� ID�� ���� ����");
		}
		
	}
	
	public User FindUser(String id)
	{
		for(User el: userArr)
		{
			if(el == null)
				continue;

			if(el.GetId().compareTo(id)==0) {//������
				return el;
			}			
		}
		return null;
	}
	
	public void DeleteUser(String id) {

		User deletingUser = FindUser(id);
		if(deletingUser == null) return;

		for (int i = 0 ; i< userArr.length ; i++){
			if(userArr[i] == deletingUser){
				System.out.println("User exist Time out !!!  User Name : " + deletingUser.GetId() );
				userArr[i] = null;
			}
		}

	}
	public void Match(String id, String pwd) {}
}