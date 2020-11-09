package branchbound;

import java.sql.Struct;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class TSP {
	static public class Node implements Comparable<Node> {
		public int[][] used;//路径
		public double[] inf;//结点使用情况+lb
		public Node(int n){
			used=new int[n][2];
			inf=new double[n+1];
		}
		public Node(int n,double[] inf) {
			used=new int[n][2];
			this.inf=inf;
		}
		public Node (int[][] used,double[] inf){
			this.used=new int[5][2];
			this.inf=new double[6];
			for(int i=0;i<5;i++) {
				this.used[i]=used[i].clone();
			}
			this.inf=inf.clone();
		}
		@Override
		public int compareTo(Node node) {
			int n=inf.length;
			return (int) (this.inf[n-1]-node.inf[n-1]);
		}
		public boolean isLeaf() {
			
			for(int i=0;i<inf.length-1;i++) {
				if(inf[i]==0)//没用过的
					return false;
			}
			return true;
		}
	};
	
	static int n;
	static double low,up;
	static int best=999;//最短路程长度
	static int[][] routine=new int[5][2];//最优路径
	static int[][] graph= {
			{0,3,1,5,8},
			{3,0,6,7,9},
			{1,6,0,4,2},
			{5,7,4,0,3},
			{8,9,2,3,0},};
	static Queue<Node> pq=new PriorityQueue<Node>();
	public static double  upbound() {//上界
		n=graph[0].length;
		int[] used=new int[n];
		int p=minP(graph[0],used);//找到第一行最小值位置
		int ub=graph[0][p];//第一行最小值
		int temp;
		for(int i=1;i<n;i++) {
			temp=minP(graph[p],used);//返回下一行最小位置
			ub+=graph[p][temp];//上界
			p=temp;//最小值位置变成下一个行数
			used[i]=p;
		}
		return ub;
	} 
	public static double lowbound() {//初始下界
		int a;
		int b;
		int lb=0;
	for(int i=0;i<n;i++){
		a=999;
		b=999;
		for(int j=0;j<n;j++) {
			if(graph[i][j]<a&&graph[i][j]!=0)
				a=graph[i][j];
		}
		for(int j=0;j<n;j++) {
			if(graph[i][j]<b&&graph[i][j]>a)
				b=graph[i][j];
		}
		lb+=a;
		lb+=b;
	}
	return lb/2;
	}
	public static double[] lb(int[][] used) {//计算结点下界
		double lb=0;//下界
		int col;//列
		int row;//行
		int a,b;
		double[] fresh=new double[n+1];
		int[] count=new int[n];
		int[] pos=new int[n];
		int i=0;
		while(!(used[i][0]==0&&used[i][1]==0)) {//遍历用过的行
			row=used[i][0];
			col=used[i][1];
			lb+=2*graph[row][col];
			//lb+=min(graph[row], col);
			//lb+=min(graph[col], row);
			count[row]++;
			count[col]++;
			pos[row]=col;
			pos[col]=row;
			i++;	
			fresh[row]=1;//用过的行为1，没用过为0
			fresh[col]=1;
		}
		for(int j=0;j<n;j++) {
			
				if(count[j]==0) {//加上这一行最小的两个
					a=999;b=999;
					for(int k=0;k<n;k++) {
						if(graph[j][k]<a&&graph[j][k]!=0)
							a=graph[j][k];
					}
					for(int k=0;k<n;k++) {
						if(graph[j][k]<b&&graph[j][k]>a)
							b=graph[j][k];
					}
					lb+=a;
					lb+=b;
				}
				if(count[j]==1) {
					lb+=min(graph[j], pos[j]);
				}	
			}
		
		fresh[n]=lb/2;
		return fresh;
	}
	public static int minP(int[] a,int[] used) {//返回未用过的最小值出现位置
		int n=0;//位置
		int min=10;//数值
		for(int i=0;i<a.length;i++) {
			if(a[i]<min&&a[i]!=0&&!isIn(i,used)) {
				min=a[i];//最小值
				n=i;//最小值出现位置
			}	
		}
		return n;
	}
	public static boolean isIn(int i,int[] used) {//判断i属不属于used
		for(int k=0;k<n;k++) {
			if(i==used[k])
				return true;
		}
		return false;
	}
	public static int  min(int[] a,int k) {//赶回一行中列数不为k的最小值
		int min=999;
		for(int i=0;i<n;i++) {
			if(a[i]!=0&&a[i]<min&&i!=k)
				min=a[i];
		}
		return min;
		
	}
	public static void Solution() {
		int length=0;
		int k=0;//used下一个填的行数
		int last=0;//上一个走过的结点
		double[] inf;
		int[][] used;
		int row=0;
		int col=0;
		while(!pq.isEmpty()) {
			Node node=pq.poll();
			used=node.used;
			k=0;
			if(node.isLeaf()) {//判断是叶子节点
				for(int i=0;i<n-1;i++) {
					row=used[i][0];
					col=used[i][1];
					length+=graph[row][col];
				}
				length+=graph[col][0];
				if(length<best) {//置换最优
					best=length;
					for(int i=0;i<n;i++) {
						routine[i]=used[i].clone();
					}
				}
			}
			else {//不是叶子结点，向下分支
			while (!(used[k][1]==0&&used[k][0]==0)) {
				k++;
			}
			if(k!=0)
				last=used[k-1][1];
			else {
				last=0;
				node.inf[0]=1;
			}
				
			for(int i=0;i<n;i++) {
				if(node.inf[i]==0) {//可以扩展的分支
					used[k][0]=last;
					used[k][1]=i;
					inf=lb(used);//计算下界和结点使用情况
					if(inf[n]>=low&&inf[n]<=up) {//lb大于下界，放入队列
						pq.add(new Node(used,inf));
					}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		up=upbound();
		low=lowbound();
		pq.add(new Node(n));
		Solution();
		System.out.print("最优路径为：");
		for(int i=0;i<n-1;i++) {
			System.out.print((routine[i][0]+1)+"-");
		}
		System.out.print(routine[n-2][1]+1+"-1");
		System.out.print("\n路径长度为："+best);
		
		
		
		
	}
}
