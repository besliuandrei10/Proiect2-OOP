package simulation.Strategies;

import database.Child;
import database.Database;
import database.Gift;
import enums.Category;

import java.util.Comparator;

public class byNiceScore implements GiftStrategy {

    static class niceScoreComparator implements Comparator<Child> {

        @Override
        public int compare(Child o1, Child o2) {
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
    public void distributeGifts() {
        Database.getInstance().getChildList().sort(new niceScoreComparator());

        for (Child child : Database.getInstance().getChildList()) {
            Double remainingBudget = child.getAllocatedBudget();

            for (Category category : child.getGiftsPreferences()) {
                Gift selectedGift = null;
                Double selectedGiftPrice = Double.MAX_VALUE;

                for (Gift gift : Database.getInstance().getGiftList()) {
                    if (gift.getQuantity() > 0) {
                        if (gift.getCategory().equals(category)) {
                            if (selectedGiftPrice > gift.getPrice()) {
                                selectedGift = gift;
                                selectedGiftPrice = gift.getPrice();
                            }
                        }
                    }
                }
                if (remainingBudget - selectedGiftPrice > 0 && selectedGift != null) {
                    child.addToReceivedGifts(selectedGift);
                    remainingBudget -= selectedGiftPrice;
                    selectedGift.setQuantity(selectedGift.getQuantity() - 1);
                }
            }
        }
    }
}
