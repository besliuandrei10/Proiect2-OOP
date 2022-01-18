package simulation.Strategies;

import database.Child;
import database.Database;
import database.Gift;
import enums.Category;

import java.util.LinkedList;

public class GiftDistributor {

    public GiftDistributor() { }

    /**
     *
     * @param childList
     */
    void distributeGifts(final LinkedList<Child> childList) {
        for (Child child : childList) {
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
