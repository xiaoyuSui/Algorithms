package branchbound;

import java.util.*;
public class Digit {
	static int N = 0;
	static int[][] start = {{1,0,3},{4,2,6},{7,5,8}};//��ʼ״̬
	static int[][] end = {{1,2,3},{4,5,6},{7,8,0}};//Ŀ��״̬
	// ���ڱ���Ŀ��״̬��ÿ���������ڵ�λ��
	static HashMap<Integer, int[]> map = new HashMap<>();
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };//�ƶ�����
	// ���ڱ������г��ֹ���״̬
	static List<int[][]> marke = new ArrayList<int[][]>();
	static public class node implements Cloneable {
		// ��ǰ���״̬�пո��λ��
		int x;
		int y;
		// ���ۺ���g��h
		int g;
		int h;
		// ��¼����
		int step;
		// ��ǰ״̬��λ�õ�����
		int[][] mt = new int[N][];
		// ����·��
		List<int[][]> path = new ArrayList<int[][]>();
		// ���캯��
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
		// ���ڿ�¡������������չʱ����һ��״̬���Ա�����һ��״̬��·��
		public Object clone() {
			node nd = null;
			try {
				nd = (node) super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO �Զ����ɵ� catch ��
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
		// ����ż���еı���
		int[] startNum = new int[N * N];
		int[] endNum = new int[N * N];
		int cnt1 = 0;
		int cnt2 = 0;
		// �����ʼ״̬��Ŀ��״̬
		//System.out.println("�������ʼ״̬(0����հ�λ��)��");
		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < N; j++)
				if (start[i][j] != 0)
					startNum[cnt1++] = start[i][j];
		}
		//System.out.println("������Ŀ��״̬(0����հ�λ��)��");
		for (int i = 0; i < N; i++) {
			
			// ��Ĭ�ϵ�map���ǵ������ڼ�����ۺ���h
			for (int j = 0; j < N; j++) {
				int[] temp = { i, j };
				map.put(end[i][j], temp);
				if (end[i][j] != 0)
					endNum[cnt2++] = end[i][j];
			}
		}
		//�ж������Ƿ��н�
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
		// �ҵ��ո����ڵ�λ��
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
		// ���ȶ���
		Queue<node> q = new PriorityQueue<node>(cmp);
		int[][] curmt = new int[N][];
		int[][] markemt = new int[N][];
		// clone�������ڸ���һ���������ڴ��п���ͬ����С�Ŀռ�
		for (int r = 0; r < N; r++)
			curmt[r] = start[r].clone();
		for (int r = 0; r < N; r++)
			markemt[r] = start[r].clone();
		List<int[][]> path = new ArrayList<int[][]>();
		path.add(start);// path�����ʼ״̬
		// ����һ����㣬��ʾ�ո񣬹��ۺ�����ʼ��Ϊ0
		node cur = new node(x0, y0, 0, 0, 0, curmt, path);
		// �����ֹ�������״̬������marke������
		marke.add(markemt);
		// ��Ӳ�����
		q.add(cur);
		while (!q.isEmpty()) {
			// ����Ԫ�س���
			cur = (node) q.poll().clone();
			boolean tag = false;
			// �жϵ�ǰ״̬�ǲ���Ŀ��״̬
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cur.mt[i][j] != end[i][j]) {
						tag = true;
					}
				}
			}
			// ����ǣ�������
			if (!tag) {
				System.out.println("����չ��" + marke.size() + "�����");
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
			// �������ַ����ϵ��ƶ�
			for (int i = 0; i < 4; i++) {
				node next = (node) cur.clone();
				next.x = cur.x + dir[i][0];
				next.y = cur.y + dir[i][1];
				// ����ո�λ�ò��Ϸ��ͺ������״̬
				if (next.x >= 0 && next.x < N && next.y >= 0 && next.y < N) {
					// ��Ϊ����next����ʱclone��cur��������������¿ո��λ��
					next.mt[cur.x][cur.y] = next.mt[next.x][next.y];
					next.mt[next.x][next.y] = 0;
					boolean mark = false;
					// �жϵ�ǰ״̬��û�г��ֹ�
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
					// �����ֹ���������״̬
					if (!mark) {
						// ����next������ֵstep�͹��ۺ���g
						next.step++;
						next.g++;
						// ����ǰ״̬���뵽����path�У���Ϊ�����ж�����ʱ��clone����һ����㣬�����ڵ�ǰ�����ӵ�״̬Ҳ��clone����һ������С�
						next.path.add(next.mt);
						// ������ۺ���h����ȡÿ��λ�õ����֣�����Ŀ��״̬�ж�Ӧ���ֵ�λ�ã�����Ҫ�Ĳ���
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
						// ����չ״̬���
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
			System.out.println("�����޽⣡");
		} else {
			int ans = boundbranch(start);
			System.out.println("�ƶ�������" + Integer.toString(ans));
		}
	}
}
