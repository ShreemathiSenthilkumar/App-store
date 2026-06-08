import java.util.*;

    public class Main {
        static List<String[]> apps = new ArrayList<>();
        static List<String[]> cart = new ArrayList<>();
        static double balance = 15.00;
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
            apps.add(new String[]{"PhotoSnap",   "Photo",   "2.99", "4.5"});
            apps.add(new String[]{"SpeedRunner", "Games",   "0.00", "4.2"});
            apps.add(new String[]{"BudgetBuddy", "Finance", "4.99", "3.8"});
            apps.add(new String[]{"CodePad",     "Tools",   "0.00", "4.9"});
            apps.add(new String[]{"MusicBox",    "Music",   "0.99", "3.5"});

            int choice;
            do {
                System.out.println("\n=== APP STORE === Balance: $" + String.format("%.2f", balance));
                System.out.println("1. Browse  2. Buy  3. My Apps  4. Add App  5. Exit");
                System.out.print("Choice: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) browse();
                else if (choice == 2) buy();
                else if (choice == 3) myApps();
                else if (choice == 4) addApp();
            } while (choice != 0);
            System.out.println("Bye!");
        }

        static void browse() {
            System.out.printf("%n%-3s %-14s %-8s %-6s %s%n", "ID", "Name", "Category", "Price", "Rating");
            System.out.println("-".repeat(45));
            for (int i = 0; i < apps.size(); i++) {
                String[] a = apps.get(i);
                System.out.printf("%-3d %-14s %-8s $%-5s %s★%n", i+1, a[0], a[1], a[2], a[3]);
            }
        }

        static void buy() {
            browse();
            System.out.print("Enter ID to buy: ");
            int id = Integer.parseInt(sc.nextLine()) - 1;
            if (id < 0 || id >= apps.size()) { System.out.println("Invalid ID."); return; }
            String[] app = apps.get(id);
            if (cart.contains(app)) { System.out.println("Already owned!"); return; }
            double price = Double.parseDouble(app[2]);
            if (balance < price) { System.out.println("Insufficient balance!"); return; }
            balance -= price;
            cart.add(app);
            System.out.println("Purchased: " + app[0] + " | Remaining: $" + String.format("%.2f", balance));
        }

        static void myApps() {
            if (cart.isEmpty()) { System.out.println("No apps purchased yet."); return; }
            System.out.println("\nYour Apps:");
            cart.forEach(a -> System.out.println("  - " + a[0] + " (" + a[1] + ")"));
        }

        static void addApp() {
            System.out.print("Name: ");      String name = sc.nextLine();
            System.out.print("Category: ");  String cat  = sc.nextLine();
            System.out.print("Price: $");    String price = sc.nextLine();
            apps.add(new String[]{name, cat, price, "0.0"});
            System.out.println("'" + name + "' added!");
        }
    }
