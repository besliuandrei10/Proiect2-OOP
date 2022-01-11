package simulation;

import database.Child;

import java.util.ArrayList;

public class Teen implements ChildAgeCategory {

    private Child childRef;

    /**
     * @return
     */
    public Child getChildRef() {
        return childRef;
    }

    public Teen(final Child child) {
        childRef = child;
    }

    /**
     * @return
     */
    @Override
    public Double calculateAverageScore() {
        ArrayList<Double> niceHistory = childRef.getNiceHistory();
        double sum = 0.0;
        int weights = 0;

        if (niceHistory.isEmpty()) {
            return childRef.getNiceScore();
        }

        for (int i = 0; i < niceHistory.size(); i++) {
            sum += niceHistory.get(i) * (i + 1);
            weights += (i + 1);
        }
        return (Double) (sum / weights);
    }
}
