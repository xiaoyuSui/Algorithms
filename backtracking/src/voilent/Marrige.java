package voilent;
import java.util.Scanner;

public class Marrige {
	public void getResult(int[][] men, int[][] women) {
        int[] result = new int[men.length];	//������Խ��
        int[] matched = new int[women.length];  //������
        for(int i = 0;i < women.length;i++) {
            matched[i] = -1;
            result[i] = -1;
        }
        int count = 0;   //ͳ���������Ը���
        while(count < men.length) {
            for(int i = 0;i < men.length;i++) {
                if(result[i] != -1)   //��ʿi�Ѿ���Գɹ��ˣ�����һ��ʿ
                    continue;
                for(int j = 0;j < men[0].length;j++) {
                    if(matched[men[i][j]] == -1) {		//���Ůʿj������
                        matched[men[i][j]] = i;       //��ʿi��Ůʿj���
                        result[i] = men[i][j];    //��¼��Խ��
                        break;  
                    } else {//���Ůʿj���ǵ���
                        int temp = 0, temp1 = 0;
                        for(;temp < women[0].length;temp++) {  //�����ʿi��Ůʿj���е����ȼ�
                            if(women[men[i][j]][temp] == i)
                                break;
                        }
                        for(;temp1 < women[0].length;temp1++) { //���Ůʿj����ż�������е����ȼ�
                            if(women[men[i][j]][temp1] == matched[men[i][j]])
                                break;
                        }
                        if(temp < temp1) {  //����ʿi��Ŀǰ����Ůʿ��Ե���ʿ���ȼ�Ҫ��ʱ
                            result[matched[men[i][j]]] = -1;   //Ůʿ����
                            matched[men[i][j]] = i;    //��ʿi��λ
                            result[i] = men[i][j];	//��¼��Խ��
                            break;       //��ʿi�����ԣ��˳�ѭ��
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
        //��ӡ�����
        for(int i = 0;i < result.length;i++)
            System.out.println("��ʿ"+(i+1)+"��Ůʿ"+(result[i]+1)+"���");
        return;
    }
    
    public static void main(String[] args) {
        Marrige test = new Marrige();
        /*int n=3;//����
      
        int[][] men = { {0,1,2},{0,2,1},{1,2,0} };        //��ʿ��Ŀ�ж������ȼ�
        int[][] women = {{1,0,2},{0,1,2},{2,1,0}} ;    //Ůʿ��Ŀ�ж������ȼ�
       */
        int n=4;
        int[][] men = { {0,1,2,3},{1,3,0,2},{0,1,2,3},{0,3,2,1}};        //��ʿ��Ŀ�ж������ȼ�
        int[][] women = {{0,1,2,3},{0,1,2,3},{3,1,0,2},{0,3,1,2},} ; 	 //Ůʿ��Ŀ�ж������ȼ�
       
        test.getResult(men, women);
    }


}
