package ca.bcit.cst.comp2522.lambdas;

/**
 * Represents a hockey player with a name, position, year of birth, and goals scored.
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class HockeyPlayer
{
    private final String name;
    private final String position; // "F", "D", or "G"
    private final int yearOfBirth;
    private final int goals;

    /**
     * Constructs a HockeyPlayer with the specified name, position, year of birth, and goals.
     *
     * @param name        the name of the player
     * @param position    the position of the player
     * @param yearOfBirth the year the player was born
     * @param goals       the number of goals scored by the player
     */
    public HockeyPlayer(final String name,
                        final String position,
                        final int yearOfBirth,
                        final int goals)
    {
        this.name        = name;
        this.position    = position;
        this.yearOfBirth = yearOfBirth;
        this.goals       = goals;
    }

    /**
     * Returns the name of the player.
     *
     * @return the player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the position of the player.
     *
     * @return the player's position
     */
    public String getPosition()
    {
        return position;
    }

    /**
     * Returns the year of birth of the player.
     *
     * @return the player's year of birth
     */
    public int getYearOfBirth()
    {
        return yearOfBirth;
    }

    /**
     * Returns the number of goals scored by the player.
     *
     * @return the player's goals
     */
    public int getGoals()
    {
        return goals;
    }
}
