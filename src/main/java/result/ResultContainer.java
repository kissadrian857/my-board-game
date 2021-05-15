package result;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultContainer {

    private static List<Result> results = new ArrayList<>();

    public static void addResult(Result result){
        results.add(result);
        writeResults();
    }

    public static void readResults(){
        try {
            ResultListWrapper r =JAXBHelper.fromXML(ResultListWrapper.class,new FileInputStream("results.xml"));
            System.out.println(r.getResults());
            results= r.getResults();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void writeResults(){
        try{
            ResultListWrapper resultListWrapper = new ResultListWrapper(results);
            JAXBHelper.toXML(resultListWrapper,new FileOutputStream("results.xml"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
