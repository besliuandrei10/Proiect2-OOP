package simulation.Children;

import common.Constants;
import database.Child;
import database.Database;

public final class ChildFactory {

    private ChildFactory() {
    }

    /**
     *
     * @param child
     * @return
     */
    public static ChildAgeCategory assignAge(final Child child) {
        if (child.getAge() < Constants.BABY_AGE) {
            return new Baby(child);
        }
        if (child.getAge() >= Constants.BABY_AGE
                && child.getAge() < Constants.TEEN_AGE) {
            return new Kid(child);
        }
        if (child.getAge() >= Constants.TEEN_AGE
                && child.getAge() <= Constants.YOUNG_ADULT_AGE) {
            return new Teen(child);
        }
        if (child.getAge() > Constants.YOUNG_ADULT_AGE) {
            Database.getInstance().removeFromChildList(child);
        }
        return null;
    }
}
