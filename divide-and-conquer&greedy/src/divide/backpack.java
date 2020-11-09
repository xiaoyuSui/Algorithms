package divide;

import java.util.PriorityQueue;
import java.util.Queue;

import javax.imageio.stream.IIOByteBuffer;

public class backpack {
	static int[] weight= {20,30,10} ;	//物品重量
	static int[] value= {60,120,50};	//物品价值
	static double[] ratio;	///物品价值和重量比值
	static int[] solution;	//选择结果
	static int max=50;		//背包最大重量
	static int n;			//可选物品数量
	static int total=0;		//总价
	public static void sort() {//把数据按照比值从大到小排序
		n=weight.length;
		ratio=new double[n];
		solution=new int[n];
		for(int i=0;i<n;i++) {	//初始化价值重量比值数组和顺序数组
			ratio[i]=value[i]/weight[i];
		}
		for(int i=0;i<n;i++){//冒泡排序，改变顺序数组不改变数据数组
            double tem = 0;
            int tempint=0;
            // 内层for循环控制相邻的两个元素进行比较
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
		System.out.println("背包最大承重为50");
		System.out.println("物品    是否选择    单价    重量");
		for(int i=0;i<n;i++) {
			System.out.println((i+1)+"\t"+solution[i]+"\t"+ratio[i]+"\t"+weight[i]);
		}
		System.out.print("最大总价值为："+total);
	}
}
