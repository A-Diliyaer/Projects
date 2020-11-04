package zombie;

import java.util.Arrays;

public class attempt3 {
    public static void main(String[] args) {
        int day = 0;
        int[] inhabitants = {0,0,0,0,12,0,0,0};
        int[] arr = new int[inhabitants.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        System.out.println("Day " + day + " " + Arrays.toString(inhabitants));
        while(!Arrays.equals(arr,inhabitants)) {
            day++;
            int zeroCount = 0;
            for (int i = 0; i < inhabitants.length; i++) {
                if (inhabitants[i] == 0) {
                    zeroCount++;
                }
            }
            int count = 0;
            int[] index = new int[zeroCount];
            for (int i = 0; i < inhabitants.length; i++) {
                if (inhabitants[i] == 0) {
                    index[count] = i;
                    count++;
                }
            }
            for (int i = 0; i < index.length; i++) {
                if (i == 0) {
                   if(inhabitants[0] == 0&&inhabitants[2]==0){
                       continue;
                   }
                   else if (inhabitants[0] == 0) {
                        inhabitants[index[i] + 1] = inhabitants[index[i] + 1] / 2;
                        continue;
                    }
                }
                if (index[i] <= inhabitants.length - 3) {
                    if (inhabitants[index[i]] == inhabitants[index[i] + 2]) {
                        inhabitants[index[i] - 1] = inhabitants[index[i] - 1] / 2;
                        continue;
                    }
                }
                if (i == index.length - 1) {
                    if (inhabitants[inhabitants.length - 1] == 0) {
                        inhabitants[index[i] - 1] = inhabitants[index[i] - 1] / 2;
                        continue;
                    }
                }
                inhabitants[index[i] - 1] = inhabitants[index[i] - 1] / 2;
                inhabitants[index[i] + 1] = inhabitants[index[i] + 1] / 2;
            }
            System.out.println("Day " + day + " " + Arrays.toString(inhabitants));
        }

    }
}