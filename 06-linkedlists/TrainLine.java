public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor


    /** Formatting */
    private static final int MAX_CHAR_PER_LINE = 80;
    private static final int CHAR_COUNT_IN_LINE = 0;

    private static final String HEADER = "         1         2         3         4         5         6         7         8";
    private static final String SUBHEADER = "12345678901234567890123456789012345678901234567890123456789012345678901234567890";
    private static final String CORNER_SYMBOL = "+";
    private static final String HORIZONTAL_LINK = "--";
    private static final String VERTICAL_BORDER = "|";
    private static final String RIGHT_POINTER = ">";
    private static final String LEFT_POINTER = "<";
    private static final String RIGHT_ARROW = HORIZONTAL_LINK + RIGHT_POINTER;
    private static final String LEFT_ARROW = HORIZONTAL_LINK + LEFT_POINTER;


    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation newStation = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = newStation;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(newStation);
        }
        // The new station becomes the tail station of the line
        this.tail = newStation;
        // Update station count
        this.numberOfStations++;
    } // method add


    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations



    public TrainStation remove(int position) {
        TrainStation removedStation = null;
        if (position >= 1 && position <= this.numberOfStations) {
            // Commence safe operations
            if (position == 1) {
                // Remove head
                removedStation = this.head;
                this.head = this.head.getNext();
            } else {
                // Find the station prior to the one to be removed
                TrainStation cursor = this.head;
                for (int i = 1; i < position-1; i++) {
                    cursor = cursor.getNext();
                }
                // cursor should be at the prior station
                if (cursor.getNext() == this.tail) {
                    this.tail = cursor;
                }
                removedStation = cursor.getNext();
                cursor.setNext(cursor.getNext().getNext());
            }
            this.numberOfStations--;
            removedStation.setNext(null);
        }
        return removedStation;
    }


    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */    
    public void insert(String name, int position) {
        if (position < 0 || position > this.numberOfStations) {
            return;
        }
        // Create the station being inserted
        TrainStation insertedStation = new TrainStation(name);
        // Determine if the station is being inserted at the head, and if so, update it
        if (position == 0) {
            insertedStation.setNext(this.head);
            this.head = insertedStation;
            // Determine if the list was empty and set the tail
            if (this.tail == null) {
                this.tail = insertedStation;
            }
        } else {
            //Traverse the cursor to the station before the specified position
            TrainStation cursor = this.head;
            for (int i = 0; i < position - 1; i++) {
                cursor = cursor.getNext();
            }
            // Insert the new station to the specified position after the cursor
            insertedStation.setNext(cursor.getNext());
            cursor.setNext(insertedStation);
            // Determine if the the station is being isnerted at the tail, and if so, update it
            if (insertedStation.getNext() == null) {
                this.tail = insertedStation;
            }
        }
        // Update station count
        this.numberOfStations++;
    }


    

    public String toString() {
        String result = "";
        TrainStation cursor = this.head;
        boolean right = true;
        int CHAR_COUNT_IN_LINE = 0;
        
        while (cursor != null) {
            String name = cursor.getName();
            String arrow = right ? RIGHT_ARROW : LEFT_ARROW;
            // Move to the next line if the total number of characters is more than 80
            if (CHAR_COUNT_IN_LINE + name.length() + arrow.length() > MAX_CHAR_PER_LINE) {
                result = "\n";
                CHAR_COUNT_IN_LINE = 0;
                right = !right;
            }
            if (right) {
                result += name + arrow;
            } else {
                result = arrow + name + result;
            }
            CHAR_COUNT_IN_LINE += name.length() + arrow.length();
            cursor = cursor.getNext();
        }
        // Remove last arrow
        if (result.length() > 0) {
            int lastArrowLength = right ? RIGHT_ARROW.length() : LEFT_ARROW.length();
            result = result.substring(0, result.length() - lastArrowLength);
        }
        return result.toString();
    }



    public static void printHeader() {
        System.out.println(HEADER);
        System.out.println(SUBHEADER);
    } // method printHeader


    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };

        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }

        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");

        // A random station name
        String randomName = "Oak Park";

        // Test insert method
        redLineSB.insert("Test_Inserted_Station", 3);
        System.out.println("TrainLine after insert:");
        System.out.println(redLineSB.toString());
     
    } // method main
} // class TrainLine
