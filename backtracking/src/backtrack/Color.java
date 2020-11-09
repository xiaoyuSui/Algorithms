package backtrack;

public class Color {
	static int nodeNum;	//节点个数
	//static int count;	//当前染色方案数
	static int type;	//记录最小颜色数
	static int line[][];//邻接矩阵
	static int result[];//染色结果
	static int color[];//染色过程记录
	
	public static boolean OK(int k) {//判断节点k当前染色方案是否可行
		for(int i=0;i<nodeNum;i++)
			if(line[k][i]==1&&(color[k]==color[i]))
				return false;
		return true;	
	}
	public static void backTrack(int t) {//回溯
		if (t >nodeNum){	//一个染色方案完成1
			int a=getType();//获取本次染色数
			if(a<type) 	//如果本次染色数更少，置换记录
				type=a;
	    }
	    else{
	        for (int i = 1; i <=type; i++){	//颜色数增加
	            color[t - 1] = i;			//节点t着色为i
	            if (OK(t-1)) {				//此着色方式可行
	                backTrack(t + 1); 		//查看下一个节点
	                if(result[t-1]!=i)
	                	result[t-1]=i;  
	            }
	            else{
	                color[ t - 1] = 0;//不可行，回到循环去增加颜色数
	            }
	        }
	    }
	}
	public static int getType() {//获取当前染色方案的染色数
		int type=color[0];
		for(int i=0;i<nodeNum;i++) {
			if(color[i]>type)
				type=color[i];
		}
		return type;
	}
	
	public static void Coloring(int n,int a[][]){
		nodeNum=n;
		type=n;
		line=a;
		result=new int[n];
		color=new int[n];
		backTrack(1);

	}
	public static void main(String[] args) {
	int a[][]= {{0,1,1,0,0},
				{1,0,1,1,1},
				{1,1,0,0,1},
				{0,1,0,0,1},
				{0,1,1,1,0}};
	Coloring(5, a);
	System.out.println("最少涂色数量为："+type);
	System.out.print("涂色方案为：");
	for(int i=0;i<5;i++) 
		System.out.print(result[i]+"-");
	
	}
	

}
