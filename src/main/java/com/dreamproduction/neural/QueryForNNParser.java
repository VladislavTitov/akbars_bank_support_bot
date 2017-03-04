package com.dreamproduction.neural;

import java.util.List;

public class QueryForNNParser {

    public static double[] parse(String query) {
        List<String> dictionary = DictionaryList.getQueryDictionary();
        int capacity = dictionary.size();

        double[] result = new double[capacity];

        for (int i = 0; i < capacity; i++) {
            if (query.contains(dictionary.get(i))) {
                result[i] = 1;
            }
        }
        return result;
    }
}
