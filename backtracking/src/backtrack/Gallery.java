package backtrack;

import java.util.Scanner;

public class Gallery {
	/*static final int MAX = 1000;
	static int d[][] = { {0,0,0}, {0,0,0}, {0,0,-1}, {0,-1,0}, {0,0,1}, {0,1,0} };
	static int [][]x=new int[MAX][MAX];
	static int [][]y=new int[MAX][MAX]; 
	static int [][]bestx=new int[MAX][MAX];   //x�������õ�ǰ������y������ʾ��������bestx�������ս��
	static int n, m, best, k = 0, t = 0;   //��ǰ�����õľ�����Ϊk���ܼ��ӵĳ�������Ϊt����ǰ���پ�����Ϊbest
	static int t1, t2, more;               //�ж��½��֦����������
	boolean p;

	/**
	 * �����������й����⣨���ݷ���
	 *
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("�����ó��й�����:");
		System.out.print("m: ");
		Scanner sc1=new Scanner(System.in);
		m=Integer.parseInt(sc1.next());
		System.out.print("n: ");
		sc1=new Scanner(System.in);
		n=Integer.parseInt(sc1.next());
	    compute(); //����
	    System.out.println("������Ҫ"+best+"��������");
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= m; j++) 
	        	System.out.print( bestx[i][j]+" ");
	        System.out.println();
	    }

	}
	static void change(int i, int j) {    //��(i, j)������һ�����������ı�����Χ�ܼ�����
	    x[i][j] = 1;
	    k++;
	    for (int r = 1; r <= 5; r++) {    //���Լ������������������ط������ܿ�
	        int p = i + d[r][1];
	        int q = j + d[r][2];
	        y[p][q]++;
	        if (y[p][q] == 1)
	            t++;
	    }
	}
	static void restore(int i, int j) {    //������(i, j)�����õľ��������ı�����Χ�ܼ�����
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
	static void search(int i, int j) {   //��������
	    do {                             //���ϵ��£�������������û����ص�λ��
	        j++;
	        if (j > m) {
	            i++;
	            j = 1;
	        }
	    } while (!((y[i][j] == 0) || (i > n)));
	    if (i > n) {
	        if (k < best) {            //ˢ�¾���ֵ
	            best = k;
	            for (int p = 1; p <= n; p++)
	                for (int q = 1; q <= m; q++)
	                    bestx[p][q] = x[p][q];
	            return;
	        }
	    }
	    if (k + (t1 - t)/5 >= best)    return;            //�������½� = �������õ����پ����� + ���еľ�����
	    if ((i < n - 1) && (k + (t2 - t)/5 >= best))    return;   //��������ž�������Ļ����ͼ�ȥ��һ��֦
	    if (i < n) {                //���p
	        change(i + 1, j);
	        search(i, j);            //�ݹ�������һ����
	        restore(i + 1,j);        //�ָ�
	    }
	    if (y[i][j + 1] == 0) {        //���q
	        change(i, j);
	        search(i, j);
	        restore(i, j);
	    }
	    if ((j < m) && ((y[i][j + 1] == 0) || (y[i][j + 2] == 0))) {    //���r
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
	    for (int i = 0; i <= m + 1; i++) {    //�������������һȦ�����ڴ���߽����
	        y[0][i] = 1;
	        y[n + 1][i] = 1;
	    }
	    for (int i = 0; i <= n + 1; i++) {
	        y[i][0] = 1;
	        y[i][m + 1] = 1;
	    }
	    search(1, 0);
	}*/
	
	
	static int[][] guard=new int[10][10];      	//��������
	static int[][] spy=new int[10][10]; 		//���ȱ��ල���
	static int[][] result=new int[10][10]; 	 //�������ս��
	static int row, col, best , k=0;

	
	public static void main(String[] args) {
		
		System.out.println("�����������������Ϊ���ش�С:");
		System.out.print("����:");
		Scanner sc1 = new Scanner(System.in);
		row = Integer.parseInt(sc1.next());
		System.out.print("����:");
		sc1 = new Scanner(System.in);
		col = Integer.parseInt(sc1.next());
		compute(); //����
		System.out.println("������Ҫ"+best+"��������");
		for(int i = 1;i <= row; i++) {
			for(int j = 1;j <= col;j++) {
			   System.out.print(result[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	
	/**
	 * �������������һȦ�����ڴ���߽����
	 */
	public static void compute() {
		best = 100;
		for(int i = 0;i <= col+1;i++) {
			spy[0][i] = 1;//��߽�
			spy[row+1][i] = 1;//�ұ߽�
		}
		for(int i = 0;i <= col+1;i++) {
			spy[i][0] = 1;//�ϱ߽�
			spy[i][col+1] = 1;//�±߽�
		}
		search(1,0);
	}
	
	//�ڣ�i��j����Ӿ������ı��ܱ���������״̬
	public static void change(int i,int j) {
		guard[i][j] = 1;
		k++;
		spy[i][j+1]++;
		spy[i+1][j]++;
		spy[i][j]++;
		spy[i][j-1]++;
		spy[i-1][j]++;
	}
	
	//ȥ����i,j�����ľ������ı��ܱ���������״̬
	public static void restore(int i,int j) {
		guard[i][j] = 0;
		k--;
		spy[i][j+1]--;
		spy[i+1][j]--;
		spy[i][j]--;
		spy[i][j-1]--;
		spy[i-1][j]--;
	}
	
	//ÿһ���д����һ��ݲ���
	public static void search(int i,int j) {
		do {
			j++;//����һ���е���һ��
			if(j > col) {//һ���ҵ�ͷ����
				i++;
				j= 1;
			}
		}while(spy[i][j]!=0&&i<=row);
		
		//һ�ν���������õ��Ľ���ȼ�¼���źã��û�
		if(i > row) {
			if(k < best) {
				best = k;
				for(int p = 1;p <= row;p++) 
					for(int q = 1;q <= col;q++) 
						result[p][q] = guard[p][q];
					return;
				}
			}
		
		if(i < row) {           //���û���ױ���
			change(i+1,j);		//�����·����ž���
			search(i,j);      	//�ݹ�������һ����
			restore(i+1, j);  	//�ָ�
		}
		
		if((spy[i][j+1] == 0)) {//����ұ�û�б����
			change(i,j);		//��ij���þ���
			search(i,j);		//�ݹ�������һ���ڵ�
			restore(i, j);		//�ָ�
		}
		
		if(((spy[i][j+1] == 0) && (spy[i][j+2] == 0))) {//����ұ������㶼û�б����
			change(i,j+1);								//���Ҳ�ĵ㲼�þ���
			search(i,j);      							//������һ����
			restore(i, j+1);							//�ָ�
		}
	}


}
