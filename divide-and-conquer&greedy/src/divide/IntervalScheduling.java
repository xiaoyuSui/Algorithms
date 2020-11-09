package divide;

import java.net.Inet4Address;

import javax.naming.ldap.SortControl;
import javax.xml.stream.events.StartDocument;

public class IntervalScheduling {

	int[] start;
	int[] end;
	int[] num;
	int[] solution;
	int n;
	public IntervalScheduling(int[] s,int[] e) {//���캯��
		start=s.clone();
		end=e.clone();
		n=end.length;
		num=new int[n];
		solution=new int[n];
		for(int i=0;i<end.length;i++) {
			num[i]=i;
		}
	}
	public void sort() {
		int temp=0;
		
		for(int i=0;i<end.length;i++) {
			for(int j=i+1;j<end.length;j++) {//ð�����򣬽�����ʱ�䰴��С��������num��¼������λ��
				if(end[i]>end[j]) {
					temp=end[i];
					end[i]=end[j];
					end[j]=temp;
					
					temp=start[i];
					start[i]=start[j];
					start[j]=temp;
					
					temp=num[i];
					num[i]=num[j];
					num[j]=temp;
				}
			}
		}
	}
	public void choose() {//ѡ����
		
		int t=0,no=0;
		for(int i=0;i<n;i++) {
			if(t<start[i]) {
				no=num[i];
				solution[no]=1;
				t=end[i];
			}
		}
	}
	public static void main(String[] args) {
		int[] s= {1,2,4,6,8};
		int[] e= {3,5,7,9,10};
		IntervalScheduling intervalScheduling=new IntervalScheduling(s,e);
		intervalScheduling.sort();
		intervalScheduling.choose();
		
		for(int i=0;i<s.length;i++) {
			System.out.print(i+1+": ");
			if(intervalScheduling.solution[i]==1) {
				System.out.print("ѡ��˹���������ʱ��Ϊ"+s[i]+"-"+e[i]);
			}else {
				System.out.print("��ѡ��˹���");
			}
			System.out.println(" ");	
		}
	}
}
