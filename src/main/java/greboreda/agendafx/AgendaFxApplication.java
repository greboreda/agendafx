package greboreda.agendafx;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AgendaFxApplication {

	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(AgendaFxApplication.class, args);
		Application.launch(MainApplication.class);
	}

	public static ConfigurableApplicationContext getContext() {
		return ctx;
	}
}
