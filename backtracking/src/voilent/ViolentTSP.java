package voilent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;


public class ViolentTSP {
	int start=0;//开始节点
	int num_visited=1;//已经访问过的节点的个数
	int index=0;//当前访问节点
	int num_city=4;//图中节点个数
	int visited[]=new int[num_city];//存储节点被访问状态
	int sum[]=new int[100];//每条路径的总长度
	int min=1000;//最小路径长度
	int l[][]={{0,3,6,7},{12,0,2,8},{8,6,0,2},{3,7,6,0}};//邻接矩阵
	int path[][]=new int[1000][num_city+1];//存储所有路径
	int route=0;//当前路径序号
	void dfs(int index) {
		if(num_visited!=num_city) {//如果不是只剩一个节点没有访问则需要判断
			for (int i = 0; i < num_city; i++) {//深度优先遍历
				if(visited[i]==0) {
					visited[i] = 1;
	                path[route][num_visited-1] = index;
	                num_visited++;
	                dfs(i);
	                //回溯
	                num_visited--;
	                visited[i] = 0;
				}
			}
		}
		else {
			path[route][num_visited-1] = index;//最后一个未访问节点
	        path[route][num_visited] = start;//回到起始节点
	        System.out.print("路线"+(route+1)+"为");
	        sum[route] = 0;
	        for(int i=0;i<num_city;i++)//计算路径总长并输出路径
	        {
	            sum[route] += l[ path[route][i] ][ path[route][i+1] ];
	            System.out.print((path[route][i]+1)+" --> ");
	            path[route+1][i] = path[route][i];
	        }
	        if(min > sum[route])//最短路径对比
	        {
	            min = sum[route];
	        }
	        System.out.print((path[route][num_city]+1)+"\n ");
	        System.out.print("该路线总长度为："+sum[route]+"\n");
	        route++;
		}	
	}
	public static void main(String args[]) {
		ViolentTSP tsp=new ViolentTSP();
		for(int i=0;i<tsp.num_city;i++) {//从每个节点依次出发
			for(int j=0;j<tsp.num_city;j++) {//每次将visited数组清零
				tsp.visited[j]=0;
			}
			tsp.start=i;
			tsp.visited[i]=1;
			tsp.dfs(i);
		}
		System.out.print("最短路径长度为"+tsp.min);
	}	
}
