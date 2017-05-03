
package notetaker;

import java.io.Serializable;

/**
 *
 * @author Jeff Campbell
 */
public class Note implements Serializable {
    private final String title, doc;
    private final String tags;
    
    public Note (String title, String doc, String tags){
        this.title = title;
        this.doc = doc;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getDoc() {
        return doc;
    }

    public String getTags() {
        return tags;
    }
    
    @Override
    public String toString() {
        return String.format("%s", title);
    }
}
