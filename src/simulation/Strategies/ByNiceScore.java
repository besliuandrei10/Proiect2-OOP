package simulation.Strategies;

import database.Child;
import database.Database;

import java.util.Comparator;

public class ByNiceScore implements GiftStrategy {

    static class NiceScoreComparator implements Comparator<Child> {

        @Override
        public int compare(final Child o1, final Child o2) {
            if (o1.getAverageScore().equals(o2.getAverageScore())) {
                return o1.getId().compareTo(o2.getId());
            }
            return (-1) * o1.getAverageScore().compareTo(o2.getAverageScore());
        }
    }

    /**
     *
     */
    @Override
    public void executeStrategy() {
        Database.getInstance().getChildList().sort(new NiceScoreComparator());

        GiftDistributor giftAssigner = new GiftDistributor();
        giftAssigner.distributeGifts(Database.getInstance().getChildList());
    }
}
