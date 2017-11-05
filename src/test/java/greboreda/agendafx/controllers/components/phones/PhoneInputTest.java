package greboreda.agendafx.controllers.components.phones;

import greboreda.agendafx.controllers.JavaFXThreadingRule;
import greboreda.agendafx.controllers.ViewLoader;
import greboreda.agendafx.controllers.components.phones.events.SavePhoneEvent;
import greboreda.agendafx.domain.phone.PhoneToSave;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ViewLoader.class})
public class PhoneInputTest {

	private PhoneInput phoneInput;

	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	@Before
	public void setUp() {

		PowerMockito.mockStatic(ViewLoader.class);
		when(ViewLoader.load(phoneInput)).then(a -> {
			phoneInput.phoneNumberInput = mock(TextField.class);
			phoneInput.phonePrefixInput = mock(TextField.class);
			phoneInput.saveButton = mock(Button.class);
			phoneInput.onSavePhoneHandler = mock(EventHandler.class);
			return mock(Parent.class);
		});
		phoneInput = spy(PhoneInput.class);
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