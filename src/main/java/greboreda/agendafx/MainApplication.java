package greboreda.agendafx;

import greboreda.agendafx.controllers.main.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

public class MainApplication extends Application {

	private Scene mainScene;

	@Override
	public void init() throws Exception {
		final MainController mainController = retrieveMainController();
		final Parent mainParent = mainController.build();
		this.mainScene = new Scene(mainParent);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	private MainController retrieveMainController() {
		final ConfigurableApplicationContext context = AgendaFxApplication.getContext();
		return context.getBean(MainController.class);
	}


}
