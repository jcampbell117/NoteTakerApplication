
package notetaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jeff Campbell
 */
public class NoteTakerViewController implements Initializable {

    @FXML private TextField titleTextField;
    @FXML private TextField tagsTextField;
    @FXML private TextArea docTextArea;
    @FXML private Label messageLabel;
    @FXML private TextField openTextField;
    private Note note;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private File file;
    
    /**
     * This method validates user information and creates the text
     * file to the designated directory (if applicable)
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void clickButton() throws FileNotFoundException, IOException {
        
        //validation
        if (titleTextField.getText().trim().isEmpty())
            messageLabel.setText("Your note needs a title.");
        else if (docTextArea.getText().trim().isEmpty())
            messageLabel.setText("Your note has nothing in it.");
        else {
            note = new Note(titleTextField.getText().trim(), docTextArea.getText().trim(),
                    tagsTextField.getText().trim());
                
            //whether tags are specified or not
            if(!tagsTextField.getText().isEmpty()) {
                file = new File(String.format("notes/%s", note.getTags()));
                if(!file.exists())
                    file.mkdirs();
                output = new ObjectOutputStream(Files.newOutputStream(Paths.get(String.format("notes/%s/%s.ser", note.getTags(), note.getTitle()))));
            }
            else {
                file = new File("notes");
                if(!file.exists())
                    file.mkdir();
                output = new ObjectOutputStream(Files.newOutputStream(Paths.get(String.format("notes/%s.ser", note.getTitle()))));
            }
            
            output.writeObject(note);
            output.close();
            
            messageLabel.setText(String.format("%s has been created.", note.getTitle()));
        }
    } // end of clickButton() method
    
    /**
     * This method opens a pre-existing note object for editing if applicable
     * @throws java.io.IOException
     */
    public void clickOpenButton() throws IOException, ClassNotFoundException
    {
        String read = "notes/" + openTextField.getText().trim() + ".ser";
        input = new ObjectInputStream(Files.newInputStream(Paths.get(read)));
        
        Note openNote = (Note) input.readObject();
        titleTextField.setText(openNote.getTitle());
        tagsTextField.setText(openNote.getTags());
        docTextArea.setText(openNote.getDoc());
        input.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
