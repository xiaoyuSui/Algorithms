package backtrack;

public class BackPack {
	private static int[] p;//物品的价值数组
	private static int[] w;//物品的重量数组
	private static int max;//最大可以拿的重量
	private static int count;//物品的个数

	private static int cWeight;//当前的重量
	private static int cPrice;//当前的价值
	static int bPrice;//目前最优装载的价值
	private static int r;//剩余物品的价值

	private static int[] cChoice;//存放当前解
	private static int[] bChoice;//存放最终解

	public static int Load(int[] weight,int[] price, int m) {
	    count = weight.length;
	    w = weight;
	    p = price;
	    max = m;
	    cWeight = 0;
	    bPrice = 0;
	    cChoice = new int[count];
	    bChoice = new int [count];

	    //初始化r，即剩余最大价格
	    for(int i = 0;i<count;i++) {
	        r += p[i];
	    }

	    //调用回溯法计算
	    BackTrack(0);
	    return bPrice;   
	}

	public static void BackTrack(int t) {
    if(t==count) {//到达叶结点
	        if(cPrice>bPrice) {//如果当前结果比记录最优结果好，把最优解换成当前解
	            for(int i = 0;i<count;i++) {
	                bChoice[i] = cChoice[i];
	            }
	            bPrice = cPrice;
	        }
	        return;
	    }

	    r -= p[t];//否则访问节点，从剩余价值中减去当前价值
	    if(cWeight + w[t] <= max) {//搜索左子树，放进背包
	        cChoice[t] = 1;
	        cPrice += p[t];
	        cWeight += w[t];
	        BackTrack(t+1);
	        cPrice -= p[t];//回溯，恢复现场
	        cWeight -= w[t];

	    }

	    if(cPrice + r >bPrice) {//剪枝操作
	        cChoice[t] = 0;//搜索右子树
	        BackTrack(t+1);
	    }
	    r += p[t];//恢复现场
	}

	public static void main(String[] args) {
	    //测试
	    int[] w1 = {7,3,4,5};
	    int[] p1 = {42,12,40,25};
	    int c1 = 10;
	    Load(w1,p1,c1);
	    System.out.println("背包中最大价值为：" + bPrice);
	    System.out.print("选择方法为：");
	    for(int i=0;i<count;i++) {
	        System.out.print(bChoice[i] + " ");
	    }           
	}
}
