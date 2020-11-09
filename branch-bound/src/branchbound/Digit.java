package branchbound;

import java.util.*;
public class Digit {
	static int N = 0;
	static int[][] start = {{1,0,3},{4,2,6},{7,5,8}};//初始状态
	static int[][] end = {{1,2,3},{4,5,6},{7,8,0}};//目标状态
	// 用于保存目标状态中每个数字所在的位置
	static HashMap<Integer, int[]> map = new HashMap<>();
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };//移动方向
	// 用于保存所有出现过的状态
	static List<int[][]> marke = new ArrayList<int[][]>();
	static public class node implements Cloneable {
		// 当前结点状态中空格的位置
		int x;
		int y;
		// 估价函数g和h
		int g;
		int h;
		// 记录步数
		int step;
		// 当前状态各位置的数字
		int[][] mt = new int[N][];
		// 保存路径
		List<int[][]> path = new ArrayList<int[][]>();
		// 构造函数
		public node(int x, int y, int g, int h, int step, int[][] mt, List<int[][]> path) {
			super();
			this.x = x;
			this.y = y;
			this.g = g;
			this.h = h;
			this.step = step;
			this.mt = mt;
			this.path = path;
		}
		// 用于克隆对象，这样在扩展时，下一个状态可以保留上一个状态的路径
		public Object clone() {
			node nd = null;
			try {
				nd = (node) super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			nd.mt = new int[3][];
			for (int r = 0; r < N; r++) {
				nd.mt[r] = this.mt[r].clone();
			}
			nd.path = new ArrayList<int[][]>();
			nd.path.addAll(this.path);
			return nd;
		}
	}
 
	static Comparator<node> cmp = new Comparator<node>() {
		@Override
		public int compare(node o1, node o2) {
			// TODO Auto-generated method stub
			return (o1.g + o1.h) - (o2.g + o2.h);
		}
	};

	static boolean input_date() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		N = 3;
		// 求奇偶排列的变量
		int[] startNum = new int[N * N];
		int[] endNum = new int[N * N];
		int cnt1 = 0;
		int cnt2 = 0;
		// 输入初始状态和目标状态
		//System.out.println("请输入初始状态(0代表空白位置)：");
		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < N; j++)
				if (start[i][j] != 0)
					startNum[cnt1++] = start[i][j];
		}
		//System.out.println("请输入目标状态(0代表空白位置)：");
		for (int i = 0; i < N; i++) {
			
			// 将默认的map覆盖掉，用于计算估价函数h
			for (int j = 0; j < N; j++) {
				int[] temp = { i, j };
				map.put(end[i][j], temp);
				if (end[i][j] != 0)
					endNum[cnt2++] = end[i][j];
			}
		}
		//判断问题是否有解
		int st = 0;
		int et = 0;
		for (int i = N * N - 2; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (startNum[i] > startNum[j])
					st++;
				if (endNum[i] > endNum[j])
					et++;
			}
		}
		if (st % 2 == et % 2)
			return true;
		return false;
	}

	static int boundbranch(int[][] start) {
		// 找到空格所在的位置
		int x0 = 0, y0 = 0;
		for (x0 = 0; x0 < N; x0++) {
			boolean flag = false;
			for (y0 = 0; y0 < N; y0++) {
				if (start[x0][y0] == 0) {
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}
		// 优先队列
		Queue<node> q = new PriorityQueue<node>(cmp);
		int[][] curmt = new int[N][];
		int[][] markemt = new int[N][];
		// clone方法用于复制一个对象，在内存中开辟同样大小的空间
		for (int r = 0; r < N; r++)
			curmt[r] = start[r].clone();
		for (int r = 0; r < N; r++)
			markemt[r] = start[r].clone();
		List<int[][]> path = new ArrayList<int[][]>();
		path.add(start);// path加入初始状态
		// 创建一个结点，表示空格，估价函数初始化为0
		node cur = new node(x0, y0, 0, 0, 0, curmt, path);
		// 将出现过的所有状态都加入marke集合中
		marke.add(markemt);
		// 入队并遍历
		q.add(cur);
		while (!q.isEmpty()) {
			// 队首元素出队
			cur = (node) q.poll().clone();
			boolean tag = false;
			// 判断当前状态是不是目标状态
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cur.mt[i][j] != end[i][j]) {
						tag = true;
					}
				}
			}
			// 如果是，输出结果
			if (!tag) {
				System.out.println("共扩展了" + marke.size() + "个结点");
				for(int i=0;i<marke.size();i++) {
					
					for(int j=0;j<3;j++) {
						for(int k=0;k<3;k++)
							System.out.print(marke.get(i)[j][k]+" ");
						System.out.print("\n");
					}
					System.out.print("------------------------------\n");
				}
				return cur.step;
			}
			// 遍历四种方向上的移动
			for (int i = 0; i < 4; i++) {
				node next = (node) cur.clone();
				next.x = cur.x + dir[i][0];
				next.y = cur.y + dir[i][1];
				// 如果空格位置不合法就忽略这个状态
				if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < N) {
					// 因为上面next定义时clone了cur，所以在这里更新空格的位置
					next.mt[cur.x][cur.y] = next.mt[next.x][next.y];
					next.mt[next.x][next.y] = 0;
					boolean mark = false;
					// 判断当前状态有没有出现过
					for (int c = 0; c < marke.size(); c++) {
						int x = 0, y = 0;
						for (x = 0; x < N; x++) {
							for (y = 0; y < N; y++)
								if (marke.get(c)[x][y] != next.mt[x][y])
									break;
							if (y < N)
								break;
						}
						if (x == N && y == N)
							mark = true;
					}
					// 若出现过则忽略这个状态
					if (!mark) {
						// 更新next的属性值step和估价函数g
						next.step++;
						next.g++;
						// 将当前状态加入到结点的path中，因为程序中定义结点时，clone了上一个结点，所以在当前结点添加的状态也会clone到下一个结点中。
						next.path.add(next.mt);
						// 计算估价函数h，获取每个位置的数字，到达目标状态中对应数字的位置，所需要的步数
						int count = 0;
						for (int row = 0; row < N; row++) {
							for (int cow = 0; cow < N; cow++) {
								if (cow != 0 && next.mt[row][cow] != end[row][cow]) {
									count += Math.abs(row - map.get(next.mt[row][cow])[0])
											+ Math.abs(cow - map.get(next.mt[row][cow])[1]);
								}
							}
						}
						next.h = count;
						// 将扩展状态入队
						int[][] newmt = new int[N][];
						for (int r = 0; r < N; r++)
							newmt[r] = next.mt[r].clone();
						marke.add(newmt);
						q.add((node) next.clone());
					}
				}
			}
		}
		return 0;
	}


	public static void main(String[] args) {
		boolean flag = input_date();
		if (!flag) {
			System.out.println("问题无解！");
		} else {
			int ans = boundbranch(start);
			System.out.println("移动步数：" + Integer.toString(ans));
		}
	}
}
