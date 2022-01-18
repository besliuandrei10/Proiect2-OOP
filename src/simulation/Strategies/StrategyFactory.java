package simulation.Strategies;

import enums.CityStrategyEnum;

public final class StrategyFactory {

    private StrategyFactory() {
    }
    /**
     *
     * @param category
     * @return
     */
    public static GiftStrategy createStrategy(final CityStrategyEnum category) {
        switch (category) {
            case ID -> {
                return new ById();
            }
            case NICE_SCORE -> {
                return new ByNiceScore();
            }
            case NICE_SCORE_CITY -> {
                return new ByNiceScoreCity();
            }
            default -> throw new IllegalStateException("Unexpected value: " + category);
        }
    }
}
