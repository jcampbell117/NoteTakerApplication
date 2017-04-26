
package notetaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
    private Note note;
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
            note = new Note(titleTextField.getText(), docTextArea.getText(),
                    tagsTextField.getText(), LocalDateTime.now());
            
            //set file path (optional)
            if (!tagsTextField.getText().trim().isEmpty()) 
                file = new File(String.format("notes/%s", note.getTags()));
            else 
                file = new File("notes");
            
            file.mkdirs();
            note.printToFile(file.toString());
            messageLabel.setText(String.format("%s has been created.", note.getTitle()));
        }
    } // end of clickButton() method

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
