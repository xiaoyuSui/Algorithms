package backtrack;

import java.util.Scanner;

public class Gallery {
	/*static final int MAX = 1000;
	static int d[][] = { {0,0,0}, {0,0,0}, {0,0,-1}, {0,-1,0}, {0,0,1}, {0,1,0} };
	static int [][]x=new int[MAX][MAX];
	static int [][]y=new int[MAX][MAX]; 
	static int [][]bestx=new int[MAX][MAX];   //x用来设置当前警卫，y用来表示监控情况，bestx返回最终结果
	static int n, m, best, k = 0, t = 0;   //当前已设置的警卫数为k，受监视的陈列室数为t，当前最少警卫数为best
	static int t1, t2, more;               //判断下界剪枝的条件参数
	boolean p;

	/**
	 * 世界名画陈列馆问题（回溯法）
	 *
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("请设置陈列馆区域:");
		System.out.print("m: ");
		Scanner sc1=new Scanner(System.in);
		m=Integer.parseInt(sc1.next());
		System.out.print("n: ");
		sc1=new Scanner(System.in);
		n=Integer.parseInt(sc1.next());
	    compute(); //计算
	    System.out.println("最少需要"+best+"个警卫！");
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= m; j++) 
	        	System.out.print( bestx[i][j]+" ");
	        System.out.println();
	    }

	}
	static void change(int i, int j) {    //在(i, j)处设置一个警卫，并改变其周围受监控情况
	    x[i][j] = 1;
	    k++;
	    for (int r = 1; r <= 5; r++) {    //在自己本身跟上下左右五个地方设置受控
	        int p = i + d[r][1];
	        int q = j + d[r][2];
	        y[p][q]++;
	        if (y[p][q] == 1)
	            t++;
	    }
	}
	static void restore(int i, int j) {    //撤销在(i, j)处设置的警卫，并改变其周围受监控情况
	    x[i][j] = 0;
	    k--;
	    for (int r = 1; r <= 5; r++) {
	        int p = i + d[r][1];
	        int q = j + d[r][2];
	        y[p][q]--;
	        if (y[p][q] == 0)
	            t--;
	    }
	}
	static void search(int i, int j) {   //回溯搜索
	    do {                             //从上到下，从左至右搜索没被监控的位置
	        j++;
	        if (j > m) {
	            i++;
	            j = 1;
	        }
	    } while (!((y[i][j] == 0) || (i > n)));
	    if (i > n) {
	        if (k < best) {            //刷新警卫值
	            best = k;
	            for (int p = 1; p <= n; p++)
	                for (int q = 1; q <= m; q++)
	                    bestx[p][q] = x[p][q];
	            return;
	        }
	    }
	    if (k + (t1 - t)/5 >= best)    return;            //警卫数下界 = 还需设置的最少警卫数 + 现有的警卫数
	    if ((i < n - 1) && (k + (t2 - t)/5 >= best))    return;   //如果比最优警卫数多的话，就剪去这一分枝
	    if (i < n) {                //结点p
	        change(i + 1, j);
	        search(i, j);            //递归搜索下一个点
	        restore(i + 1,j);        //恢复
	    }
	    if (y[i][j + 1] == 0) {        //结点q
	        change(i, j);
	        search(i, j);
	        restore(i, j);
	    }
	    if ((j < m) && ((y[i][j + 1] == 0) || (y[i][j + 2] == 0))) {    //结点r
	        change(i, j + 1);
	        search(i, j);
	        restore(i, j + 1);
	    }
	}

	static void compute() {
	    more = m/4 + 1;
	    if (m % 4 == 3)
	        more++;
	    else if (m % 4 == 2)
	        more += 2;
	    t2 = m * n + more + 4;
	    t1 = m * n + 4;
	    best = 65536;
	    if (m == 1 && n == 1) {
	    	System.out.println(1);
	    	System.out.println(1);
	    }
	    for (int i = 0; i <= m + 1; i++) {    //在整个外面加上一圈，便于处理边界情况
	        y[0][i] = 1;
	        y[n + 1][i] = 1;
	    }
	    for (int i = 0; i <= n + 1; i++) {
	        y[i][0] = 1;
	        y[i][m + 1] = 1;
	    }
	    search(1, 0);
	}*/
	
	
	static int[][] guard=new int[10][10];      	//警卫坐标
	static int[][] spy=new int[10][10]; 		//画廊被监督情况
	static int[][] result=new int[10][10]; 	 //返回最终结果
	static int row, col, best , k=0;

	
	public static void main(String[] args) {
		
		System.out.println("输入矩阵行数列数作为场地大小:");
		System.out.print("行数:");
		Scanner sc1 = new Scanner(System.in);
		row = Integer.parseInt(sc1.next());
		System.out.print("列数:");
		sc1 = new Scanner(System.in);
		col = Integer.parseInt(sc1.next());
		compute(); //计算
		System.out.println("最少需要"+best+"个警卫！");
		for(int i = 1;i <= row; i++) {
			for(int j = 1;j <= col;j++) {
			   System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	
	/**
	 * 在整个外面加上一圈，便于处理边界情况
	 */
	public static void compute() {
		best = 100;
		for(int i = 0;i <= col+1;i++) {
			spy[0][i] = 1;//左边界
			spy[row+1][i] = 1;//右边界
		}
		for(int i = 0;i <= col+1;i++) {
			spy[i][0] = 1;//上边界
			spy[i][col+1] = 1;//下边界
		}
		search(1,0);
	}
	
	//在（i，j）添加警卫并改变周边五个点监视状态
	public static void change(int i,int j) {
		guard[i][j] = 1;
		k++;
		spy[i][j+1]++;
		spy[i+1][j]++;
		spy[i][j]++;
		spy[i][j-1]++;
		spy[i-1][j]++;
	}
	
	//去掉（i,j）处的警卫并改变周边五个点监视状态
	public static void restore(int i,int j) {
		guard[i][j] = 0;
		k--;
		spy[i][j+1]--;
		spy[i+1][j]--;
		spy[i][j]--;
		spy[i][j-1]--;
		spy[i-1][j]--;
	}
	
	//每一行中从左到右回溯查找
	public static void search(int i,int j) {
		do {
			j++;//查找一行中的下一个
			if(j > col) {//一行找到头换行
				i++;
				j= 1;
			}
		}while(spy[i][j]!=0&&i<=row);
		
		//一次结束，如果得到的结果比记录最优好，置换
		if(i > row) {
			if(k < best) {
				best = k;
				for(int p = 1;p <= row;p++) 
					for(int q = 1;q <= col;q++) 
						result[p][q] = guard[p][q];
					return;
				}
			}
		
		if(i < row) {           //如果没到底边上
			change(i+1,j);		//在正下方安排警卫
			search(i,j);      	//递归搜索下一个点
			restore(i+1, j);  	//恢复
		}
		
		if((spy[i][j+1] == 0)) {//如果右边没有被监控
			change(i,j);		//在ij布置警卫
			search(i,j);		//递归搜索下一个节点
			restore(i, j);		//恢复
		}
		
		if(((spy[i][j+1] == 0) && (spy[i][j+2] == 0))) {//如果右边两个点都没有被监控
			change(i,j+1);								//在右侧的点布置警卫
			search(i,j);      							//查找下一个点
			restore(i, j+1);							//恢复
		}
	}


}
