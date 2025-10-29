package ca.bcit.cst.comp2522.lambdas;

import java.util.List;

/**
 * Represents a hockey team with a name and a roster of players.
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class HockeyTeam
{
    private final String             name;
    private final List<HockeyPlayer> roster;

    /**
     * Constructs a HockeyTeam with the specified name and roster of players.
     *
     * @param name   the name of the team
     * @param roster the list of players in the team
     */
    public HockeyTeam(final String name,
                      final List<HockeyPlayer> roster)
    {
        this.name   = name;
        this.roster = roster;
    }

    /**
     * Returns the name of the team.
     *
     * @return the team's name
     */
    public String getName() {
        // Returns the name of the hockey team
        return name;
    }

    /**
     * Returns the roster of players in the team.
     *
     * @return the list of players in the team
     */
    public List<HockeyPlayer> getRoster() {
        // Returns the roster of hockey players in the team
        return roster;
    }
}
