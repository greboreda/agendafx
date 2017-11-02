package greboreda.agendafx.domain.person;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Persons implements Iterable<Person> {

	private final List<Person> collection;

	private Persons(List<Person> collection) {
		this.collection = collection;
	}

	@Override
	public Iterator<Person> iterator() {
		return collection.iterator();
	}

	@Override
	public void forEach(Consumer<? super Person> action) {
		collection.forEach(action);
	}

	public static Persons of(List<Person> persons) {
		return new Persons(persons);
	}

	public List<Person> asList() {
		return Collections.unmodifiableList(collection);
	}


}
