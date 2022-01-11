package simulation;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import database.AnnualChange;
import database.Child;
import database.ChildUpdate;
import database.Database;
import database.Gift;
import enums.Category;
import io.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Simulation {

    private JSONArray annualChanges;

    public Simulation() {

    }

    /**
     * @param file
     * @throws IOException
     */
    public void writeReport(final FileWriter file) throws IOException {
        JSONObject output = new JSONObject();
        output.put("annualChildren", this.annualChanges);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(output);

        file.write(json);
        file.close();
    }

    private void initializeNiceScoreHistory() {
        for (Child child : Database.getInstance().getChildList()) {
            child.addToNiceHistory(child.getNiceScore());
        }
    }

    /**
     * Prepares database for next year
     */
    private void nextYear(final int year) {
        for (Child child : Database.getInstance().getChildList()) {
            child.setAge(child.getAge() + 1);
            child.clearFieldsForNextYear();
        }

        AnnualChange changes = Database.getInstance().getChanges().get(year);
        Database.getInstance().setSantaBudget(changes.getNewSantaBudget());

        for (Child child : changes.getNewChildren()) {
            Database.getInstance().addToChildList(child);
            child.addToNiceHistory(child.getNiceScore());
        }

        for (Gift gift : changes.getNewGifts()) {
            Database.getInstance().addToGiftsList(gift);
        }

        for (ChildUpdate update : changes.getChildrenUpdates()) {

            Child child = Database.getInstance().childByID(update.getId());
            if (child != null) {
                if (update.getNiceScore() != null) {
                    child.addToNiceHistory(update.getNiceScore());
                }
                if (update.getNewPreferences() != null) {
                    while (!update.getNewPreferences().isEmpty()) {
                        Category selectedCat = update.getNewPreferences().getLast();
                        if (child.getGiftsPreferences().contains(selectedCat)) {
                            child.getGiftsPreferences().remove(selectedCat);
                        }
                        child.getGiftsPreferences().addFirst(selectedCat);
                        update.getNewPreferences().removeLast();
                    }
                }
            }
        }
    }

    /**
     * Distributes gifts according to preferences
     */
    private void distributeGifts() {
        for (Child child : Database.getInstance().getChildList()) {
            Double remainingBudget = child.getAllocatedBudget();

            for (Category category : child.getGiftsPreferences()) {
                Gift selectedGift = null;
                Double selectedGiftPrice = Double.MAX_VALUE;

                for (Gift gift : Database.getInstance().getGiftList()) {
                    if (gift.getCategory().equals(category)) {
                        if (selectedGiftPrice > gift.getPrice()) {
                            selectedGift = gift;
                            selectedGiftPrice = gift.getPrice();
                        }
                    }
                }
                if (remainingBudget - selectedGiftPrice > 0) {
                    child.addToReceivedGifts(selectedGift);
                    remainingBudget -= selectedGiftPrice;
                }
            }
        }
    }

    /**
     * Allocates budget to each child
     */
    private void allocateBudget() {
        Double averageScoresSum = 0.0;
        for (Child child : Database.getInstance().getChildList()) {
            averageScoresSum += child.getAverageScore();
        }

        Double budgetUnit = Database.getInstance().getSantaBudget() / averageScoresSum;
        for (Child child : Database.getInstance().getChildList()) {
            child.setAllocatedBudget(budgetUnit * child.getAverageScore());
        }
    }

    /**
     * Simulates round
     */
    private void simulateRound() {

        ArrayList<ChildAgeCategory> categorizedChildren = this.sortChildren();
        for (ChildAgeCategory category : categorizedChildren) {
            Double score = category.calculateAverageScore();

            category.getChildRef().setAverageScore(score);
        }
        this.allocateBudget();
        this.distributeGifts();

    }

    /**
     * @return
     */
    private ArrayList<ChildAgeCategory> sortChildren() {
        ArrayList<ChildAgeCategory> categorizedChildren = new ArrayList<>();
        LinkedList<Child> copy = new LinkedList<>(Database.getInstance().getChildList());

        for (Child child : copy) {
            if (child.getAge() < Constants.BABY_AGE) {
                categorizedChildren.add(new Baby(child));
            }
            if (child.getAge() >= Constants.BABY_AGE
                    && child.getAge() < Constants.TEEN_AGE) {
                categorizedChildren.add(new Kid(child));
            }
            if (child.getAge() >= Constants.TEEN_AGE
                    && child.getAge() <= Constants.YOUNG_ADULT_AGE) {
                categorizedChildren.add(new Teen(child));
            }
            if (child.getAge() > Constants.YOUNG_ADULT_AGE) {
                Database.getInstance().removeFromChildList(child);
            }
        }
        copy.clear();
        return categorizedChildren;
    }

    /**
     *
     */
    public void simulateYears() {
        Long numberOfYears = Database.getInstance().getNumberOfYears();
        JSONArray listOfAnnualChanges = new JSONArray();

        this.initializeNiceScoreHistory();
        simulateRound();
        JSONObject children = Writer.writeAllChildren();
        listOfAnnualChanges.add(children);

        for (int i = 0; i < numberOfYears; i++) {
            this.nextYear(i);
            simulateRound();
            children = Writer.writeAllChildren();
            listOfAnnualChanges.add(children);
        }
        annualChanges = listOfAnnualChanges;
    }
}
