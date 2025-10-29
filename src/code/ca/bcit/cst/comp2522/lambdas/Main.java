package ca.bcit.cst.comp2522.lambdas;

import java.util.*;
import java.util.function.*;

/**
 * The main driver class that demonstrates the functionality of HockeyPlayer and HockeyTeam classes.
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Main
{

    private static final int CURRENT_YEAR = 2025;

    private static final int GOALS_ALEX_MORGAN = 21;
    private static final int GOALS_BEN_CARTER = 6;
    private static final int GOALS_CASEY_YOUNG = 28;
    private static final int GOALS_DREW_SINGH = 0;
    private static final int GOALS_EVA_CHEN = 5;
    private static final int GOALS_FRANK_LEE = 15;

    private static final int BRITH_ALEX_MORGAN = 2002;
    private static final int BRITH_BEN_CARTER = 1999;
    private static final int BRITH_CASEY_YOUNG = 2004;
    private static final int BRITH_DREW_SINGH = 2000;
    private static final int BRITH_EVA_CHEN = 2001;
    private static final int BRITH_FRANK_LEE = 1998;

    private static final String CALL_UP_NAME = "New Player";
    private static final String CALL_UP_POSITION = "F";
    private static final int CALL_UP_YEAR = 2000;
    private static final int CALL_UP_GOALS = 10;

    private static final int MIN_ELIGIBLE_AGE = 20;
    private static final int MIN_ELIGIBLE_GOALS = 15;

    private static final int ZERO_START = 0;
    private static final int GOAL_THRESHOLD = 20;
    private static final String FORWARD_POSITION = "F";

    /**
     * Creates a sample hockey team with a predefined roster of players.
     *
     * @return a HockeyTeam instance with a sample roster
     */
    private static HockeyTeam sampleTeam()
    {
        final List<HockeyPlayer> ps = new ArrayList<>();

        ps.add(new HockeyPlayer("Alex Morgan", "F", BRITH_ALEX_MORGAN, GOALS_ALEX_MORGAN));
        ps.add(new HockeyPlayer("Ben Carter", "D", BRITH_BEN_CARTER, GOALS_BEN_CARTER));
        ps.add(new HockeyPlayer("Casey Young", "F", BRITH_CASEY_YOUNG, GOALS_CASEY_YOUNG));
        ps.add(new HockeyPlayer("Drew Singh", "G", BRITH_DREW_SINGH, GOALS_DREW_SINGH));
        ps.add(new HockeyPlayer("Eva Chen", "D", BRITH_EVA_CHEN, GOALS_EVA_CHEN));
        ps.add(new HockeyPlayer("Frank Lee", "F", BRITH_FRANK_LEE, GOALS_FRANK_LEE));

        return new HockeyTeam("BC Blizzards", ps);
    }

    /**
     * The main method that executes the program logic, demonstrating various functional programming concepts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args)
    {
        final HockeyTeam team;
        team = sampleTeam();
        final List<HockeyPlayer> roster;
        roster = team.getRoster();

        // 1) Supplier — create a call-up and add it
        final Supplier<HockeyPlayer> callUp;
        callUp = () -> new HockeyPlayer(CALL_UP_NAME, CALL_UP_POSITION, CALL_UP_YEAR, CALL_UP_GOALS);
        roster.add(callUp.get());

        // 2) Predicate — forwards with enough goals
        final Predicate<HockeyPlayer> isForward;
        isForward = player -> player.getPosition().equals(FORWARD_POSITION);

        final Predicate<HockeyPlayer> hasEnoughGoals;
        hasEnoughGoals = player -> player.getGoals() >= GOAL_THRESHOLD;

        System.out.println("Forwards with enough goals:");

        for (final HockeyPlayer player : roster)
        {
            if (isForward.test(player) &&
                hasEnoughGoals.test(player))
            {
                System.out.println(player.getName());
            }

        }

        // 3) Function — map player to a label string
        final Function<HockeyPlayer, String> playerLabel;
        playerLabel = player -> player.getName() + " — " + player.getGoals() + "G";

        System.out.println("Player Labels:");

        for (final HockeyPlayer player : roster)
        {
            System.out.println(playerLabel.apply(player));
        }

        // 4) Consumer — print names
        final Consumer<HockeyPlayer> printName;
        printName = player -> System.out.println(player.getName());

        System.out.println("Player Names:");

        roster.forEach(printName);

        // 5) UnaryOperator — uppercase names
        final UnaryOperator<String> toUpperCase;
        toUpperCase = String::toUpperCase;

        System.out.println("Uppercase Player Names:");

        for (final HockeyPlayer player : roster)
        {
            System.out.println(toUpperCase.apply(player.getName()));
        }

        // 6) Comparator — sort by goals DESC
        final Comparator<HockeyPlayer> sortByGoalsDesc;
        sortByGoalsDesc = (p1, p2) -> Integer.compare(p2.getGoals(), p1.getGoals());
        roster.sort(sortByGoalsDesc);

        System.out.println("Sorted Players by Goals (Descending):");

        for (final HockeyPlayer player : roster)
        {
            System.out.println(player.getName() + " — " + player.getGoals() + "G");
        }

        // 7) Aggregation (loop) — total goals
        int totalGoals = ZERO_START;
        for (int i = ZERO_START; i < roster.size(); i++)
        {
            final HockeyPlayer player;
            player = roster.get(i);
            totalGoals += player.getGoals();
        }

        System.out.println("Total Goals: " + totalGoals);

        // 8) Custom FI (EligibilityRule)
        @FunctionalInterface
        interface EligibilityRule
        {
            boolean test(HockeyPlayer player,
                         int minAge,
                         int minGoals,
                         int currentYear);
        }

        final EligibilityRule isEligible;
        isEligible = (player, minAge, minGoals, year) ->
        {
            final int age = year - player.getYearOfBirth();
            return age >= minAge && player.getGoals() >= minGoals;
        };

        System.out.println("Eligible Players (minAge = " + MIN_ELIGIBLE_AGE + ", minGoals = " + MIN_ELIGIBLE_GOALS + "):");
        for (final HockeyPlayer player : roster)
        {
            if (isEligible.test(player, MIN_ELIGIBLE_AGE, MIN_ELIGIBLE_GOALS, CURRENT_YEAR))
            {
                System.out.println(player.getName());
            }
        }
    }
}

