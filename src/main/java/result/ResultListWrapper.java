package result;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for a list of {@code Result} objects.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultListWrapper {

    @XmlElement(name = "result")
    private List<Result> results = new ArrayList<>();

    /**
     * Parametrized constructor.
     *
     * @param results the list of {@code Result} objects to be wrapped in
     */
    public ResultListWrapper(List<Result> results) {
        this.results = results;
    }

    public ResultListWrapper() {
    }

    public List<Result> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return results.toString();
    }
}
