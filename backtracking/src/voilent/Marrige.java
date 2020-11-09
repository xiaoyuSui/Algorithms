package voilent;
import java.util.Scanner;

public class Marrige {
	public void getResult(int[][] men, int[][] women) {
        int[] result = new int[men.length];	//最终配对结果
        int[] matched = new int[women.length];  //配对情况
        for(int i = 0;i < women.length;i++) {
            matched[i] = -1;
            result[i] = -1;
        }
        int count = 0;   //统计已完成配对个数
        while(count < men.length) {
            for(int i = 0;i < men.length;i++) {
                if(result[i] != -1)   //男士i已经配对成功了！换下一男士
                    continue;
                for(int j = 0;j < men[0].length;j++) {
                    if(matched[men[i][j]] == -1) {		//如果女士j还单身
                        matched[men[i][j]] = i;       //男士i和女士j配对
                        result[i] = men[i][j];    //记录配对结果
                        break;  
                    } else {//如果女士j不是单身
                        int temp = 0, temp1 = 0;
                        for(;temp < women[0].length;temp++) {  //求出男士i在女士j心中的优先级
                            if(women[men[i][j]][temp] == i)
                                break;
                        }
                        for(;temp1 < women[0].length;temp1++) { //求出女士j的配偶在她心中的优先级
                            if(women[men[i][j]][temp1] == matched[men[i][j]])
                                break;
                        }
                        if(temp < temp1) {  //当男士i比目前已与女士配对的男士优先级要高时
                            result[matched[men[i][j]]] = -1;   //女士分手
                            matched[men[i][j]] = i;    //男士i上位
                            result[i] = men[i][j];	//记录配对结果
                            break;       //男士i完成配对，退出循环
                        }
                    }
                }
            }
            count = 0;
            for(int i = 0;i < matched.length;i++) {
                if(matched[i] != -1)
                    count++;
            }
        }
        //打印出结果
        for(int i = 0;i < result.length;i++)
            System.out.println("男士"+(i+1)+"和女士"+(result[i]+1)+"配对");
        return;
    }
    
    public static void main(String[] args) {
        Marrige test = new Marrige();
        /*int n=3;//人数
      
        int[][] men = { {0,1,2},{0,2,1},{1,2,0} };        //男士心目中对象优先级
        int[][] women = {{1,0,2},{0,1,2},{2,1,0}} ;    //女士心目中对象优先级
       */
        int n=4;
        int[][] men = { {0,1,2,3},{1,3,0,2},{0,1,2,3},{0,3,2,1}};        //男士心目中对象优先级
        int[][] women = {{0,1,2,3},{0,1,2,3},{3,1,0,2},{0,3,1,2},} ; 	 //女士心目中对象优先级
       
        test.getResult(men, women);
    }


}
