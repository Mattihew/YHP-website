package models;

import java.util.UUID;

public abstract class Person
{
	private final UUID id;
	private final String forename;
	private final String surname;

	public Person(String forename, String surname)
	{
		this(UUID.randomUUID(), forename, surname);
	}

	public Person(UUID uuid, String forename, String surname)
	{
		this.id = uuid;
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
