package id.metamorph.fabis.algorithm;

class WeightedNormalisedRatings {

    private double idealSolution;
    private double antiIdealSolution;

    /**
     * Creates a new WeightedNormalisedRatings object compound by two objects:
     * <p>
     * 1- IdealSolution: the criteria's ideal solution.
     * 2- AntiIdealSolution: the criteria's anti-ideal solution.
     */
    WeightedNormalisedRatings(double idealSolution, double antiIdealSolution) {

        this.idealSolution = idealSolution;
        this.antiIdealSolution = antiIdealSolution;
    }

    /**
     * @param idealSolution the criteria's ideal solution to set.
     */
    void setIdealSolution(double idealSolution) {
        this.idealSolution = idealSolution;
    }

    /**
     * @param antiIdealSolution the criteria's anti-ideal solution to set.
     */
    void setAntiIdealSolution(double antiIdealSolution) {
        this.antiIdealSolution = antiIdealSolution;
    }

    /**
     * @return the criteria's ideal solution.
     */
    double getIdealSolution() {
        return idealSolution;
    }

    /**
     * @return the criteria's anti-ideal solution.
     */
    double getAntiIdealSolution() {
        return antiIdealSolution;
    }
}