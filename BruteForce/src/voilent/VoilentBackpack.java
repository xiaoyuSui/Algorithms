package voilent;

import java.awt.FontFormatException;

public class VoilentBackpack {
	
	
	public static void main(String args[]) {
		int value=0;//��¼����ܼ�ֵ
		int[][]item= {{7,42},{3,12},{4,40},{5,25}};//�洢��Ʒ�����ͼ�ֵ�ľ���һ�д���һ����Ʒ��ÿ�е�һλ��ʾ�����ڶ�λ��ʾ��ֵ
		int[][] backpack=new int[10][3];//��¼����װ��ѡ��ÿ�ж�Ӧһ��ѡ��ǰ��λ��Ӧѡ�����Ʒ��ţ�����λ��Ӧ�ܼ�ֵ
		int max=10;//�������
		int k=0;//ѡ������
		for(int i=0;i<4;i++) {
			if(item[i][0]<max) {
				backpack[k][0]=i+1;
				backpack[k][1]=0;
				backpack[k][2]=item[i][1];//������ֻ����һ����Ʒ
				k++;
				if(i<3) {
					for(int j=i+1;j<4;j++) {//���Ѿ�����һ����Ʒ�Ļ��������򱳰��������Ʒ
						if(item[j][0]+item[i][0]<=max) {
							backpack[k][0]=i+1;
							backpack[k][1]=j+1;
							backpack[k][2]=item[i][1]+item[j][1];
							k++;
						}
							
					}
				}
				
			}
		}
		for(int i=0;i<k;i++) {
			if(backpack[i][2]>value)//�ж�����ֵ
				value=backpack[i][2];
			if(backpack[i][1]!=0)
				System.out.print(i+1+"\tѡ��"+backpack[i][0]+"��"+backpack[i][1]+",�ܼ�ֵΪ"+backpack[i][2]+"\n");
			else {
				System.out.print(i+1+"\tѡ��"+backpack[i][0]+"       �ܼ�ֵΪ"+backpack[i][2]+"\n");
			}
		}
		System.out.print("�ܼ�ֵ���Ϊ��"+value);
		
	}
	
					
	}

