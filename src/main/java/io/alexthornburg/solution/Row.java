package io.alexthornburg.solution;

/**
 * User: alexthornburg
 * Date: 4/10/14
 * Time: 11:47 AM
 */
public class Row {
    private String a;
    private String b;
    private String c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String[] rowsAsArray(){
        String[] row = new String[3];
        row[0] = a;
        row[1] = b;
        row[2] = c;
        return row;
    }
}
