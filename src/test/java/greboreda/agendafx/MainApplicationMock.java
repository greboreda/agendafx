package greboreda.agendafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplicationMock extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
	}

	public static void initialize() {
		final Thread thread = new Thread(() -> Application.launch(MainApplicationMock.class));
		thread.start();
	}
}
