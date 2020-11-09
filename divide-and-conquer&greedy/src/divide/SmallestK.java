package divide;

public class SmallestK {
	int quickOnce(int[] theArray, int left, int right)       // ���ŵĵ��λ��ֹ���
	{
	    int value = theArray[left];//�������ֵΪ�м�ֵ������������ߵĶ�С�������ұߵĶ�������
	    int l = left, r = right;
	    while (l < r)//��û���ŵ��м�
	    {
	        while (l<r && theArray[r]>value)//
	            r--;
	        if (l < r)
	        {
	            theArray[l] = theArray[r];
	            l++;
	        }
	        while (l < r && theArray[l] < value)
	            l++;
	        if (l < r)
	        {
	            theArray[r] = theArray[l];
	            r--;
	        }
	    }
	    theArray[l] = value;
	    return l;
	}
	
	int quickFind(int[] theArray, int left, int right, int k)   //������Ϊ�±�
	{
	    int index = quickOnce(theArray, left, right);   // ִ��һ�λ��ֲ���
	    if (index < k)          // |A1|+|A2|<k �����Ŀ��ּ��k�ұ�
	    {
	        return quickFind(theArray, index+1, right, k);
	    }
	    else if (index > k)     // |A1|>=k�����Ŀ����k���
	    {
	        return quickFind(theArray, left, index, k);
	    }
	    else                    // index�����Ƿֽ�ʱ��k��ΪĿ��
	    {
	        return theArray[index];
	    }

	}
	public static void main(String args[]) {
		SmallestK smallestK=new SmallestK();
		int[] a= {1,7,2,4,6,3,5};
		int k=4;
		int n=a.length;
		int p=smallestK.quickFind(a,0,n-1,k-1);
		System.out.print("��"+k+"СԪ��Ϊ��"+p);
	}
}
