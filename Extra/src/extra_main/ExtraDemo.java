package extra_main;

import java.util.ArrayList;
import java.util.Arrays;

import static extra_methods.Extra.removeDuplicates;

public class ExtraDemo {
    public static void main(String[] args) {
        ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 2, 7, 3, 4, 2, 5, 6, 2, 7, 1, 8));

        removeDuplicates(integersList);
    }

}
