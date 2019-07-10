
public class Table{
    private Scheme scheme;
    private String tableName;
    private String FilePath;//������ ���� ����� ���� ������� ����
    private String name;
    public Table(DataBase db,String Name, String Scheme){
        System.out.println(Scheme);
        Scheme = AAA(Scheme);
    	String[] scheme_Storage = Scheme.split(",");
    	FilePath = "./"+Name+".txt";
    	tableName = db.GetName()  + "/" + Name; //  databasename/name
    	scheme = new Scheme(scheme_Storage);
    	this.name = Name;
    	// ( ~~~ )
    	FileManager.GetInstance().CreateNewFile(tableName, Scheme);
    }

    public String GetName(){
        return name;
    }
    private String AAA(String Scheme)
    {
        String temp = null;
        String [] Scheme_temp_Storage = Scheme.split(" ");

        for(int i=0; i<Scheme_temp_Storage.length; i++)
        {
            if(i==0||(i==Scheme_temp_Storage.length-1)){
                continue;
            }

            else{
                if(temp == null){
                    temp = Scheme_temp_Storage[i];
                }

                else{
                    temp += Scheme_temp_Storage[i];
                }
            }
        }

        return temp;
    }

    public void SelectData(String key){
    	FileManager.GetInstance().Read(tableName, key);
    }
    
    public void InsertData(String Data){
    	String[] data = Data.split(",");
    	FileManager.GetInstance().Write(tableName, data);
    }
    
    public void DeleteData(String parameter){
        FileManager.GetInstance().DeleteData(tableName, parameter);

    }

    public void Drop(String parameter){
        FileManager.GetInstance().DeleteFile(parameter);
    
    }
}