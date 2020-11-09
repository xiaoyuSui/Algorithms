package divide;

import java.util.PriorityQueue;
import java.util.Queue;

import javax.imageio.stream.IIOByteBuffer;

public class backpack {
	static int[] weight= {20,30,10} ;	//��Ʒ����
	static int[] value= {60,120,50};	//��Ʒ��ֵ
	static double[] ratio;	///��Ʒ��ֵ��������ֵ
	static int[] solution;	//ѡ����
	static int max=50;		//�����������
	static int n;			//��ѡ��Ʒ����
	static int total=0;		//�ܼ�
	public static void sort() {//�����ݰ��ձ�ֵ�Ӵ�С����
		n=weight.length;
		ratio=new double[n];
		solution=new int[n];
		for(int i=0;i<n;i++) {	//��ʼ����ֵ������ֵ�����˳������
			ratio[i]=value[i]/weight[i];
		}
		for(int i=0;i<n;i++){//ð�����򣬸ı�˳�����鲻�ı���������
            double tem = 0;
            int tempint=0;
            // �ڲ�forѭ���������ڵ�����Ԫ�ؽ��бȽ�
            for(int j=i+1;j<n;j++){
               if(ratio[i]<ratio[j]){
                   tem = ratio[j];
                   ratio[j]=ratio[i];
                   ratio[i]=tem;
                   
                   tempint = weight[j];
                   weight[j]=weight[i];
                   weight[i]=tempint;
                   
                   tempint =value[j];
                   value[j]=value[i];
                   value[i]=tempint;
                   
               }
            }
		}
	}
	
	public static void put() {
		int no=0;
		for(int i=0;i<n;i++) {
			if(max>=weight[i]) {
				max-=weight[i];	
				solution[i]=1;
				total+=value[i];
			}	
			else break;
		}
	}
	public static void main(String[] args) {
		sort();
		put();
		System.out.println("����������Ϊ50");
		System.out.println("��Ʒ    �Ƿ�ѡ��    ����    ����");
		for(int i=0;i<n;i++) {
			System.out.println((i+1)+"\t"+solution[i]+"\t"+ratio[i]+"\t"+weight[i]);
		}
		System.out.print("����ܼ�ֵΪ��"+total);
	}
}
