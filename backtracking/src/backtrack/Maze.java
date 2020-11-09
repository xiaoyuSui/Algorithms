package backtrack;

import java.util.Stack;

public class Maze {
	public static Point cur;
	public static int[][]maze= {
			{0,0,0,0,0,0,0,0,0,0},
			{0,1,1,0,1,1,1,0,1,0},
			{0,1,1,0,1,1,1,0,1,0},
			{0,1,1,1,1,0,0,1,1,0},
			{0,1,0,0,0,1,1,1,1,0},
			{0,1,1,1,0,1,1,1,1,0},
			{0,1,0,1,1,1,0,1,1,0},
			{0,1,0,0,0,1,0,0,1,0},
			{0,0,1,1,1,1,1,1,1,0},
			{0,0,0,0,0,0,0,0,0,0}
	};
	public static Stack stack=new Stack();
	
	public static void Solution(int x,int y) {
			cur=(Point) stack.peek();	
			if(cur.x==8&&cur.y==8)
				return;
			maze[cur.x][cur.y]=2;//����ջ��
			if(maze[cur.x+1][cur.y]==1) {//������
				stack.add(new Point(cur.x+1, cur.y));
				Solution(x+1, y);
			}
			if(maze[cur.x][cur.y+1]==1) {//������
				stack.add(new Point(cur.x, cur.y+1));
				Solution(x, y+1);
			}
			if(maze[cur.x][cur.y-1]==1) {//������
				stack.add(new Point(cur.x, cur.y-1));
				Solution(x, y-1);
			}
			if(maze[cur.x-1][cur.y]==1) {//������
				stack.add(new Point(cur.x-1, cur.y));
				Solution(x-1, y);
			}
				maze[cur.x][cur.y]=1;//����߲�ͨ���˾����������û�б���
				stack.pop();//����ջ��������
				cur=(Point)stack.peek();//���õ�ǰλ��Ϊջ��
			
	}
	public static void main(String[] args) {
		stack.add(new Point(1,1));//����ʼλ����ջ
		Solution(1,1);//���ݷ�Ѱ�ҽ�

		for(int i=0;i<10;i++) {//����Թ���·�ߣ�ǽΪ0��·��Ϊ+
			for(int j=0;j<10;j++) {
				if(maze[i][j]==0)
					System.out.print("0");
				if(maze[i][j]==1)
					System.out.print(" ");
				if(maze[i][j]==2)
					System.out.print("+");	
			}
			System.out.println();
		}
		
	}

}
