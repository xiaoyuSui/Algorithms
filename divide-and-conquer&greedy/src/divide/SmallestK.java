package divide;

public class SmallestK {
	int quickOnce(int[] theArray, int left, int right)       // 快排的单次划分过程
	{
	    int value = theArray[left];//以最左的值为中间值，排序结果它左边的都小于它，右边的都大于它
	    int l = left, r = right;
	    while (l < r)//还没有排到中间
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
	
	int quickFind(int[] theArray, int left, int right, int k)   //参数均为下标
	{
	    int index = quickOnce(theArray, left, right);   // 执行一次划分操作
	    if (index < k)          // |A1|+|A2|<k 情况，目标旨在k右边
	    {
	        return quickFind(theArray, index+1, right, k);
	    }
	    else if (index > k)     // |A1|>=k情况，目标在k左边
	    {
	        return quickFind(theArray, left, index, k);
	    }
	    else                    // index正好是分界时，k即为目标
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
		System.out.print("第"+k+"小元素为："+p);
	}
}
