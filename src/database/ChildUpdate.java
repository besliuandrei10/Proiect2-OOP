package database;

import enums.Category;
import enums.ElvesType;

import java.util.LinkedList;

public class ChildUpdate {

    public ChildUpdate() {

    }

    private Long id;
    private Double niceScore;
    private LinkedList<Category> newPreferences = new LinkedList<>();
    private ElvesType elf;


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
    public LinkedList<Category> getNewPreferences() {
        return newPreferences;
    }

    /**
     * @param giftCat
     */
    public void addNewPreferences(final Category giftCat) {
        this.newPreferences.add(giftCat);
    }

}
