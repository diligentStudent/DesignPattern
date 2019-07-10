import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManager {

	private List<File> fileStorage = new ArrayList();
	private File file;
	private String name;
	private String Path = "./";

	static FileManager instance = new FileManager();

	public FileManager() {

	}

	public static FileManager GetInstance() {
		return instance;
	}

	public void CreateNewDirectory(String Name) {

		File file = new File("./" + Name);

		file.mkdir();
	}
	
	public void DeleteFile(String Name)
	{
		File deleteFile = new File("./" + Name);
		
		if(!deleteFile.isFile())//이름이 디렉토리인지 확인
		{
			System.out.println("There is no File Matched with the Parameter.");
			return;
		}
		
		if(deleteFile.exists())
		{
			//transaction들어갈 자리
			deleteFile.delete();
			System.out.println("Successfully erase file");
		}
		
		else {
			System.out.println("The file name you want to erase does not exist.");
		}
	}
	
	public void DeleteDirectory(String Name) {
		File deleteFolder = new File("./" + Name);
		
		if(!deleteFolder.isDirectory())//이름이 디렉토리인지 확인
		{
			System.out.println("There is no Folder Matched with the Parameter.");
			return;
		}
		
		if(deleteFolder.exists())
		{
			//transaction들어갈 자리
			File[] deleteFolderList = deleteFolder .listFiles();//삭제할 폴더하위에 파일 존재 할 경우 지워지지않기때문에
															//하위파일을 모두 제거하는 작업
			for(int i=0; i<deleteFolderList.length; i++)
			{
				deleteFolderList[i].delete();
			}
		
			deleteFolder.delete();
			System.out.println("Successfully erase folder");
		}
		
		else {
			System.out.println("The folder name you want to erase does not exist.");
		}
	}

	public void CreateNewFile(String Name, String Scheme) {
		name = Name;
		String[] scheme = Scheme.split(",");

		String path = Path + name + ".txt";

		if (!CreateFile(path)) {
			System.out.println("createFile is failed");
		} else {
			System.out.println("createFile success");
			Write(Name, scheme);
		}
	}

	public void Show(List<String> pParam) {
		for (String res : pParam) {
			if (res != null)
				System.out.println(res);
		}
	}

	public void Read(String filePath, String key) {
		String TextContainer;// Text占쏙옙 占쌈시뤄옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
		String[] key_Storage = key.split(" ");// String占쏙옙占싸듸옙 key占쏙옙 占쏙옙占썽문占쌘몌옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌩띰옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙
		List<Integer> Scheme_index = new ArrayList<Integer>();// key占쏙옙占쏙옙 占쏙옙트占쏙옙占쏙옙트占쏙옙 占쏙옙占승곤옙占� 占쌍놂옙 index占쏙옙 占쌍댐옙 List
		List<String> Result_List = new ArrayList<String>();// 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙트
		try {

			file = new File("./" + filePath + ".txt");// 占쏙옙占싹울옙占쏙옙

			if (!file.isFile()) {
				System.out.println("file is not exist");
				return;
			}

			BufferedReader br = new BufferedReader(new FileReader(file));

			String Scheme_Line = br.readLine();// Scheme占싸븝옙占쏙옙 占쏙옙占쏙옙
			String[] Storage = Scheme_Line.split("\t");// Scheme_Line占쏙옙占쏙옙 占쏙옙占쏙옙 Scheme占쏙옙 String占썼열占싸븝옙환

			if (key_Storage[0].compareTo("*") != 0) {
				for (String key_elem : key_Storage)// key占쏙옙占쏙옙 占쏙옙트占쏙옙占쏙옙트占쏙옙 占쏙옙占승곤옙占� 占쌍놂옙 index占쏙옙 占쌍댐옙 List
				{
					int index = 0;
					for (String Compare_elem : Storage) {
						if (key_elem.compareTo(Compare_elem) == 0) {
							Scheme_index.add(index);
						}
						index++;
					}

				}
			}

			else{
				int index = 0;
				for(int i=0; i< Storage.length; i++){
					Scheme_index.add(index);
					index++;
				}
			}

			while (true) {
				int index = 0;
				String Result = null;// 占쏙옙 占쏙옙占시울옙占쏙옙 찾占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占�
				TextContainer = br.readLine();

				if (TextContainer == null) {
					break;
				}

				String[] Contents_Storage = TextContainer.split("\t");// 占쏙옙占쏙옙占쏙옙 占싻억옙 占쏙옙占쏙옙占쏙옙 split占싹울옙 String占썼열占쏙옙 占쏙옙占쏙옙

				for (String Contents : Contents_Storage) {
					for (Integer idx : Scheme_index) {
						if (index == idx) {
							if (Result == null) {
								Result = Contents;
							}

							else {
								Result += Contents;
							}

							Result += "\t";
						}
					}
					index++;
				}
				Result_List.add(Result);
			}

			Show(Result_List);

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean Compareschemeandkey(String parameter, String[]values)
	{
		String[] line_Contents = parameter.split("\t");//읽은 라인에 대하여 내용을 넣는 배열
		int Count = 0;
		int valuesCount = 0;
		for(String temp :values) {
			if(temp==null)
				break;
			else
				valuesCount++;
		}
		
		for(String content:line_Contents)
		{
			for(String el: values)
			{
				if(el == null) {
					break;
				}
				
				if(content.compareTo(el)==0)
				{
					Count++;
				}
			}
		}
		if(Count == valuesCount)
			return true;
		else
			return false;
	}
	
	public void Write(String filePath, String[] data) {
		try {
			file = new File("./" + filePath + ".txt");// 占쏙옙占싹울옙占쏙옙

			if (!file.isFile()) {
				System.out.println("file is not exist");
				return;
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

			for (String elem : data) {
				if (elem == null) {
					bw.write("\t");
					continue;
				}
				elem = elem.trim();
				bw.write(elem); // 占쌔댐옙 String占쏙옙占쌍댐옙 data占쏙옙 value占쏙옙占쏙옙 占싹놂옙占쏙옙 占쏙옙占싹울옙 占쏙옙
				bw.write("\t"); // 占쏙옙 value占쏙옙占쏙옙占쏙옙 占신몌옙
			}
			bw.newLine(); // 占쏙옙占쏙옙 占쌕넣억옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쌕꾸억옙占쏙옙
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean CreateFile(String addPath) {
		try {
			file = new File(addPath);
			if (file.isFile()) {
				return false;
			}

			else {
				file.createNewFile();
				fileStorage.add(file);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
	}

	public String GetName() {
		return name;
	}

	public static int[] ConvertIntegers(List<Integer> integers)// 占쏙옙占쏙옙占쏙옙 키占쏙옙 占쏙옙트占쏙옙占쏙옙트占쏙옙 占쏙옙占승곤옙美占쏙옙占� 占쌍댐옙占쏙옙 占싯깍옙占쏙옙占쏙옙 占쏙옙占쏙옙求占�
																// 占쌉쇽옙
	{
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}
	
	public void DeleteData(String fileName, String parameter)
	{try {
		file = new File("./" + fileName + ".txt");
		String lowerparam = parameter.toLowerCase();
		String[] Datas_Storage = lowerparam.split(" ");//put all Datas in DataStoraget
		String tempstr = null;
		String[] key = new String[100]; 
		String[] value = new String[100];
		String dummy = "";
		String line = null;
		int keyCount = 0;
		int valueCount = 0;
		
		//input only key and value in tempstr; 
		for(String var : Datas_Storage)
		{
			if(var.compareTo("=") == 0) {
				continue;
			}
			
			else if(var.compareTo("where") == 0) {
				continue;
			}
			
			else {
				if(tempstr == null) {
					tempstr = var + ' ';
					continue;
				}
				else {
					tempstr += var + ' ';
					continue;
				}
			}
		}
		
		//change Datastorage contents by key and value
		Datas_Storage = tempstr.split(" ");
		int count = 0;
		//seperate Data_Storage by key and value
		for(String var: Datas_Storage)
		{
			if(var.compareTo("")==0) {
				continue;
			}
			
			if((count%2)==1) {
				value[valueCount] = var;
				valueCount++;
				count++;
			}
			else {
				key[keyCount] = var;
				keyCount++;
				count++;
			}
		}
			
		if (key[0].compareTo("*") != 0) {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while((line = br.readLine())!=null)
			{
							
				if(Compareschemeandkey(line,value)==true) {//방금읽은 라인이 value와같다면건너뜀
				System.out.println("delete string: "+line);
					continue;
				}
				dummy += (line + "\r\n" );				
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(dummy);
			bw.close();
			br.close();
		}
		
		else {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			BufferedReader br = new BufferedReader(new FileReader(file));
						
			line = br.readLine();
			bw.write(line);
			bw.close();
			br.close();
		}
		
		
		
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}