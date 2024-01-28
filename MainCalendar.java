import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Display_calendar{
    Scanner scan = new Scanner (System.in);
    void display_calendar(){
        System.out.print("Enter year (e.g: 1234) : ");
        int year = scan.nextInt();
        System.out.print("Enter month (1-12) : ");
        int month = scan.nextInt();

        if (month < 1 || month > 12)
            System.out.println("Wrong input!");
        else
            printMonth(year, month);
    }
    void printMonth(int year, int month) {
        printMonthTitle(year, month);
        printMonthBody(year, month);
    }
    void printMonthTitle(int year, int month) {
        System.out.println("         " + getMonthName(month) + " " + year);
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }
    public String getMonthName(int month) {
        String monthName = null;
        switch (month) {
            case 1: monthName = "January"; break;
            case 2: monthName = "February"; break;
            case 3: monthName = "March"; break;
            case 4: monthName = "April"; break;
            case 5: monthName = "May"; break;
            case 6: monthName = "June"; break;
            case 7: monthName = "July"; break;
            case 8: monthName = "August"; break;
            case 9: monthName = "September"; break;
            case 10: monthName = "October"; break;
            case 11: monthName = "November"; break;
            case 12: monthName = "December";
        }
        return monthName;
    }
    void printMonthBody(int year, int month) {
        int startDay = getStartDay(year, month);
        int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);
        int i = 0;
        for (i = 0; i < startDay; i++)
            System.out.print("    ");
        for (i = 1; i <= numberOfDaysInMonth; i++) {
            if (i < 10)
                System.out.print("   " + i);
            else
                System.out.print("  " + i);
            if ((i + startDay) % 7 == 0)
                System.out.println();
        }
        System.out.println();
    }
    int getStartDay(int year, int month) {
        int startDay1800 = 3;
        int totalNumberOfDays = getTotalNumberOfDays(year, month);
        return (totalNumberOfDays + startDay1800) % 7;
    }
    int getTotalNumberOfDays(int year, int month) {
        int total = 0;
        for (int i = 1800; i < year; i++)
            if (isLeapYear(i))
                total = total + 366;
            else
                total = total + 365;
        for (int i = 1; i < month; i++)
            total = total + getNumberOfDaysInMonth(year, i);
        return total;
    }
    int getNumberOfDaysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            return 31;
        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        if (month == 2) return isLeapYear(year) ? 29 : 28;
        return 0;
    }
    boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }
}
class Events {
    private String title;
    private Date date;
    public Events(String title, Date date) {
        this.title = title;
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public Date getDate() {
        return date;
    }
}
class Manage_Events{
    static  ArrayList<Events> events=new ArrayList<>();
    public boolean userEventsFound;
    Scanner scan = new Scanner(System.in);
    public Map<String, Date> defineRecurringEvents(int year) {
        Map<String, Date> recurringEvents = new HashMap<>();
        recurringEvents.put("New Year's Day", createDate(year, 1, 1));
        recurringEvents.put("Kashmir Day", createDate(year, 2, 5));
        recurringEvents.put("Pakistan Day", createDate(year, 3, 23));
        recurringEvents.put("April Fools' Day", createDate(year, 4, 1));
        recurringEvents.put("Labour Day", createDate(year, 5, 1));
        recurringEvents.put("Bank Holiday", createDate(year, 7, 1));
        recurringEvents.put("Independence Day", createDate(year, 8, 14));
        recurringEvents.put("Defence Day", createDate(year, 9, 6));
        recurringEvents.put("Teacher's Day", createDate(year, 10, 5));
        recurringEvents.put("Iqbal Day", createDate(year, 11, 9));
        recurringEvents.put("Christmas Eve", createDate(year, 12, 24));
        recurringEvents.put("Christmas Day", createDate(year, 12, 25));
        recurringEvents.put("Quaid-e-Azam Day", createDate(year, 12, 25));
        recurringEvents.put("New Year's Eve", createDate(year, 12, 31));
        recurringEvents.put("Mother's Day", getMothersDay(year)); // Custom method
        recurringEvents.put("Father's Day", getFathersDay(year)); // Custom method

        return recurringEvents;
    }
    // Custom method to get Mother's Day (2nd Sunday in May)
    public Date getMothersDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.add(Calendar.DAY_OF_MONTH, 7); // Second Sunday

