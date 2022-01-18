package simulation.Strategies;

import database.Child;
import database.Database;

import java.util.Comparator;

public class ById implements GiftStrategy {

    static class IDComparator implements Comparator<Child> {

        @Override
        public int compare(final Child o1, final Child o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    /**
     *
     */
    @Override
    public void executeStrategy() {

        Database.getInstance().getChildList().sort(new IDComparator());

        GiftDistributor giftAssigner = new GiftDistributor();
        giftAssigner.distributeGifts(Database.getInstance().getChildList());
    }
}
