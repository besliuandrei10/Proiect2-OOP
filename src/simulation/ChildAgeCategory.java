package simulation;

import database.Child;

public interface ChildAgeCategory {
    /**
     * @return
     */
    Double calculateAverageScore();

    /**
     * @return
     */
    Child getChildRef();
}
