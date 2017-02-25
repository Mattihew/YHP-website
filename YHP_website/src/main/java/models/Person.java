package models;

import java.util.UUID;

public abstract class Person
{
	private final UUID id;
	private final String forename;
	private final String surname;

	// @SuppressWarnings("unused")
	// private Person()
	// {
	// //Default constructor should not be used
	// this.id = null;
	// this.forename = null;
	// this.surname = null;
	// }

	public Person(String forename, String surname)
	{
		this.id = UUID.randomUUID();
		this.forename = forename;
		this.surname = surname;
	}

	public Person(String uuid, String forename, String surname)
	{
		this.id = UUID.fromString(uuid);
		this.forename = forename;
		this.surname = surname;
	}

	/**
	 * @return the uuid
	 */
	public UUID getUuid()
	{
		return id;
	}

	/**
	 * @return the forename
	 */
	public String getForename()
	{
		return forename;
	}

	/**
	 * @return the surname
	 */
	public String getSurname()
	{
		return surname;
	}
}
