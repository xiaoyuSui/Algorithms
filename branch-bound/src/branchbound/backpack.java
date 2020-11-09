package branchbound;
import java.io.UTFDataFormatException;
import java.util.PriorityQueue;
import java.util.Queue;
import branchbound.TSP.Node;

public class backpack {
	
	static public class Node implements Comparable<Node> {
		public double ub;//upbound
		public int no;//在排序后的数组中的位置
		public int[] used=new int[4];//使用过的
		
		public Node(double upbound,int no) {
			this.ub=upbound;
			this.no=no;
		}
		public Node(double upbound,int no,int[] used) {
			this.ub=upbound;
			this.no=no;
			this.used=used.clone();
		}
		@Override
		public int compareTo(Node o) {
			return (int)(o.ub-this.ub);
		}
	};
	static int[] weight= {4,7,5,3} ;	//物品重量
	static int[] value= {40,42,25,12};	//物品价值
	static double[] ratio;	///物品价值和重量比值
	static int[] solution;	//
	static int max=10;		//背包最大重量
	static int cweight;		//当前重量
	static double cvalue;	//当前记录最好价值
	static int n;			//可选物品数量
	static double upbound=0;
	static Queue<Node> pq=new PriorityQueue<Node>();
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

	public static void Solution() {
		cweight=0;
		cvalue=0;
		double ub_r=0;
		double ub_l=0;
		//pq.add(new Node(upbound, 0));
		Node node;
		while (!pq.isEmpty()) {
			node=pq.poll();
			int val=0;
			int left=max;
			for(int i=0;i<4;i++) {
				 val+=value[i]*node.used[i];
				 left-=weight[i]*node.used[i];
			}
			if(node.no==4) {//如果是叶子节点
				if(upbound==0||upbound>node.ub)
					upbound=node.ub;
				if(cvalue<val) {
					solution=node.used.clone();
					cvalue=val;
				}
			}                  
			else {
				ub_r=cvalue+ratio[node.no]*left;//不选择当前物品
				if(ub_r>=upbound) {//剪枝
					pq.add(new Node(ub_r,node.no+1,node.used));
				}
				if(weight[node.no-1]<left) {//有左子树
					left-=weight[node.no-1];
					ub_l=cvalue+value[node.no-1]+ratio[node.no]*left;//左子树ub
					if(ub_l>=upbound) {//剪枝
						node.used[node.no-1]=1;
						pq.add(new Node(ub_l, node.no+1,node.used));
					}
				}			
			}
		}
	}
	public static void main(String[] args) {
		sort();
		pq.add(new Node(0,1));
		Solution();
		System.out.print("重量 价值/重量 是否选择\n");
		for(int i=0;i<n;i++) {
			System.out.println(weight[i]+"\t"+ratio[i]+"\t"+solution[i]);
		}
		System.out.print("最优解为："+cvalue);
		
	}

}
