package backtrack;

public class Color {
	static int nodeNum;	//�ڵ����
	//static int count;	//��ǰȾɫ������
	static int type;	//��¼��С��ɫ��
	static int line[][];//�ڽӾ���
	static int result[];//Ⱦɫ���
	static int color[];//Ⱦɫ���̼�¼
	
	public static boolean OK(int k) {//�жϽڵ�k��ǰȾɫ�����Ƿ����
		for(int i=0;i<nodeNum;i++)
			if(line[k][i]==1&&(color[k]==color[i]))
				return false;
		return true;	
	}
	public static void backTrack(int t) {//����
		if (t >nodeNum){	//һ��Ⱦɫ�������1
			int a=getType();//��ȡ����Ⱦɫ��
			if(a<type) 	//�������Ⱦɫ�����٣��û���¼
				type=a;
	    }
	    else{
	        for (int i = 1; i <=type; i++){	//��ɫ������
	            color[t - 1] = i;			//�ڵ�t��ɫΪi
	            if (OK(t-1)) {				//����ɫ��ʽ����
	                backTrack(t + 1); 		//�鿴��һ���ڵ�
	                if(result[t-1]!=i)
	                	result[t-1]=i;  
	            }
	            else{
	                color[ t - 1] = 0;//�����У��ص�ѭ��ȥ������ɫ��
	            }
	        }
	    }
	}
	public static int getType() {//��ȡ��ǰȾɫ������Ⱦɫ��
		int type=color[0];
		for(int i=0;i<nodeNum;i++) {
			if(color[i]>type)
				type=color[i];
		}
		return type;
	}
	
	public static void Coloring(int n,int a[][]){
		nodeNum=n;
		type=n;
		line=a;
		result=new int[n];
		color=new int[n];
		backTrack(1);

	}
	public static void main(String[] args) {
	int a[][]= {{0,1,1,0,0},
				{1,0,1,1,1},
				{1,1,0,0,1},
				{0,1,0,0,1},
				{0,1,1,1,0}};
	Coloring(5, a);
	System.out.println("����Ϳɫ����Ϊ��"+type);
	System.out.print("Ϳɫ����Ϊ��");
	for(int i=0;i<5;i++) 
		System.out.print(result[i]+"-");
	
	}
	

}
