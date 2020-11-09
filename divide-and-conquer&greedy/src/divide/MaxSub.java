package divide;

import java.util.spi.LocaleNameProvider;

public class MaxSub {
	int  l=0,r=0;
public int MaxSubSum(int[] a,int left,int right) {
	int sum=0;
	int center=0;
	
	if(left==right) {//�ж����г��ȣ������һ����Ҫ�����ݹ飬ֱ�����
		if(a[left]>0)//������������0��������ֵ�����򷵻�0
			sum=a[left];
		else sum=0;
	}
	else {
		center=(left+right)/2;//�м�λ��Ϊ�������ȡ��
		int leftsum=MaxSubSum(a, left, center);//�ݹ����center���
		int rightsum=MaxSubSum(a, center+1, right);//�ݹ����center�ұ�
		int left_s=center,right_s=center+1;//���м����������λ��
		int s1=0,lefts=0;//���������������
		for(int i=center;i>=left;i--) {
			lefts+=a[i];
			if(lefts>s1) {
				s1=lefts;
				left_s=i;
			}
		}
		int s2=0,rights=0;//���������������
		for(int i=center+1;i<=right;i++) {
			rights+=a[i];
			if(rights>s2) {
				s2=rights;
				right_s=i;
			}
		}
		sum=s1+s2;//������ĵ������������������õĺ�
		if(sum<leftsum) {//�Աȿ������������������ߵ�����
			sum=leftsum;
		}
		if(sum<rightsum) {
			sum=rightsum;
		}
	}
	return sum;
}

public static void main(String[] args) {
	int[]a= {-1,-1,-1,2,3,4,-4,-4,5};
	int left=0;
	int right=a.length-1;
	MaxSub testMaxSub=new MaxSub();
	int sum=testMaxSub.MaxSubSum(a, left, right);
	System.out.print("����Ϊ��");
	for(int i=0;i<=right;i++) {
		System.out.print(a[i]+" ");
	}
	System.out.println(" ");
	System.out.print("����Ӷκ�Ϊ"+sum);
}



}
