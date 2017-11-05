package greboreda.agendafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplicationMock extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
	}

	public static Thread getThread() {
		return new Thread(() -> Application.launch(MainApplicationMock.class));
	}
}
