/** LoginController.java
 *  @author Paul King - s3449513
 */

package controller.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LoginController implements EventHandler<ActionEvent> {

	final Text actiontarget;
	
	public LoginController(Text actiontarget) {
		this.actiontarget = actiontarget;
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		actiontarget.setFill(Color.TURQUOISE);
        actiontarget.setText("Sign in button pressed");
	}

}
