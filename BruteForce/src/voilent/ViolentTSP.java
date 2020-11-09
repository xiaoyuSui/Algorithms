package voilent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;


public class ViolentTSP {
	int start=0;//��ʼ�ڵ�
	int num_visited=1;//�Ѿ����ʹ��Ľڵ�ĸ���
	int index=0;//��ǰ���ʽڵ�
	int num_city=4;//ͼ�нڵ����
	int visited[]=new int[num_city];//�洢�ڵ㱻����״̬
	int sum[]=new int[100];//ÿ��·�����ܳ���
	int min=1000;//��С·������
	int l[][]={{0,3,6,7},{12,0,2,8},{8,6,0,2},{3,7,6,0}};//�ڽӾ���
	int path[][]=new int[1000][num_city+1];//�洢����·��
	int route=0;//��ǰ·�����
	void dfs(int index) {
		if(num_visited!=num_city) {//�������ֻʣһ���ڵ�û�з�������Ҫ�ж�
			for (int i = 0; i < num_city; i++) {//������ȱ���
				if(visited[i]==0) {
					visited[i] = 1;
	                path[route][num_visited-1] = index;
	                num_visited++;
	                dfs(i);
	                //����
	                num_visited--;
	                visited[i] = 0;
				}
			}
		}
		else {
			path[route][num_visited-1] = index;//���һ��δ���ʽڵ�
	        path[route][num_visited] = start;//�ص���ʼ�ڵ�
	        System.out.print("·��"+(route+1)+"Ϊ");
	        sum[route] = 0;
	        for(int i=0;i<num_city;i++)//����·���ܳ������·��
	        {
	            sum[route] += l[ path[route][i] ][ path[route][i+1] ];
	            System.out.print((path[route][i]+1)+" --> ");
	            path[route+1][i] = path[route][i];
	        }
	        if(min > sum[route])//���·���Ա�
	        {
	            min = sum[route];
	        }
	        System.out.print((path[route][num_city]+1)+"\n ");
	        System.out.print("��·���ܳ���Ϊ��"+sum[route]+"\n");
	        route++;
		}	
	}
	public static void main(String args[]) {
		ViolentTSP tsp=new ViolentTSP();
		for(int i=0;i<tsp.num_city;i++) {//��ÿ���ڵ����γ���
			for(int j=0;j<tsp.num_city;j++) {//ÿ�ν�visited��������
				tsp.visited[j]=0;
			}
			tsp.start=i;
			tsp.visited[i]=1;
			tsp.dfs(i);
		}
		System.out.print("���·������Ϊ"+tsp.min);
	}	
}
