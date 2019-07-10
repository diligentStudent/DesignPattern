

public class User {
	private DataBase UsingDataBase_;
	private Table UsingTable_;
	private String id;
	private String pwd;
	private long start;
	public User(String Id, String Password) {
		UsingDataBase_ = null;
		UsingTable_ = null;
		id = Id;
		pwd = Password;
		timeRenew();
		
	}

	public void timeRenew(){
		start = System.currentTimeMillis();
	}

	public long GetStartTime(){
		return start;
	}
	
	public boolean SetDatabase(DataBase usingDataBase) {
		if(usingDataBase == null)
		{
			System.out.println("User: no parameter set");
			return false;
		}
		
		UsingDataBase_ = usingDataBase;
		return true;
	}
	
	public DataBase GetDatabase() {
		if(UsingDataBase_ == null) {
			System.out.println("User: has not database");
		}
		return UsingDataBase_;
	}
	
	public boolean SetTable(Table usingtable) {
		if(usingtable == null)
		{
			System.out.println("User: not set table parameter");
			return false;
		}
		
		UsingTable_ = usingtable;
		return true;
	}
	
	public Table GetTable() {
		if(UsingTable_ == null) {
			System.out.println("User : is not exist table");
		}
		return UsingTable_;
	}
	
	public String GetId() {
		timeRenew();
		return id;
	}
	
	public String GetPwd() {
		return pwd;
	}
}
