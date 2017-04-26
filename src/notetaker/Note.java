
package notetaker;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Formatter;

/**
 *
 * @author Jeff Campbell
 */
public class Note {
    private final String title, doc;
    private final String tags;
    private final LocalDateTime make;
    
    public Note (String title, String doc, String tags, LocalDateTime make){
        this.title = title;
        this.doc = doc;
        this.tags = tags;
        this.make = make;
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

    public LocalDateTime getMake() {
        return make;
    }
    
    /**
     * This method creates a text file in the directory passed to it
     * @param directory
     * @throws FileNotFoundException 
     */
    public void printToFile(String directory) throws FileNotFoundException 
    {
        Formatter outputStream = new Formatter(String.format("%s/%s.txt", directory, getTitle()));
        outputStream.format(getDoc());
        outputStream.close();
    }
    
    @Override
    public String toString() {
        return String.format("%s made at %s", title, make.toString());
    }
}
