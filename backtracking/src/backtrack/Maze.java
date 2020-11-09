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
			maze[cur.x][cur.y]=2;//访问栈顶
			if(maze[cur.x+1][cur.y]==1) {//向下走
				stack.add(new Point(cur.x+1, cur.y));
				Solution(x+1, y);
			}
			if(maze[cur.x][cur.y+1]==1) {//向右走
				stack.add(new Point(cur.x, cur.y+1));
				Solution(x, y+1);
			}
			if(maze[cur.x][cur.y-1]==1) {//向左走
				stack.add(new Point(cur.x, cur.y-1));
				Solution(x, y-1);
			}
			if(maze[cur.x-1][cur.y]==1) {//向上走
				stack.add(new Point(cur.x-1, cur.y));
				Solution(x-1, y);
			}
				maze[cur.x][cur.y]=1;//如果走不通回退就设置这个点没有被走
				stack.pop();//弹出栈顶，回退
				cur=(Point)stack.peek();//设置当前位置为栈顶
			
	}
	public static void main(String[] args) {
		stack.add(new Point(1,1));//将初始位置入栈
		Solution(1,1);//回溯法寻找解

		for(int i=0;i<10;i++) {//输出迷宫和路线，墙为0，路线为+
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
