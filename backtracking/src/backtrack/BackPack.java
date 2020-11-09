package backtrack;

public class BackPack {
	private static int[] p;//��Ʒ�ļ�ֵ����
	private static int[] w;//��Ʒ����������
	private static int max;//�������õ�����
	private static int count;//��Ʒ�ĸ���

	private static int cWeight;//��ǰ������
	private static int cPrice;//��ǰ�ļ�ֵ
	static int bPrice;//Ŀǰ����װ�صļ�ֵ
	private static int r;//ʣ����Ʒ�ļ�ֵ

	private static int[] cChoice;//��ŵ�ǰ��
	private static int[] bChoice;//������ս�

	public static int Load(int[] weight,int[] price, int m) {
	    count = weight.length;
	    w = weight;
	    p = price;
	    max = m;
	    cWeight = 0;
	    bPrice = 0;
	    cChoice = new int[count];
	    bChoice = new int [count];

	    //��ʼ��r����ʣ�����۸�
	    for(int i = 0;i<count;i++) {
	        r += p[i];
	    }

	    //���û��ݷ�����
	    BackTrack(0);
	    return bPrice;   
	}

	public static void BackTrack(int t) {
    if(t==count) {//����Ҷ���
	        if(cPrice>bPrice) {//�����ǰ����ȼ�¼���Ž���ã������Ž⻻�ɵ�ǰ��
	            for(int i = 0;i<count;i++) {
	                bChoice[i] = cChoice[i];
	            }
	            bPrice = cPrice;
	        }
	        return;
	    }

	    r -= p[t];//������ʽڵ㣬��ʣ���ֵ�м�ȥ��ǰ��ֵ
	    if(cWeight + w[t] <= max) {//�������������Ž�����
	        cChoice[t] = 1;
	        cPrice += p[t];
	        cWeight += w[t];
	        BackTrack(t+1);
	        cPrice -= p[t];//���ݣ��ָ��ֳ�
	        cWeight -= w[t];

	    }

	    if(cPrice + r >bPrice) {//��֦����
	        cChoice[t] = 0;//����������
	        BackTrack(t+1);
	    }
	    r += p[t];//�ָ��ֳ�
	}

	public static void main(String[] args) {
	    //����
	    int[] w1 = {7,3,4,5};
	    int[] p1 = {42,12,40,25};
	    int c1 = 10;
	    Load(w1,p1,c1);
	    System.out.println("����������ֵΪ��" + bPrice);
	    System.out.print("ѡ�񷽷�Ϊ��");
	    for(int i=0;i<count;i++) {
	        System.out.print(bChoice[i] + " ");
	    }           
	}
}
