package database;

import enums.Category;
import enums.Cities;
import enums.ElvesType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

public class Child {

    public Child() { }

    // data gathered from parser
    private Long id;
    private Long age;
    private String lastName;
    private String firstName;
    private Cities city;
    private Double niceScore;
    private Double niceScoreBonus;
    private LinkedList<Category> giftsPreferences = new LinkedList<>();
    private ElvesType elf;


    // data for simulation needs
    private Double averageScore;
    private Double allocatedBudget;
    private ArrayList<Double> niceHistory = new ArrayList<>();
    private ArrayList<Gift> receivedGifts = new ArrayList<>();

    /**
     *
     * @return
     */
    public ElvesType getElf() {
        return elf;
    }

    /**
     *
     * @param elf
     */
    public void setElf(ElvesType elf) {
        this.elf = elf;
    }

    /**
     *
     * @return
     */
    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     *
     * @param niceScoreBonus
     */
    public void setNiceScoreBonus(Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    /**
     * @return
     */
    public Double getAllocatedBudget() {
        return allocatedBudget;
    }

    /**
     * @param allocatedBudget
     */
    public void setAllocatedBudget(final Double allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    /**
     * @return
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * @param averageScore
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * @return
     */
    public ArrayList<Double> getNiceHistory() {
        return niceHistory;
    }

    /**
     * @param score
     */
    public void addToNiceHistory(final Double score) {
        this.niceHistory.add(score);
    }

    /**
     * @return
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * @param receivedGifts
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * @param gift
     */
    public void addToReceivedGifts(final Gift gift) {
        this.receivedGifts.add(gift);
    }

    /**
     * @param giftsPreferences
     */
    public void setGiftsPreferences(final LinkedList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * @return
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * @param niceScore
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Long getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(final Long age) {
        this.age = age;
    }

    /**
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public Cities getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(final Cities city) {
        this.city = city;
    }

    /**
     * @return
     */
    public LinkedList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * @param category
     */
    public void addGiftsPreferences(final Category category) {
        this.giftsPreferences.add(category);
    }

    /**
     * @return
     */
    public JSONObject toJSONObject() {
        JSONObject output = new JSONObject();

        output.put("id", this.id);
        output.put("lastName", this.lastName);
        output.put("firstName", this.firstName);
        output.put("city", this.city.getValue());
        output.put("age", this.age);

        JSONArray giftPreferences = new JSONArray();
        for (Category giftPref : this.getGiftsPreferences()) {
            giftPreferences.add(giftPref.getValue());
        }
        output.put("giftsPreferences", giftPreferences);
        output.put("averageScore", this.averageScore);

        JSONArray niceScoreHistory = new JSONArray();
        for (Double score : this.getNiceHistory()) {
            niceScoreHistory.add(score);
        }
        output.put("niceScoreHistory", niceScoreHistory);
        output.put("assignedBudget", allocatedBudget);

        JSONArray jsonReceivedGifts = new JSONArray();
        for (Gift gift : this.getReceivedGifts()) {
            jsonReceivedGifts.add(gift.toJSONObject());
        }
        output.put("receivedGifts", jsonReceivedGifts);

        return output;
    }

    /**
     *
     */
    public void clearFieldsForNextYear() {
        this.averageScore = 0.0;
        this.receivedGifts.clear();
        this.allocatedBudget = 0.0;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Child{"
                +
                "id=" + id
                +
                ", age=" + age
                +
                ", lastName='" + lastName + '\''
                +
                ", firstName='" + firstName + '\''
                +
                ", city=" + city
                +
                ", niceScore=" + niceScore
                +
                ", giftsPreferences=" + giftsPreferences
                +
                '}';
    }
}
