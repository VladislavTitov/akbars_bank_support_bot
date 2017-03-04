package com.dreamproduction.neural;

import org.neuroph.core.NeuralNetwork;

public class NeuralRunner {

    public static double[] startNN(double[] input) {
        NeuralNetwork neuralNetwork=NeuralNetwork.load("testnn.nnet");
        neuralNetwork.setInput(input);
        neuralNetwork.calculate();

        double[] output = neuralNetwork.getOutput();
        return output;
    }
}
