package com.dreamproduction.neural;

public class AnswerMapper {

    public static String getAnswer(double[] nnOutput){

        int index = -1;
        double max = 0;

        for (int i = 0; i < nnOutput.length; i++) {
            if (nnOutput[i] > max){
                max = nnOutput[i];
                index = i;
            }
        }

        return DictionaryList.getAnswers().get(index);
    }

}
