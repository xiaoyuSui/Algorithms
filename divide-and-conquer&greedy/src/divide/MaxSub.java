package divide;

import java.util.spi.LocaleNameProvider;

public class MaxSub {
	int  l=0,r=0;
public int MaxSubSum(int[] a,int left,int right) {
	int sum=0;
	int center=0;
	
	if(left==right) {//判断序列长度，如果是一不需要继续递归，直接求解
		if(a[left]>0)//如果这个数大于0返回它的值，否则返回0
			sum=a[left];
		else sum=0;
	}
	else {
		center=(left+right)/2;//中间位置为左加右下取整
		int leftsum=MaxSubSum(a, left, center);//递归求解center左边
		int rightsum=MaxSubSum(a, center+1, right);//递归求解center右边
		int left_s=center,right_s=center+1;//从中间向两边求的位置
		int s1=0,lefts=0;//从中心向左求最大
		for(int i=center;i>=left;i--) {
			lefts+=a[i];
			if(lefts>s1) {
				s1=lefts;
				left_s=i;
			}
		}
		int s2=0,rights=0;//从中心向右求最大
		for(int i=center+1;i<=right;i++) {
			rights+=a[i];
			if(rights>s2) {
				s2=rights;
				right_s=i;
			}
		}
		sum=s1+s2;//跨过中心的最大等于向左和向右求得的和
		if(sum<leftsum) {//对比跨中心最大和与左右两边的最大和
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
	System.out.print("数列为：");
	for(int i=0;i<=right;i++) {
		System.out.print(a[i]+" ");
	}
	System.out.println(" ");
	System.out.print("最大子段和为"+sum);
}



}