        return calendar.getTime();
    }
    // Custom method to get Father's Day (3rd Sunday in June)
    public Date getFathersDay(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.add(Calendar.DAY_OF_MONTH, 14); // Third Sunday

        return calendar.getTime();
    }
    public void set_event(String title, Date date) {

        Events event = new Events(title, date);
        events.add(event);
        System.out.println("Event set successfully!");

    }
    public void search_event() {
        Calendar calendar=Calendar.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.print("Enter event title: ");
        String searchTitle = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean eventFound = false;
        // Search for user-set events
        for (Events event : events) {
            if (event.getTitle().equalsIgnoreCase(searchTitle) && isDateInMonth(event.getDate(), year, calendar.get(Calendar.MONTH) + 1)) {
                Date eventDate = event.getDate();
                System.out.println("User-set event found: " + dateFormat.format(eventDate) + " - " + searchTitle);
                eventFound = true;
            }
        }

        //Search for recurring events
        Map<String, Date> recurringEvents = defineRecurringEvents(year);

        for (Map.Entry<String, Date> entry : recurringEvents.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(searchTitle)) {
                Date eventDate = entry.getValue();
                System.out.println("Event found: " + dateFormat.format(eventDate) + " - " + searchTitle);
                eventFound = true;
            }
        }
        if (!eventFound) {
            System.out.println(searchTitle+" not found in the year " + year);
        }
    }


    public void delete_event() {
        Calendar calendar=Calendar.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year of the event: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        System.out.print("Enter title of the event to delete: ");
        String deleteTitle = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean eventFound = false;

        // Check and remove user-set events
        for (int i = 0; i < events.size(); i++) {
            Events event = events.get(i);
            Date eventDate = event.getDate();
            if (event.getTitle().equalsIgnoreCase(deleteTitle) && isDateInMonth(eventDate, year, calendar.get(Calendar.MONTH) + 1)) {
                events.remove(i); // Remove the event if found
                eventFound = true;
                System.out.println("Event deleted: " + dateFormat.format(eventDate) + " - " + deleteTitle);
                i--; // Adjust the index after removal
            }
        }
        if (!eventFound) {
            System.out.println(deleteTitle + " not found in the year " + year);
        }
    }
    // Check if a given date falls within a specific month and year
    public boolean isDateInMonth(Date date, int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int eventYear = calendar.get(Calendar.YEAR);
        int eventMonth = calendar.get(Calendar.MONTH) + 1; // Month is 0-based, so we ae adding 1 here

        return eventYear == year && eventMonth == month;
    }
    public Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Month is 0-based, so subtract 1
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
    public void display(int year, int month){
        System.out.println("This is display method ");

    }
}
class Display_Recurring_Events extends Manage_Events{
    public void display(int year, int month) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Month is 0-based, so subtract 1

        // Define the recurring events by reusing the code from defineRecurringEvents
        Map<String, Date> recurringEvents = defineRecurringEvents(year);

        // Print the events for the specified month
        Display_calendar obj=new Display_calendar();
        System.out.println("Events for " + obj.getMonthName(month) + " " + year + ":");
        boolean eventsFound = false; // To track if any events were found

        for (Map.Entry<String, Date> entry : recurringEvents.entrySet()) {
            Date eventDate = entry.getValue();
            if (isDateInMonth(eventDate, year, month)) {
                System.out.println(dateFormat.format(eventDate) + ": " + entry.getKey());
                eventsFound = true; // Events were found for this month
            }
        }
        if (!eventsFound) {
            System.out.println("No recurring events to display for this month.");
        }
    }
}
class Display_UserSet_Events extends Display_Recurring_Events{
   public void display(int year,int month){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        userEventsFound = false;
        for (Events ee : events) {
            Date eventDate = ee.getDate();
            if (isDateInMonth(eventDate, year, month)) {
                System.out.println("Your event \n"+ee.getTitle()+ ": " + dateFormat.format(eventDate) );
                userEventsFound = true;
            }
        }
        if (!userEventsFound) {
            System.out.println("No user-set events to display for this month.");
        }
    }
}
class User_Interface extends Thread {

    Display_calendar d=new Display_calendar();
    Manage_Events m=new Manage_Events();

    Manage_Events m1=new Display_Recurring_Events();
    Manage_Events m2=new Display_UserSet_Events();
//    Display_Recurring_Events m1=new Display_Recurring_Events();
//    Display_UserSet_Events m2=new Display_UserSet_Events();
    public void run() {
        main_page();
    }

    public void main_page(){

        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.println("|         CALENDAR         |");
        System.out.println("----------------------------\n\n");
        int choice;
        do{
            System.out.println("1. See Calendar for a particular month of particular year");
            System.out.println("2. Set your own event ");
            System.out.println("3. See Events in a particular month of particular year ");
            System.out.println("4. Search for an Event by name and year");
            System.out.println("5. Delete an Event by name and year");
            System.out.println("6. Exit");
            System.out.println("Choose an option:");
            choice = scan.nextInt();
                    switch (choice) {
                        case 1: {
                            d.display_calendar();
                         System.out.println("Press any key to continue : ");
                         scan.next();

                            break;
                        }
                        case 2: {
                            String title;
                            Date date = null;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            System.out.println("Enter title for the event: ");
                            scan.nextLine();
                            title = scan.nextLine();
                            while (date == null) {
                                System.out.println("Enter date for the event (yyyy-MM-dd):");
                                String input = scan.nextLine();

                                try {
                                    date = dateFormat.parse(input);
                                    //System.out.println("You entered: " + dateFormat.format(date));
                                } catch (ParseException e) {
                                    System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                                }
                            }
                            m.set_event(title, date);
                            System.out.println("Press any key to continue : ");
                            scan.next();
                            break;
                        }
                        case 3: {
                            System.out.println("Enter year: ");
                            int year = scan.nextInt();
                            System.out.println("Enter month: ");
                            int month = scan.nextInt();
                            m1.display(year, month);
                            m2.display(year, month);
                            System.out.println("Press any key to continue : ");
                            scan.next();

                            break;
                        }
                        case 4: {
                            m.search_event();
                            System.out.println("Press any key to continue : ");
                            scan.next();
                            break;
                        }
                        case 5: {
                            m.delete_event();
                            System.out.println("Press any key to continue : ");
                            scan.next();
                            break;
                        }
                        case 6: {
                            System.exit(0);
                            break;
                        }
                        default: {
                            System.out.println("Invalid option. Please try again.");
                            System.out.println("Press any key to continue : ");
                            scan.next();
                            break;
                        }
                    }
        }while(choice!=6);
    }
}
public class MainCalendar {
    public static void main(String[] args) {

        User_Interface user=new User_Interface();
        user.start();
    }
}
