package greboreda.agendafx;

import greboreda.agendafx.controllers.main.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		final ConfigurableApplicationContext context = AgendaFxApplication.getContext();
		final MainController mainController = context.getBean(MainController.class);
		final Parent mainParent = mainController.build();
		final Scene scene = new Scene(mainParent);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}
