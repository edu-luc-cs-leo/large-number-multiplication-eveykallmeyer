public class FastQ {

    private String[] array;
    private int size;
    private int used;
    private int front;
    private int back;

    private static final int DEFAULT_SIZE = 4;

    /** Full constructor */
    public FastQ(int size) {
        if (size <1){
            size = DEFAULT_SIZE;
        }
        this.size = size;
        this.array = new String[this.size];
        this.used = 0;
        this.front = 0;
        this.back = 0;
    } // full constructor

    /** Default constructor */
    public FastQ() {
        this(DEFAULT_SIZE);
    } // default constructor

    public boolean add(String string) {
        boolean success = true;
        if (this.used < this.size) { // checks if the size is less than the amount of elements in the array (if there is space in the queue)
            this.array[this.back] = string; // new string is added to where the back pointer currently is
            this.back = (this.back + 1) % this.size; // move the pointer to the next position
            this.used++; // increase the number of elements in the array (used)
            return success; // return if string is successfully added
        }
        return false; // return if there is no room to add a new element (used /< size)
    } // method add

    public String remove() {
        if (this.used > 0) { // checks if the queue is empty
            String removed = this.array[this.front]; // string is removed from the position that the front pointer is at
            this.array[this.front] = null; // position of the removed string is set to null
            this.front = (this.front + 1) % this.size; // moves the pointer to the next position
            this.used--; // decrease the number of elements in the array (used)
            return removed; // return removed string
        }
        return null; // return if the queue is empty
    } // method remove
    
} // class FastQ
