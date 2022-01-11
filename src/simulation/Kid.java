package simulation;

import common.Constants;
import database.Child;

import java.util.ArrayList;

public class Kid implements ChildAgeCategory {

    private Child childRef;

    /**
     * @return
     */
    public Child getChildRef() {
        return childRef;
    }

    public Kid(final Child child) {
        childRef = child;
    }

    /**
     * @return
     */
    @Override
    public Double calculateAverageScore() {
        ArrayList<Double> niceHistory = childRef.getNiceHistory();
        Double output = 0.0;
        Integer counter = 0;

        if (niceHistory.isEmpty()) {
            return childRef.getNiceScore();
        }

        for (Double niceScore : niceHistory) {
            output += niceScore;
            counter++;
        }
        if (childRef.getNiceScoreBonus() != 0) {
            Double score = (Double) (output / counter);
            score += (score * childRef.getNiceScoreBonus()) / 100;

            if (score > Constants.BABY_NICESCORE)
                return Constants.BABY_NICESCORE;
            return score;
        } else {
            return (Double) (output / counter);
        }
    }
}
