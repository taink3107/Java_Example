import java.io.*;

public class Demo_FilterInputStream {

    FilterInputStream inputStream;
    File file;

    public Demo_FilterInputStream(File fileX) {
        try {
            file = fileX;
            FileInputStream fileInput = new FileInputStream(fileX);
            inputStream = new BufferedInputStream(fileInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method intAvailable():
     * Returns an estimate of the number of bytes that can be read (or skipped over)
     * from this input stream without blocking by the next caller of a method for this input stream.
     * */
    public void exampleAvailableMethod() {
        try (InputStream inputStream1 = new FileInputStream(file)) {
            int temp = inputStream1.available();
            System.out.println("estimate of the number of bytes can read is: " + temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method int read()
     * Reads the next byte of data from this input stream.
     * The value byte is returned as an int in the range 0 to 255.
     * If no byte is available because the end of the stream has been reached, the value -1 is returned.
     * This method blocks until input data is available, the end of the stream is detected, or an exception is thrown.
     * This method simply performs in.read() and returns the result.
     * */
    public void exReadMethod_1() {
        int k = 0;
        try {
            while ((k = inputStream.read()) != -1) { // in the end. byte is: -1. This code is right way.
                //  k = inputStream.read(). this code make last byte is: -1;
                System.out.println("current value is: " + (char)k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method int read(byte[] b)
     * Reads up to byte.length bytes of data from this input stream into an array of bytes.
     * This method blocks until some input is available.
     * This method simply performs the call read(b, 0, b.length) and returns the result.
     * It is important that it does not do in.read(b) instead;
     * certain subclasses of FilterInputStream depend on the implementation strategy actually used.
     * */
    public void exReadMethod_2() {
        int k = 0;
        try {
            byte[] bytes = new byte[8]; // mỗi lần đọc sẽ đọc 10 ký tự 1 lần.
            while ((k = inputStream.read(bytes)) != -1) {
                System.out.println("current value is: " + k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method int read(byte[] b,int offset,int length)
     * Reads up to len bytes of data from this input stream into an array of bytes.
     * If len is not zero, the method blocks until some input is available; otherwise, no bytes are read and 0 is returned.
     * This method simply performs in.read(b, off, len) and returns the result.
     * */
    public void exReadMethod_3() {
        int k = 0;
        try {
            byte[] bytes = new byte[10];
            while ((k = inputStream.read(bytes, 2, 8)) != -1) {
                System.out.println("current value is: " + k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method long skip(long n)
     * Skips over and discards n bytes of data from the input stream.
     * The skip method may, for a variety of reasons, end up skipping over some smaller number of bytes, possibly 0.
     * The actual number of bytes skipped is returned.
     * This method simply performs in.skip(n).
     * */
    public void exSkipMethod() {
        int k = 0;
        char c;
        int count =0;
        try {
            while ((k = inputStream.read()) != -1) {
                // converts integer to character
                c = (char) k;
                // skips 3 bytes
                inputStream.skip(3);
                // print
                System.out.println("Character read: " + c);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //OutputStream
    }

    public static void main(String[] args) {
        File file = new File("Text.txt");  //change String path.
        if (file.exists()) {
            Demo_FilterInputStream inputStream = new Demo_FilterInputStream(file);

            //inputStream.exampleAvailableMethod();
            // inputStream.exReadMethod_1();
            //System.out.println("Bài 2");
            //inputStream.exReadMethod_3();
            inputStream.exSkipMethod();
        }
    }
}
