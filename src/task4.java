public class task4 {
    public static void main(String[] args) {
        int[] array = new int[1000];
        int[] newArray = fillRandomWine(array);
        System.out.println();
        slaveTesting(newArray);
    }
    static void slaveTesting(int[] arr) {

        int[][] secondSlaveArray = new int[10][100],thirdSlaveArray = new int[10][100];
        int[] sD3Array = new int[10],eArray = new int[10];
        String[] strArray = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strArray[i] = String.valueOf(arr[i]);
        }
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 10; j++) {
                if (String.valueOf(i).startsWith(String.valueOf(j), 1)) {
                    secondSlaveArray[j][eArray[j]] = Integer.parseInt(strArray[i]);
                    eArray[j]++;
                }
                if (String.valueOf(i).endsWith(String.valueOf(j))) {
                    thirdSlaveArray[j][sD3Array[j]] = Integer.parseInt(strArray[i]);
                    sD3Array[j]++;
                }
            }
        }
        int start = 0, end =99;
        int[][] FirstSlave_Array = new int[10][],SecondSlave_Array = new int[10][],ThirdSlave_Array = new int[10][];
        for (int i = 0; i < 10; i++) {
            FirstSlave_Array[i] = findPoison(arr, i * 100, (i + 1) * 100 - 1);
            SecondSlave_Array[i] = findPoison(secondSlaveArray[i], start, end);
            ThirdSlave_Array[i] = findPoison(thirdSlaveArray[i], start, end);
        }
        int day1 = check(FirstSlave_Array,1);
        System.out.print(" in day 1\n");
        int day2 = check(SecondSlave_Array,2);
        System.out.print(" in day 2\n");
         int day3 = check(ThirdSlave_Array,3);
        System.out.println(" in day 3");

        System.out.println("Therefore poisoned bottle is: "+ day1 + day2 + day3);

    }
    private static int check(int[][] persons, int day) {
        int poisonIndex = -1;
        String poisonHint = "";

        for (int i = 0; i < persons.length; i++) {
            if (persons[i][0] == 1) {
                poisonIndex = i;
                if (day == 1) {
                    poisonHint = " which ranges from " + (i * 100) + "-" + (i * 100 + 99);
                } else if (day == 2) {
                    poisonHint = " which contains in number in form of X" + i + "X";
                } else if (day == 3) {
                    poisonHint = " which contains in number in form of XX" + i;
                }
                System.out.print("Person " + (i + 1) + " is the one that drank poison" + poisonHint);
                break;
            }
        }
        return poisonIndex;
    }
    static int[] findPoison(int[] arr, int start, int end) {
        if (start == end) {
            return new int[] {
                    arr[start], start, end
            };
        }
        int midValue = (start + end) / 2;
        int[] leftValues = findPoison(arr, start, midValue);
        int[] rightValues = findPoison(arr, midValue + 1, end);
        if (leftValues[0] >= rightValues[0]) {
            return leftValues;
        }
        return rightValues;
    }
    static int[] fillRandomWine(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = 0;
        }
        int random = (int) (Math.random() * 1000);
        System.out.println(random);
        arr[random] = 1;
        return arr;
    }
}