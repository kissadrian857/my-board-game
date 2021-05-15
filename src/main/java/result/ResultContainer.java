package result;

import org.tinylog.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that reads and writes {@code ResultListWrapper} object from xml file
 * and stores it's elements in a {@code List} object.
 */
public class ResultContainer {

    private static List<Result> results = new ArrayList<>();

    /**
     * Adds a result to results and writes the results to xml file.
     *
     * @param result the result to be added to results
     */
    public static void addResult(Result result) {
        Logger.info("Added actual result to results");
        results.add(result);
        writeResults();
    }

    /**
     * Reads results from xml file,if it doesn't exist it does nothing.
     * If the xml exists read a {@code ResultWrapper} object from it and set the content of results to the wrapped
     * list
     */
    public static void readResults() {
        File file = new File("results.xml");
        if(file.exists()){
            Logger.info("Read results from results.xml");
            try {
                ResultListWrapper r = JAXBHelper.fromXML(ResultListWrapper.class, new FileInputStream("results.xml"));
                System.out.println(r.getResults());
                results = r.getResults();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes a {@code ResultListWrapper} initialized with the list of results to xml.
     */
    public static void writeResults() {
        Logger.info("Wrote result to result.xml");
        try {
            ResultListWrapper resultListWrapper = new ResultListWrapper(results);
            JAXBHelper.toXML(resultListWrapper, new FileOutputStream("results.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Result> getResults() {
        return results;
    }
}
