import java.io.*;
import java.util.Optional;
import java.lang.Math;

/// simple scoring scheme, no gap penalty
class scheme1 extends absdna {  //put this in a different file
   @Override
    public int score(int i, int k) {
        if (A.charAt(i) == B.charAt(k)) return 1;
        else return 0;
    } 
    @Override
    public int w(boolean edge) { return 0; }

    // constructors
    public scheme1(int n, int m) { super(n,m); }
    public scheme1(String n, String m, boolean arefiles) {super(n,m,arefiles);}
}// scheme1

class scheme2 extends absdna { // this is from the sample on wikipedia article
   @Override
    public int score(int i, int k) {
        if (A.charAt(i) == B.charAt(k)) return 1;
        else return -1;
    }
    @Override
    public int w(boolean edge) { return -1; }

    // constructors
    public scheme2(int n, int m) { super(n,m); }
    public scheme2(String n, String m, boolean arefiles) {super(n,m,arefiles);}
}// scheme2

class scheme4 extends absdna {
   @Override
    public int score(int i, int k) {
        if (A.charAt(i) == B.charAt(k)) return 2;
        else return 0;
    } 
    @Override
    public int w(boolean edge) { if (edge) return 0; else return -1; }

    public scheme4(int n, int m) { super(n,m); }
    public scheme4(String n, String m, boolean arefiles) {super(n,m,arefiles);}
    // constructors ...
}// scheme4

public class scheme3 extends absdna { // another scheme, with a main
   @Override
    public int score(int i, int k) {
        if (A.charAt(i) == B.charAt(k)) return 2;
        else return 0;
    }
    @Override
    public int w(boolean edge) { return -1; }

    // constructors
    public scheme3(int n, int m) { super(n,m); }
    public scheme3(String n, String m, boolean arefiles) {super(n,m,arefiles);}

    public static void main(String[] args) {
       scheme1 S1 = new scheme1(10,11); 
       scheme2 S2 = new scheme2("GATTACA","GCATGCG",false);
       scheme3 S3 = new scheme3("seq1.dna","seq2.dna",true); //load from files
       scheme2 S4 = new scheme2("ACGCTCA","GCTA",false);
       
       System.out.println("Scheme 1:");
       S1.align();
       
       System.out.println("Scheme 2:");
       S2.align();

       System.out.println("Scheme 3:");
       S3.align();

       System.out.println("Scheme 4:");
       S4.align();
    }//main

}// scheme3