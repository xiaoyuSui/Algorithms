package branchbound;
import java.io.UTFDataFormatException;
import java.util.PriorityQueue;
import java.util.Queue;
import branchbound.TSP.Node;

public class backpack {
	
	static public class Node implements Comparable<Node> {
		public double ub;//upbound
		public int no;//�������������е�λ��
		public int[] used=new int[4];//ʹ�ù���
		
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
	static int[] weight= {4,7,5,3} ;	//��Ʒ����
	static int[] value= {40,42,25,12};	//��Ʒ��ֵ
	static double[] ratio;	///��Ʒ��ֵ��������ֵ
	static int[] solution;	//
	static int max=10;		//�����������
	static int cweight;		//��ǰ����
	static double cvalue;	//��ǰ��¼��ü�ֵ
	static int n;			//��ѡ��Ʒ����
	static double upbound=0;
	static Queue<Node> pq=new PriorityQueue<Node>();
	public static void sort() {//�����ݰ��ձ�ֵ�Ӵ�С����
		n=weight.length;
		ratio=new double[n];
		solution=new int[n];
		for(int i=0;i<n;i++) {	//��ʼ����ֵ������ֵ�����˳������
			ratio[i]=value[i]/weight[i];
			
		}
		for(int i=0;i<n;i++){//ð�����򣬸ı�˳�����鲻�ı���������
            double tem = 0;
            int tempint=0;
            // �ڲ�forѭ���������ڵ�����Ԫ�ؽ��бȽ�
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
			if(node.no==4) {//�����Ҷ�ӽڵ�
				if(upbound==0||upbound>node.ub)
					upbound=node.ub;
				if(cvalue<val) {
					solution=node.used.clone();
					cvalue=val;
				}
			}                  
			else {
				ub_r=cvalue+ratio[node.no]*left;//��ѡ��ǰ��Ʒ
				if(ub_r>=upbound) {//��֦
					pq.add(new Node(ub_r,node.no+1,node.used));
				}
				if(weight[node.no-1]<left) {//��������
					left-=weight[node.no-1];
					ub_l=cvalue+value[node.no-1]+ratio[node.no]*left;//������ub
					if(ub_l>=upbound) {//��֦
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
		System.out.print("���� ��ֵ/���� �Ƿ�ѡ��\n");
		for(int i=0;i<n;i++) {
			System.out.println(weight[i]+"\t"+ratio[i]+"\t"+solution[i]);
		}
		System.out.print("���Ž�Ϊ��"+cvalue);
		
	}

}
