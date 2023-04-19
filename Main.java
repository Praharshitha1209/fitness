import java.util.*;

class Lesson {
    private String type;
    private String day;
    private int time;
    private int capacity;
    private int price;
    private List<String> customers;
    private double rating;
    private String status;

    public Lesson(String type, String day, int time, int price) {
        this.type = type;
        this.day = day;
        this.time = time;
        this.price = price;
        this.capacity = 5;
        this.customers = new ArrayList<>();
        this.rating = 0.0;
        this.status = "booked";
    }

    public String getType() {
        return type;
    }

    public String getDay() {
        return day;
    }

    public int getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public boolean isFull() {
        return customers.size() >= capacity;
    }

    public boolean addCustomer(String customer) {
        if (isFull()) return false;
        customers.add(customer);
        return true;
    }

    public boolean removeCustomer(String customer) {
        return customers.remove(customer);
    }

    public List<String> getCustomers() {
        return new ArrayList<>(customers);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addReview(String review) {
        // add the review to the lesson
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumCustomers() {
        return customers.size();
    }

    public int getCapacity() {
        return capacity;
    }
}


class Booking {
    private static int count = 0;
    private int id;
    private Lesson lesson;
    private String customer;

    public Booking(Lesson lesson, String customer) {
        this.id = ++count;
        this.lesson = lesson;
        this.customer = customer;
        this.lesson.addCustomer(customer);
    }

    public int getId() {
        return id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getCustomer() {
        return customer;
    }

    public void setLesson(Lesson lesson) {
        this.lesson.removeCustomer(customer);
        this.lesson = lesson;
        this.lesson.addCustomer(customer);
    }

    public void cancel() {
        this.lesson.removeCustomer(customer);
    }
}

class WeekendFitnessClub {
    private static Map<String, List<Lesson>> timetable = new HashMap<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeTimetable();
        while (true) {
            System.out.println("Welcome to the Weekend Fitness Club!");
            System.out.println("Please select an option:");
            System.out.println("1. Lesson Booking");
            System.out.println("2. Change/cancel a booking");
            System.out.println("3. Attend a lesson");
            System.out.println("4. Monthly lesson report");
            System.out.println("5. Monthly champion fitness type report");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bookLesson();
                    break;
                case 2:
                    changeCancelBooking();
                    break;
                case 3:
                    attendLesson();
                    break;
                case 4:
                    monthlyLessonReport();
                    break;
                case 5:
                    monthlyChampionReport();
                    break;
                case 6:
                    System.out.println("Thank you for using the Weekend Fitness Club software!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeTimetable() {
        List<Lesson> mondayLessons = List.of(
                new Lesson("Yoga", "Monday", 8, 20),
                new Lesson("Pilates", "Monday", 10, 25),
                new Lesson("Spinning", "Monday", 12, 30),
                new Lesson("Zumba", "Monday", 14, 15),
                new Lesson("Boxing", "Monday", 16, 35)
        );
        timetable.put("Monday", mondayLessons);
        List<Lesson> tuesdayLessons = List.of(
                new Lesson("Yoga", "Tuesday", 8, 20),
                new Lesson("Pilates", "Tuesday", 10, 25),
                new Lesson("Spinning", "Tuesday", 12, 30),
                new Lesson("Zumba", "Tuesday", 14, 15),
                new Lesson("Boxing", "Tuesday", 16, 35)
        );
        timetable.put("Tuesday", tuesdayLessons);

        List<Lesson> wednesdayLessons = List.of(
                new Lesson("Yoga", "Wednesday", 8, 20),
                new Lesson("Pilates", "Wednesday", 10, 25),
                new Lesson("Spinning", "Wednesday", 12, 30),
                new Lesson("Zumba", "Wednesday", 14, 15),
                new Lesson("Boxing", "Wednesday", 16, 35)
        );
        timetable.put("Wednesday", wednesdayLessons);

        List<Lesson> thursdayLessons = List.of(
                new Lesson("Yoga", "Thursday", 8, 20),
                new Lesson("Pilates", "Thursday", 10, 25),
                new Lesson("Spinning", "Thursday", 12, 30),
                new Lesson("Zumba", "Thursday", 14, 15),
                new Lesson("Boxing", "Thursday", 16, 35)
        );
        timetable.put("Thursday", thursdayLessons);

        List<Lesson> fridayLessons = List.of(
                new Lesson("Yoga", "Friday", 8, 20),
                new Lesson("Pilates", "Friday", 10, 25),
                new Lesson("Spinning", "Friday", 12, 30),
                new Lesson("Zumba", "Friday", 14, 15),
                new Lesson("Boxing", "Friday", 16, 35)
        );
        timetable.put("Friday", fridayLessons);

        List<Lesson> saturdayLessons = List.of(
                new Lesson("Yoga", "Saturday", 9, 20),
                new Lesson("Pilates", "Saturday", 11, 25),
                new Lesson("Spinning", "Saturday", 13, 30),
                new Lesson("Zumba", "Saturday", 15, 15),
                new Lesson("Boxing", "Saturday", 17, 35)
        );
        timetable.put("Saturday", saturdayLessons);

        List<Lesson> sundayLessons = List.of(
                new Lesson("Yoga", "Sunday", 9, 20),
                new Lesson("Pilates", "Sunday", 11, 25),
                new Lesson("Spinning", "Sunday", 13, 30),
                new Lesson("Zumba", "Sunday", 15, 15),
                new Lesson("Boxing", "Sunday", 17, 35)
        );
        timetable.put("Sunday", sundayLessons);
    }

    private static void bookLesson() {
        System.out.println("Please enter the day of the lesson (Monday to Friday):");
        String day = scanner.next();
        List<Lesson> lessons = timetable.get(day);
        if (lessons == null) {
            System.out.println("Invalid day. Please try again.");
            return;
        }
        System.out.println("Please enter the time of the lesson (8, 10, 12, 14, 16):");
        int time = scanner.nextInt();
        Lesson lesson = null;
        for (Lesson l : lessons) {
            if (l.getTime() == time) {
                lesson = l;
                break;
            }
        }
        if (lesson == null) {
            System.out.println("Invalid time. Please try again.");
            return;
        }
        if (lesson.isFull()) {
            System.out.println("The lesson is already full. Please try another time or day.");
            return;
        }
        System.out.println("Please enter your name:");
        String customer = scanner.next();
        Booking booking = new Booking(lesson, customer);
        bookings.add(booking);
        System.out.println("Booking successful. Your booking ID is " + booking.getId() + ".");
    }

    private static void changeCancelBooking() {
        System.out.println("Please enter your booking ID:");
        int id = scanner.nextInt();
        Booking booking = null;
        for (Booking b : bookings) {
            if (b.getId() == id) {
                booking = b;
                break;
            }
        }
        if (booking == null) {
            System.out.println("Invalid booking ID. Please try again.");
            return;
        }
        System.out.println("Please select an option:");
        System.out.println("1. Change lesson");
        System.out.println("2. Cancel booking");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                changeLesson(booking);
                break;
            case 2:
                cancelBooking(booking);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void changeLesson(Booking booking) {
        System.out.println("Please enter the day of the new lesson (Monday to Friday):");
        String day = scanner.next();
        List<Lesson> lessons = timetable.get(day);
        if (lessons == null) {
            System.out.println("Invalid day. Please try again.");
            return;
        }
        System.out.println("Please enter the time of the new lesson (8, 10, 12, 14, 16):");
        int time = scanner.nextInt();
        Lesson lesson = null;
        for (Lesson l : lessons) {
            if (l.getTime() == time) {
                lesson = l;
                break;
            }
        }
        if (lesson == null) {
            System.out.println("Invalid time. Please try again.");
            return;
        }
        if (lesson.isFull()) {
            System.out.println("The lesson is already full. Please try another time or day.");
            return;
        }
        booking.getLesson().removeCustomer(booking.getCustomer());
        booking.setLesson(lesson);
        lesson.addCustomer(booking.getCustomer());
        System.out.println("Lesson changed successfully.");
    }

    private static void cancelBooking(Booking booking) {
        booking.getLesson().removeCustomer(booking.getCustomer());
        bookings.remove(booking);
        System.out.println("Booking cancelled successfully.");
    }

    private static void attendLesson() {
        System.out.println("Please enter your name:");
        String customer = scanner.next();
        boolean found = false;
        for (Booking booking : bookings) {
            if (booking.getCustomer().equals(customer)) {
                Lesson lesson = booking.getLesson();
                if (lesson.getCustomers().contains(customer)) {
                    System.out.println("You have a booking for the following lesson:");
                    System.out.println("ID: " + booking.getId());
                    System.out.println("Type: " + lesson.getType());
                    System.out.println("Day: " + lesson.getDay());
                    System.out.println("Time: " + lesson.getTime());
                    System.out.println("Price: " + lesson.getPrice());
                    System.out.println("Rating: " + lesson.getRating());
                    System.out.println("Please provide a review (a few words about the lesson):");
                    String review = scanner.next();
                    System.out.println("Please provide a rating (1-5):");
                    int rating;
                    rating = scanner.nextInt();
                    lesson.setRating(rating);
                    found = true;
                    System.out.println("Thank you for attending the lesson and providing a review!");
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("You do not have any bookings.");
        }
    }

    public static void monthlyLessonReport() {
        System.out.println("Monthly Lesson Report");

        for (String day : timetable.keySet()) {
            System.out.println("----" + day + "----");
            List<Lesson> lessons = timetable.get(day);

            for (Lesson lesson : lessons) {
                int numCustomers = lesson.getNumCustomers();
                int capacity = lesson.getCapacity();
                double percentFull = (double) numCustomers / capacity * 100;

                System.out.println("Type: " + lesson.getType());
                System.out.println("Time: " + lesson.getTime());
                System.out.println("Number of Customers: " + numCustomers);
                System.out.println("Capacity: " + capacity);
                System.out.println("Percentage Full: " + percentFull + "%");
                System.out.println();
            }
        }
    }

    public static void monthlyChampionReport() {
        System.out.println("Monthly Champion Fitness Type Report");

        Map<String, List<Double>> ratingsByType = new HashMap<>();

        for (String day : timetable.keySet()) {
            List<Lesson> lessons = timetable.get(day);

            for (Lesson lesson : lessons) {
                String type = lesson.getType();
                double rating = lesson.getRating();

                if (!ratingsByType.containsKey(type)) {
                    ratingsByType.put(type, new ArrayList<>());
                }

                ratingsByType.get(type).add(rating);
            }
        }

        String championType = null;
        double championRating = -1;

        for (String type : ratingsByType.keySet()) {
            List<Double> ratings = ratingsByType.get(type);

            double sum = 0;

            for (Double rating : ratings) {
                sum += rating;
            }

            double averageRating = sum / ratings.size();

            if (averageRating > championRating) {
                championRating = averageRating;
                championType = type;
            }
        }

        System.out.println("The champion fitness type is " + championType + " with an average rating of " + championRating);
    }
}