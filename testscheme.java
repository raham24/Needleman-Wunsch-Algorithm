import java.io.*;
import java.util.Optional;
import java.lang.Math;

public class testscheme extends absdna {
        
    public testscheme(int n, int m) { super(n,m); }
    public testscheme(String n, String m, boolean arefiles) { super(n,m,arefiles); }

    @Override
    public int score(int i, int k) {
        if ((A.charAt(i)|32) == (B.charAt(k)|32)) return 3; else return -1;
    }// score

    @Override
    public int w(boolean edge) {
        if (edge) return 0; else return -2;
    }// w

    public static void main(String[] args) {

        testscheme test1 = new testscheme("arman.dna", "artyan.dna",true);
        System.out.println("Score for Arman and Artyan");
        test1.align();
        System.out.println("\n");
        testscheme test2 = new testscheme("mother.dna", "artyan.dna",true);
        System.out.println("Score for Mother and Artyan");
        test2.align();
        System.out.println("\n");
        testscheme test3 = new testscheme("arman.dna", "mother.dna",true);
        System.out.println("Score for Arman and Mother");
        test3.align();
        System.out.println("\n");
        testscheme test4 = new testscheme("father.dna", "artyan.dna",true);
        System.out.println("Score for Father and Artyan");
        test4.align();
        System.out.println("\n");
        testscheme test5 = new testscheme("arman.dna", "father.dna",true);
        System.out.println("Score for Arman and Father");
        test5.align();
        System.out.println("\n");
        testscheme test6 = new testscheme("mother.dna", "father.dna",true);
        System.out.println("Score for Mother and Father");
        test6.align(); 
        System.out.println("\n");
    }

}//tesetscheme
