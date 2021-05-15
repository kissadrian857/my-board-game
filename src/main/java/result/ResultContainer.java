package result;

import org.tinylog.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ResultContainer {

    private static List<Result> results = new ArrayList<>();

    public static void addResult(Result result) {
        Logger.info("Added actual result to results");
        results.add(result);
        writeResults();
    }

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
