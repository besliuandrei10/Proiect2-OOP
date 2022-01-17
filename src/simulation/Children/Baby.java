package simulation.Children;

import common.Constants;
import database.Child;

public class Baby implements ChildAgeCategory {

    private Child childRef;

    /**
     * @return
     */
    public Child getChildRef() {
        return childRef;
    }

    public Baby(final Child child) {
        childRef = child;
    }

    /**
     * @return
     */
    @Override
    public Double calculateAverageScore() {
        return Constants.BABY_NICESCORE;
    }
}
