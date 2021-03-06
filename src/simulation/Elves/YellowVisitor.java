package simulation.Elves;

import database.Child;
import database.Database;
import database.Gift;
import enums.Category;
import simulation.Visitor;

public class YellowVisitor implements Visitor {

    /**
     *
     * @param child
     */
    @Override
    public void visit(final Child child) {
        if (child.getReceivedGifts().isEmpty()) {
            Category giftCat = child.getGiftsPreferences().get(0);

            Gift selectedGift = null;
            Double selectedPrice = Double.MAX_VALUE;

            for (Gift gift : Database.getInstance().getGiftList()) {
                if (gift.getCategory().equals(giftCat)) {
                    if (selectedPrice > gift.getPrice()) {
                        selectedGift = gift;
                        selectedPrice = gift.getPrice();
                    }
                }
            }
            if (selectedGift != null) {
                if (selectedGift.getQuantity() > 0) {
                    child.addToReceivedGifts(selectedGift);
                    selectedGift.setQuantity(selectedGift.getQuantity() - 1);
                }
            }
        }
    }
}
