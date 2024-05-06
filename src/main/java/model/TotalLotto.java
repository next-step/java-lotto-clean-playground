package model;

import java.util.ArrayList;
import java.util.List;

public class TotalLotto {
    public static List<List<Integer>> createResultArray () {
        List<List<Integer>> resultArray = new ArrayList<>();
        return resultArray;
    }

    public static List<List<Integer>> createAddLotto (List<Integer> singleLotto, List<List<Integer>> resultArray) {
        resultArray.add(singleLotto); // ?
        return resultArray; //void?
    }
}
