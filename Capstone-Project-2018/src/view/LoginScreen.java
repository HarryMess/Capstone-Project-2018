/** LoginScreen.java
 *  @author Paul King - s3449513
 */

package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen extends Stage {
	
	/**	 
	 * @author Peggy Fisher
	 * Reference: https://www.lynda.com/Java-tutorials/JavaFX-GUI-Development/466182-2.html
	 */
	public LoginScreen() {
		
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(grid, 300, 275);
        
        Text scenetitle = new Text("Please Login: ");
        scenetitle.setFont(Font.font("Tahoma",FontWeight.NORMAL, 20));
        Label userName = new Label("User Name:");
        TextField userTextField = new TextField();
        Label pw = new Label("Password: ");
        PasswordField pwBox = new PasswordField();
        
        grid.add(scenetitle,0,0,2,1);
        grid.add(userName,0,1);
        grid.add(userTextField, 1,1);
        grid.add(pw,0,2);
        grid.add(pwBox,1,2);
        
        grid.setGridLinesVisible(false);
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn,1,4);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget,1,6);
        
        // this calls an event from a class tahat is found in the controller package
        btn.setOnAction(new LoginController(actiontarget)); 

        scene.getStylesheets().
                add(Login.class.getResource("/view/css/Login.css").toExternalForm());
        
        setTitle("Stock Market Login");
        setScene(scene);
        show();
	}
}
