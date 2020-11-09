package voilent;

import java.awt.FontFormatException;

public class VoilentBackpack {
	
	
	public static void main(String args[]) {
		int value=0;//记录最大总价值
		int[][]item= {{7,42},{3,12},{4,40},{5,25}};//存储物品重量和价值的矩阵，一行代表一个物品，每行第一位表示重量第二位表示价值
		int[][] backpack=new int[10][3];//记录所有装包选择，每行对应一种选择，前两位对应选择的物品序号，第三位对应总价值
		int max=10;//最大总重
		int k=0;//选择的序号
		for(int i=0;i<4;i++) {
			if(item[i][0]<max) {
				backpack[k][0]=i+1;
				backpack[k][1]=0;
				backpack[k][2]=item[i][1];//背包中只放入一个物品
				k++;
				if(i<3) {
					for(int j=i+1;j<4;j++) {//在已经放入一个物品的基础上再向背包中添加物品
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
			if(backpack[i][2]>value)//判断最大价值
				value=backpack[i][2];
			if(backpack[i][1]!=0)
				System.out.print(i+1+"\t选择："+backpack[i][0]+"和"+backpack[i][1]+",总价值为"+backpack[i][2]+"\n");
			else {
				System.out.print(i+1+"\t选择："+backpack[i][0]+"       总价值为"+backpack[i][2]+"\n");
			}
		}
		System.out.print("总价值最高为："+value);
		
	}
	
					
	}

