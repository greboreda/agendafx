package greboreda.agendafx;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import greboreda.agendafx.views.MainView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgendaFxApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
		launchApp(AgendaFxApplication.class, MainView.class, args);
	}
}
