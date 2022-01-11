package io;

import database.AnnualChange;
import database.Child;
import database.ChildUpdate;
import database.Database;
import database.Gift;
import enums.Category;
import enums.Cities;
import enums.CityStrategyEnum;
import enums.ElvesType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public final class Parser {

    private Parser() {

    }

    /**
     *
     * @return
     */
    private static CityStrategyEnum stringToCityStrategyEnum(String string) {
        switch (string) {
            case "id" -> {
                return CityStrategyEnum.ID;
            }
            case "niceScore" -> {
                return CityStrategyEnum.NICE_SCORE;
            }
            case "niceScoreCity" -> {
                return CityStrategyEnum.NICE_SCORE_CITY;
            }
            default -> {
            }
        }
        return null;
    }
    /**
     * @param city
     * @return
     */
    private static String cityToEnum(final String city) {
        if (city.equals("Cluj-Napoca")) {
            return "CLUJ";
        }
        return city.toUpperCase(Locale.ROOT);
    }

    /**
     * @param giftCat
     * @return
     */
    private static String giftCatToEnum(final String giftCat) {
        if (giftCat.equals("Board Games")) {
            return "BOARD_GAMES";
        }
        return giftCat.toUpperCase(Locale.ROOT);
    }

    /**
     * Parses given file and populates Database with correct Data.
     * @param inputFile
     */
    public static void readFile(final String inputFile) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(inputFile));

        JSONObject jo = (JSONObject) obj;

        // read data
        Long numberOfYears = (Long) jo.get("numberOfYears");
        Double santaBudget = Double.valueOf((Long) jo.get("santaBudget"));

        JSONObject initialData = (JSONObject) jo.get("initialData");

        // populate database
        Database.getInstance().setSantaBudget(santaBudget);
        Database.getInstance().setNumberOfYears(numberOfYears);

        // read all children
        JSONArray children = (JSONArray) initialData.get("children");
        for (Object o : children) {
            Child child = new Child();
            JSONObject jsonChild = (JSONObject) o;

            child.setId((Long) jsonChild.get("id"));
            child.setAge((Long) jsonChild.get("age"));

            String city = (String) jsonChild.get("city");
            child.setCity(Cities.valueOf(Parser.cityToEnum(city)));
            child.setFirstName((String) jsonChild.get("firstName"));
            child.setLastName((String) jsonChild.get("lastName"));
            child.setNiceScore(Double.valueOf((Long) jsonChild.get("niceScore")));
            child.setNiceScoreBonus(Double.valueOf((Long) jsonChild.get("niceScoreBonus")));
            child.setElf(ElvesType.valueOf(jsonChild.get("elf").toString().toUpperCase(Locale.ROOT)));

            ArrayList<String> giftPreference =
                    (ArrayList<String>) jsonChild.get("giftsPreferences");
            for (String s : giftPreference) {
                String enumReady = Parser.giftCatToEnum(s);
                child.addGiftsPreferences(Category.valueOf(enumReady));
            }

            // add child to database
            Database.getInstance().addToChildList(child);
        }

        // read all gifts
        JSONArray santaGiftsList = (JSONArray) initialData.get("santaGiftsList");
        for (Object o : santaGiftsList) {
            Gift gift = new Gift();
            JSONObject jsonGift = (JSONObject) o;

            gift.setPrice(Double.valueOf((Long) jsonGift.get("price")));
            gift.setProductName((String) jsonGift.get("productName"));

            String category = (String) jsonGift.get("category");
            gift.setCategory(Category.valueOf(Parser.giftCatToEnum(category)));

            // add gift to database
            Database.getInstance().addToGiftsList(gift);
        }

        // read all annual changes
        JSONArray annualChanges = (JSONArray) jo.get("annualChanges");
        for (Object o1 : annualChanges) {
            JSONObject jsonChanges = (JSONObject) o1;
            AnnualChange annualChange = new AnnualChange();

            annualChange.setNewSantaBudget(Double.valueOf(
                    (Long) jsonChanges.get("newSantaBudget")));
            annualChange.setStrategy(stringToCityStrategyEnum(jsonChanges.get("strategy").toString()));
            // parse newGifts
            JSONArray newGifts = (JSONArray) jsonChanges.get("newGifts");
            for (Object o2 : newGifts) {
                Gift gift = new Gift();
                JSONObject jsonGift = (JSONObject) o2;

                gift.setPrice(Double.valueOf((Long) jsonGift.get("price")));
                gift.setProductName((String) jsonGift.get("productName"));

                String category = (String) jsonGift.get("category");
                gift.setCategory(Category.valueOf(Parser.giftCatToEnum(category)));

                // add gift to annualchange
                annualChange.addNewGifts(gift);
            }

            // parse newChildren
            JSONArray newChildren = (JSONArray) jsonChanges.get("newChildren");
            for (Object o2 : newChildren) {
                Child child = new Child();
                JSONObject jsonChild = (JSONObject) o2;

                child.setId((Long) jsonChild.get("id"));
                child.setAge((Long) jsonChild.get("age"));

                String city = (String) jsonChild.get("city");
                child.setCity(Cities.valueOf(Parser.cityToEnum(city)));
                child.setFirstName((String) jsonChild.get("firstName"));
                child.setLastName((String) jsonChild.get("lastName"));
                child.setNiceScore(Double.valueOf((Long) jsonChild.get("niceScore")));
                child.setNiceScoreBonus(Double.valueOf((Long) jsonChild.get("niceScoreBonus")));
                child.setElf(ElvesType.valueOf(jsonChild.get("elf").toString().toUpperCase(Locale.ROOT)));

                ArrayList<String> giftPreference =
                        (ArrayList<String>) jsonChild.get("giftsPreferences");
                for (String s : giftPreference) {
                    String enumReady = Parser.giftCatToEnum(s);
                    child.addGiftsPreferences(Category.valueOf(enumReady));
                }

                // add child to annualchange
                annualChange.addNewChildren(child);
            }

            // parse ChildUpdates
            JSONArray childrenUpdates = (JSONArray) jsonChanges.get("childrenUpdates");
            for (Object o2 : childrenUpdates) {
                ChildUpdate childUpdate = new ChildUpdate();
                JSONObject jsonChildUpdate = (JSONObject) o2;

                childUpdate.setId((Long) jsonChildUpdate.get("id"));
                if (jsonChildUpdate.get("niceScore") != null) {
                    childUpdate.setNiceScore(Double.valueOf(
                            (Long) jsonChildUpdate.get("niceScore")));
                }

                ArrayList<String> giftPreference =
                        (ArrayList<String>) jsonChildUpdate.get("giftsPreferences");
                for (String s : giftPreference) {
                    String enumReady = Parser.giftCatToEnum(s);
                    childUpdate.addNewPreferences(Category.valueOf(enumReady));
                }

                if (jsonChildUpdate.get("elf") != null) {
                    childUpdate.setElf(ElvesType.valueOf(
                            jsonChildUpdate.get("elf").toString().toUpperCase(Locale.ROOT)));
                }

                // add updates to database
                annualChange.addChildrenUpdates(childUpdate);
            }
            Database.getInstance().addChange(annualChange);
        }

    }

}
