package greboreda.agendafx.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleRetriever {

	public static ResourceBundle get() {
		return ResourceBundle.getBundle("i18n/messages", Locale.ENGLISH);
	}

}
