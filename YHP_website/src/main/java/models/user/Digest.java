package models.user;

public class Digest
{
	private final String encodedValue;
	private final String salt;
	private final int iterations;
	
	public Digest(final String digestedString)
	{
		final String[] sections = digestedString.split("$");
		this.salt = sections[0];
		this.iterations = Integer.parseInt(sections[1]);
		this.encodedValue = sections[2];
	}
	
	public String getEncodedPassword()
	{
		return this.encodedValue;
	}
	
	public String getSalt()
	{
		return this.salt;
	}
	
	public int getIterations()
	{
		return this.iterations;
	}
}
