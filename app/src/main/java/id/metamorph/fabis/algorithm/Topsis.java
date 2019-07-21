package id.metamorph.fabis.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Topsis {

    double[][] criteria, criteriaNormalization, criteriaWeights, idealDistances, antiIdealDistances;
    double[] weights, alternativesIdealDistances, alternativesAntiIdealDistances;
    boolean[] costCriteria;
    WeightedNormalisedRatings weightedNormalisedRatings[];

    public Topsis(int numberOfCriteria, int numberOfAlternatives) {

        criteria = new double[numberOfAlternatives][numberOfCriteria];
        weights = new double[numberOfCriteria];
        alternativesIdealDistances = new double[numberOfCriteria];
        alternativesAntiIdealDistances = new double[numberOfCriteria];
        costCriteria = new boolean[numberOfCriteria];
        idealDistances = new double[numberOfAlternatives][numberOfCriteria];
        antiIdealDistances = new double[numberOfAlternatives][numberOfCriteria];

    }

    /**
     * Calculates the alternative's ranking using the eigenvalue method.
     *
     * @return an array with the alternatives's ranking.
     */
    public double[] TopsisMethod() {

        for (int i = 0; i < criteria.length; ++i) {
            for (int j = 0; j < criteria[i].length; ++j) {
                criteria[j][i] *= weights[i];
            }
        }

        criteriaWeights = criteria.clone();

        weightedNormalisedRatings = new WeightedNormalisedRatings[criteria.length];

        for (int i = 0; i < criteria.length; ++i) {

            double max = criteria[0][i], min = criteria[0][i];

            for(int j = 1; j < criteria[i].length; ++j) {
                if (criteria[j][i] > max) {
                    max = criteria[j][i];
                }
                if (criteria[j][i] < min) {
                    min = criteria[j][i];
                }
            }

            if (costCriteria[i]) {
                WeightedNormalisedRatings solutions = new WeightedNormalisedRatings(min, max);
                weightedNormalisedRatings[i] = solutions;
            } else {
                WeightedNormalisedRatings solutions = new WeightedNormalisedRatings(max, min);
                weightedNormalisedRatings[i] = solutions;
            }
        }

        double[] ranking = new double[criteria[0].length];

        for (int i = 0; i < criteria.length; ++i) {

            double idealSum = 0.0, antiIdealSum = 0.0;

            for (int j = 0; j < criteria[i].length; ++j) {
                idealDistances[j][i] = Math.pow((weightedNormalisedRatings[j].getIdealSolution() - criteria[i][j]), 2);
                antiIdealDistances[j][i] = Math.pow((weightedNormalisedRatings[j].getAntiIdealSolution() - criteria[i][j]), 2);
                idealSum += idealDistances[j][i];
                antiIdealSum += antiIdealDistances[j][i];
            }

            alternativesIdealDistances[i] = idealSum = Math.sqrt(idealSum);
            alternativesAntiIdealDistances[i] = antiIdealSum = Math.sqrt(antiIdealSum);

            ranking[i] = antiIdealSum / (idealSum + antiIdealSum);
        }

        return ranking;
    }

    /**
     * Normalizes the comparision matrix using distributive normalization.
     */
    public void distributiveNormalization() {

        for (int i = 0; i < criteria.length; ++i) {
            double denominator = 0.0;

            for (int j = 0; j < criteria[i].length; ++j) {
                denominator += criteria[j][i] * criteria[j][i];
            }

            denominator = Math.sqrt(denominator);

            for (int j = 0; j < criteria[i].length; ++j) {
                criteria[j][i] = criteria[j][i] / denominator;
            }
        }
        criteriaNormalization = criteria.clone();
    }

    /**
     * Normalizes the comparision matrix using ideal normalization.
     */
    void idealNormalization() {

        for (int i = 0; i < criteria.length; ++i) {

            double value = criteria[0][i];

            if (costCriteria[i]) {
                for (int j = 1; j < criteria[i].length; ++j) {
                    if (criteria[j][i] < value) {
                        value = criteria[j][i];
                    }
                }

                for (int j = 0; j < criteria[i].length; ++j) {
                    criteria[j][i] = value / criteria[j][i];
                }

            } else {
                for (int j = 1; j < criteria[i].length; ++j) {
                    if (criteria[j][i] > value) {
                        value = criteria[j][i];
                    }
                }

                for (int j = 0; j < criteria[i].length; ++j) {
                    criteria[j][i] = criteria[j][i] / value;
                }
            }
        }

        criteriaNormalization = criteria.clone();
    }
}
