package homework.search.object;

import java.util.List;

public class Document {
    private Integer docId;
    private List<String> terms;

    public Document(final Integer docId, final List<String> terms) {
        this.docId = docId;
        this.terms = terms;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(final Integer docId) {
        this.docId = docId;
    }

    public List<String> getTerms() {
        return terms;
    }

    public void setTerms(final List<String> terms) {
        this.terms = terms;
    }
}
