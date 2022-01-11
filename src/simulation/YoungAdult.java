package simulation;

import database.Child;

public class YoungAdult implements ChildAgeCategory {

    private Child childRef;

    /**
     * @return
     */
    public Child getChildRef() {
        return childRef;
    }

    /**
     * @param child
     */
    public YoungAdult(final Child child) {
        childRef = child;
    }

    /**
     * @return
     */
    @Override
    public Double calculateAverageScore() {
        return null;
    }
}
