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
        if (this.used < this.size) {
            this.array[this.back] = string;
            this.back = (this.back + 1) % this.size;
            this.used++;
            return true;
        }
        return false;
    } // method add

    public String remove() {
        if (this.used > 0) {
            String removed = this.array[this.front];
            this.array[this.front] = null;
            this.front = (this.front + 1) % this.size;
            this.used--;
            return removed;
        }
        return null;
    } // method remove
    
} // class FastQ
