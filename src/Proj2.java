import java.text.DecimalFormat;
import java.util.Scanner;

public class Proj2 {

    private static final double TAX_RATE = 0.0625;
    private static final DecimalFormat format = new DecimalFormat("#,##0.00");
    private static boolean parkingPurchased = false;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        displayWelcomeMessage();
        String plan = choosePlan(scnr);
        String seating = chooseSeating(scnr);
        int numOfSeats = chooseSeatingAmount(scnr);
        int parkingAmount = chooseParking(plan, scnr);

        double grandTotal = calculateTotals(plan, seating, numOfSeats, parkingAmount);
        confirmPurchase(scnr, grandTotal);
    }

    private static void displayWelcomeMessage() {
        System.out.println("*** Welcome to the Kansas City Royals 2020 Season Ticketing Application ***");
        System.out.println("                 (Application developed by Zackary Nichol)                 ");
        System.out.println("                           ---- CROWNED KC! -----                          ");
        System.out.println("");
    }

    private static String choosePlan(Scanner scnr) {
        System.out.println("Please select one of the Season Ticket Plans listed below:");
        System.out.println("        1) Full Season Plan (tickets to all 81 regular season games!)");
        System.out.println("        2) Half Season Plan (tickets to 40 regular season games)");
        System.out.println("        3) Quarter Season (tickets to 20 regular season games)");

        System.out.println("");
        System.out.print("        Selection: ");

        int input = Integer.parseInt(scnr.nextLine());
        System.out.println("");

        if (input > 3 || input < 1) {
            System.out.println("Not a valid Plan selected. Enter 1-3 only");
            System.exit(0);
        }
        else {
            switch (input) {
                case 1: return "Full Season Plan (tickets to all 81 regular season games!)";
                case 2: return "Half Season Plan (tickets to 40 regular season games)";
                case 3: return "Quarter Season (tickets to 20 regular season games)";
                default: return null;
            }
        }
        return null;
    }

    private static String chooseSeating(Scanner scnr) {
        System.out.println("Please select one of the Seating Options listed below:");
        System.out.println("        Seating              Per Game");
        System.out.println("    1) BATS Crown Club Seats     $235");
        System.out.println("    2) Diamond Club Box          $95");
        System.out.println("    3) Dugout Box                $51");
        System.out.println("    4) Dugout Plaza              $40");
        System.out.println("    5) Field Box                 $36");
        System.out.println("    6) Field Plaza               $28");
        System.out.println("    7) Outfield Box              $26");
        System.out.println("    8) Hy-Vee Box                $16");

        System.out.println("");
        System.out.print("    Selection: ");

        int input = Integer.parseInt(scnr.nextLine());
        System.out.println("");

        if (input > 8 || input < 1) {
            System.out.println("Not a valid Seating option. 1-8 only");
            System.exit(0);
        }
        else {
            switch (input) {
                case 1: return "BATS Crown Club Seats     $235";
                case 2: return "Diamond Club Box          $95";
                case 3: return "Dugout Box                $51";
                case 4: return "Dugout Plaza              $40";
                case 5: return "Field Box                 $36";
                case 6: return "Field Plaza               $28";
                case 7: return "Outfield Box              $26";
                case 8: return "Hy-Vee Box                $16";
                default: return null;
            }
        }
        return null;
    }

    private static int chooseSeatingAmount(Scanner scnr) {
        System.out.print("    How many seats would you like to purchase? ");
        return Integer.parseInt(scnr.nextLine());
    }

    private static int chooseParking(String plan, Scanner scnr) {
        int baseCost = 0;
        String passString = "";

        if (plan.contains("Full")) {
            passString = " a discounted rate of $8 per game (reguarly $10).";
            baseCost = 8;
        }
        else if (plan.contains("Half")) {
            passString = " a discounted rate of $9 per game (reguarly $10).";
            baseCost = 9;
        }
        else {
            passString = " a rate of $10 per game.";
            baseCost = 10;
        }

        System.out.println("");
        System.out.println("    A single parking pass is available for purchase at" + passString);
        System.out.println("    (You will be charge for all games of a given package.)");
        System.out.print("    Would you like to include parking? (Y or N): ");

        String input = scnr.nextLine();

        if (input.toLowerCase().equals("y")) {
            parkingPurchased = true;
        }
        System.out.println("");

        return baseCost;

    }

    private static double calculateTotals(String plan, String seating, int numOfSeats, int parkingAmount) {

        int numPlanGames = Integer.parseInt(plan.substring(plan.indexOf("regular") - 3, plan.indexOf("regular") - 1));

        int seatingCost = Integer.parseInt(seating.substring(seating.indexOf('$') + 1)) * numOfSeats;
        int parkingCost = numPlanGames * parkingAmount;
        double taxTotal = TAX_RATE * (numPlanGames * seatingCost);

        double grandTotal = taxTotal + (numPlanGames * seatingCost) + parkingCost;

        System.out.println(
            "You purchased the " + plan.substring(0, plan.indexOf('(') - 5)
            + numPlanGames + "-game plan /" + numOfSeats + " "
            + seating.substring(0, seating.indexOf('$')).trim() + " tickets"
            + (parkingPurchased ? " with Parking" : " without Parking")
        );
        System.out.println("    Ticket Total: $" + format.format( (double) (numPlanGames * seatingCost)));
        System.out.println("    Tax: $" + format.format(taxTotal));
        System.out.println("    Parking: $" + format.format( (double) parkingCost));
        System.out.println("    Grand Total: $" + format.format(grandTotal));
        System.out.println("");

        return grandTotal;

    }

    private static void confirmPurchase(Scanner scnr, double grandTotal) {
        System.out.print("Confirm Order (Y or N)? ");

        String input = scnr.nextLine();
        System.out.println("");

        if (input.toLowerCase().equals("y")) {
            System.out.println("Order was confirmed. $" + format.format(grandTotal) + " will be charged to the account on file.");
        }
        else {
            System.out.println("Purchase cancelled. Re-run the application to reselect tickets.");
        }

    }


}
