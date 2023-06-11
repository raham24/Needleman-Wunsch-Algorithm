import java.io.*;
import java.util.Optional;
import java.lang.Math;

public class absdna {

	protected String A;
	protected String B;
    protected int[][] matrix;
    protected int AS;

    // first constructor, creates random strings for dna
	public absdna(int n, int m) { 
        A = " "+random_dna(n); 
        B = " "+random_dna(m);
    } // constructor

    // second constructor, takes dna or files as input
	public absdna(String a, String b, boolean arefiles) {
		if (!arefiles){
			A = " "+a;
			B = " "+b;
		}
		else {
            Optional<String> tempA = load_dna(a);
            Optional<String> tempB = load_dna(b);
            if (tempA.isPresent()) {
                A = " "+tempA.get();    
            }
			if (tempB.isPresent()) {
                B = " "+tempB.get();
            } 
		}

	} //second constructor

    // if no scheme declared will use the basic scoring scheme
    // match = +1, gap penalty = 0
	public int score(int i, int k) {
        if (A.charAt(i) == B.charAt(k)) return 1;
        else
            return 0;
	}//score


    public int w(boolean edge) {
        return 0;
    }//W function for returning gap penalty

    
    public void align() {

        // first rows are relative to whatever the gap penalty is
        
        initialize();
        fill();
        traceback(matrix);
        //printmatrix(A,B,matrix); //for testing only!!!!
    }//align

    public void initialize() {
     // initializes the matrix that was declared
        matrix = new int[(A.length())][(B.length())];
        int d = w(false);
        for (int i=0; i<(A.length()); i++) {
            if (i == A.length()-1) {d = w(true);}
            matrix[i][0] = d*i;
        }
        for (int j=0; j<(B.length()); j++) {
            if (j == B.length()-1) {d = w(true);}
            matrix[0][j] = d*j;
        }
    }//initialize

    public void fill() {
     // fills the matrix by using the score and w() function
        boolean e = false;
        for (int i=1; i<(A.length()); i++) {
            for (int j=1; j<(B.length()); j++) {
                if (j == 1 || i == 1 || i == A.length()-1 || j == B.length()-1) {
                    e = true;
                }
                else
                    e = false;
                int match = matrix[i-1][j-1] + score(i,j);
                int delete = matrix[i-1][j] + w(e);
                int insert = matrix[i][j-1] + w(e);
                matrix[i][j] = Math.max(Math.max(delete,insert),match);

            }//inner loop
        }//outer loop
        AS = matrix[A.length()-1][B.length()-1];
    }//fill


    public void traceback(int[][] M) {
     // tracesback the matrix to find the matching and insert gaps
        
        String aligA = "";
        String aligB = "";
        int i = A.length()-1;
        int j = B.length()-1;
        int dashesl = Math.max(A.length(),B.length());
        String dashes = "";
        boolean e;

        while (i>0 && j>0) {
            if (j == 1 || i == 1 || i == A.length()-1 || j == B.length()-1) {
                e = true;
            }
            else
                e = false;
            if (i>0 && j>0 && matrix[i][j]==matrix[i-1][j-1]+ score(i,j)) {
                aligA = A.charAt(i) + aligA;
                aligB = B.charAt(j) + aligB;
                dashes = "|" + dashes;
                i--;
                j--;
            }
            else
                if (i>0 && matrix[i][j] == matrix [i-1][j] + w(e)) {
                    aligA = A.charAt(i) + aligA ;
                    aligB = "-" + aligB;
                    dashes = " " + dashes;
                    i--;
                }
                else {
                    aligA = "-" + aligA;
                    aligB = B.charAt(j) + aligB;
                    dashes = " " + dashes;
                    j--;
                }

        }//while

        

        // System.out.printf(aligA + "\n");
        // System.out.printf(dashes + "\n");
        // System.out.printf(aligB + "\n");
        System.out.printf("Alignment Score: "+ AS + "\n");
        // System.out.printf("\n");


    }//traceback


	public static String random_dna(int n) { // random dna sequence of length n
        if (n<1) return "";
        char[] C = new char[n];
        char[] DNA = {'A','C','G','T'};
        for(int i=0;i<n;i++)
            C[i] = DNA[ (int)(Math.random()*4) ];
        return new String(C); // converts char[] to String
    }//random_dna

    //load dna from file    
    public static Optional<String> load_dna(String filename) { 
        Optional<String> answer = Optional.empty();
	try {
	   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
           answer = Optional.of(br.readLine());
           br.close();
        } // IOExceptions MUST be caught
        catch (IOException ie) {}
	return answer;
    }//load_dna

    // for debugging only!  only call on small strings
    public static void printmatrix(String A, String B, int[][] M) {
        if (A==null || B==null || M==null || M[0]==null) return;
        if (A.length() != M.length || B.length() != M[0].length) return;
        int rows = A.length();
        int cols = B.length();
        System.out.print("    ");
        for(int i=0;i<cols;i++) System.out.printf(" %2s ",B.charAt(i));
        System.out.println();
        for(int i=0;i<rows;i++) {
            System.out.print("   "+A.charAt(i));
            for(int k=0;k<cols;k++) {
                System.out.printf(" %2d ",M[i][k]);
            }//for k
            System.out.println();
        }//for i
    }//printmatrix

    

    // my main for testing
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

}//absdna class