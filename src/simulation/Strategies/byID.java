package simulation.Strategies;

import database.Child;
import database.Database;
import database.Gift;
import enums.Category;

import java.util.Comparator;

public class byID implements GiftStrategy {

    static class IDComparator implements Comparator<Child> {

        @Override
        public int compare(Child o1, Child o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    /**
     *
     */
    @Override
    public void distributeGifts() {

        Database.getInstance().getChildList().sort(new IDComparator());

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
