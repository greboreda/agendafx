package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.AgendaFxApplication;
import greboreda.agendafx.MainApplication;
import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import greboreda.agendafx.controllers.main.MainController;
import greboreda.agendafx.domain.phone.PhoneToSave;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({AgendaFxApplication.class, ViewLoader.class})
public class PhoneInputTest {

	private static Thread appThread;
	private PhoneInput phoneInput;

	@BeforeClass
	public static void setUpClass() throws IOException {
		final ConfigurableApplicationContext ctx = mock(ConfigurableApplicationContext.class);
		PowerMockito.mockStatic(AgendaFxApplication.class);
		when(AgendaFxApplication.getContext()).thenReturn(ctx);
		final MainController mainController = mock(MainController.class);
		when(mainController.build()).thenReturn(mock(Parent.class));
		when(ctx.getBean(MainController.class)).thenReturn(mainController);
		appThread = new Thread(() -> Application.launch(MainApplication.class));
		appThread.start();
	}

	@Before
	public void setUp() {

		PowerMockito.mockStatic(ViewLoader.class);
		when(ViewLoader.load(phoneInput)).thenReturn(mock(Parent.class));
		phoneInput = spy(PhoneInput.class);
		phoneInput.phoneNumberInput = mock(TextField.class);
		phoneInput.phonePrefixInput = mock(TextField.class);
		phoneInput.saveButton = mock(Button.class);
		phoneInput.onSavePhoneHandler = mock(EventHandler.class);
		phoneInput.initialize();
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		appThread.join();
	}

	@Test
	public void should_handle_save_person_event_when_click_on_save_button() {

		when(phoneInput.phonePrefixInput.getText()).thenReturn("123");
		when(phoneInput.phoneNumberInput.getText()).thenReturn("456");

		phoneInput.saveButton.fire();

		verify(phoneInput).onSavePhone(any(MouseEvent.class));
		final ArgumentCaptor<SavePhoneEvent> eventCaptor = ArgumentCaptor.forClass(SavePhoneEvent.class);
		verify(phoneInput.onSavePhoneHandler).handle(eventCaptor.capture());
		final PhoneToSave phoneToSave = eventCaptor.getValue().getPhoneToSave();
		assertThat(phoneToSave.number, is("456"));
		assertThat(phoneToSave.prefix, is("123"));
	}

}