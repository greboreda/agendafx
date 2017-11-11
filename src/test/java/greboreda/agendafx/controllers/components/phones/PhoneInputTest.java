package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.AgendaFxApplication;
import greboreda.agendafx.MainApplicationMock;
import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({AgendaFxApplication.class, ViewLoader.class})
public class PhoneInputTest {

	private PhoneInput phoneInput;

	@BeforeClass
	public static void setUpClass() throws IOException {
		MainApplicationMock.initialize();
	}

	@Before
	public void setUp() {
		PowerMockito.mockStatic(ViewLoader.class);
		when(ViewLoader.load(phoneInput)).thenReturn(mock(Parent.class));
		phoneInput = spy(PhoneInput.class);
		phoneInput.phoneNumberInput = spy(TextField.class);
		phoneInput.phonePrefixInput = spy(TextField.class);
		phoneInput.saveButton = spy(Button.class);
	}

	@AfterClass
	public static void tearDown() throws InterruptedException {
		Platform.exit();
	}

	@Test
	public void should_disable_save_button_when_initialize() {
		phoneInput.initialize();
		verify(phoneInput.saveButton).setDisable(true);
	}

	@Test
	public void should_set_save_button_behaviour_when_initialize() {
		phoneInput.initialize();
		verify(phoneInput.saveButton).setOnMouseClicked(e -> phoneInput.onSavePhone(e));
	}

	@Test
	@Ignore
	public void should_fire_save_person_event_when_handled_on_save() {

		phoneInput.phonePrefixInput.setText("123");
		phoneInput.phoneNumberInput.setText("456");

		phoneInput.onSavePhone(null);

		final ArgumentCaptor<SavePhoneEvent> eventCaptor = ArgumentCaptor.forClass(SavePhoneEvent.class);
		verify(phoneInput).fireEvent(eventCaptor.capture());
		final SavePhoneEvent savePhoneEvent = eventCaptor.getValue();
		assertThat(savePhoneEvent.getPhonePrefix(), is("123"));
		assertThat(savePhoneEvent.getPhoneNumber(), is("456"));
	}

}