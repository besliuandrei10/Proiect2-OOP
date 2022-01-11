package simulation;

import common.Constants;
import database.Database;
import io.Parser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public final class SimulationWrapper {

    /**
     *  Constructor
     */
    private SimulationWrapper() {

    }

    /**
     * Begin simulation
     */
    public static void run() throws IOException, ParseException {
        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {

            // read data from file
            Parser.readFile(Constants.INPUT_PATH + i + Constants.FILE_EXTENSION);

            Simulation santaYearSimulation = new Simulation();
            santaYearSimulation.simulateYears();

            FileWriter file = new FileWriter(Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION);
            santaYearSimulation.writeReport(file);

            // clear database for next run
            Database.getInstance().clearDatabase();
        }

    }
}
