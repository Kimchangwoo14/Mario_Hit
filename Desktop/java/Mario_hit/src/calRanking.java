import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class calRanking {

	public BufferedReader fr;//filereader
	public BufferedWriter fw;//filewriter
	public int [] Rank = new int[5];//��ũ�� ������ �迭
	private int count;
	private String stringRank;
	public calRanking(){
		for(int i=0;i<5;i++) {
			Rank[i] = 0; 
		}
	}
	
	
	public void fileread(){//���Ͽ��� ��ũ�� �о��
		count = 0;
		try {
			fr = new BufferedReader(new FileReader (new File("test.txt")));
			String data = "";
			while ((data = fr.readLine()) != null)
			{ 
				stringRank = data;
				Rank[count] = Integer.parseInt(stringRank);//���� �����͸� ��ũ �迭�� int������ ����
				count++;
			}

			fr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
	
	public void filewrite() {//��ũ�� ���Ͽ� ����
		try {
			fw =  new BufferedWriter(new FileWriter (new File("test.txt")));
			for(int i=0;i<5;i++) {
			fw.write(Rank[i] + "\r\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
