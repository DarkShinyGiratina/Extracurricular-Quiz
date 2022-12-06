/*
Subjects.java
An enum which contains all the different subjects that the quiz can weight.
 */
public enum Subjects {
	ART(0),
	WRITING(1),
	THEATER(2),
	BUILDING(3),
	MATH(4),
	SCIENCE(5),
	COMPUTERS(6),
	SOC(7);

	public final int INDEX;
	public static final int NUMBER_OF_SUBJECTS = values().length;
	private Subjects(int index) {
		this.INDEX = index;
	}

}
