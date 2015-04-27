import java.io.*;

import org.omg.CORBA.*;

public class Client
{
  public static void main( String args[] )  {

    // typedef sequence <Vector> Matrice; --> ça défini seulement un tableau à 2 dimensions !
    // faut le savoir put1 !
    int[][] a = { {1, 0, 0}, {0, 2, 0}, {0, 0, 3}, {0, 0, 4} };
    int[][] b = { {1, 2, 3}, {1, 2, 3}, {1, 2, 3} };
    int[][] res;

    // Vector<Integer>[] res;
    // Vector<Integer>[] a = new Vector[4];
    // Vector<Integer>[] b = new Vector[3];
    //
    // a[0] = new Vector<Integer>();
    // a[0].add(1);
    // a[0].add(0);
    // a[0].add(0);
    //
    // a[1] = new Vector<Integer>();
    // a[1].add(0);
    // a[1].add(2);
    // a[1].add(0);
    //
    // a[2] = new Vector<Integer>();
    // a[2].add(0);
    // a[2].add(0);
    // a[2].add(3);
    //
    // a[3] = new Vector<Integer>();
    // a[3].add(0);
    // a[3].add(0);
    // a[3].add(4);
    //
    // ArrayList<Integer> seq = new ArrayList<Integer>();
    // seq.add(1);
    // seq.add(2);
    // seq.add(3);
    //
    // b[0] = new Vector<Integer>();
    // b[0].addAll(seq);
    // b[1] = new Vector<Integer>();
    // b[1].addAll(seq);
    // b[2] = new Vector<Integer>();
    // b[2].addAll(seq);

    // if( args.length < 1 )
    // {
    //   System.out.println( "Usage: java Client <ior>" );
    //   System.exit(1);
    // }




    // initialiser l'ORB.
    try {
      ORB orb = ORB.init( args, null );
      System.out.println( "0) ORB initialise'");

      // String ior = args[0];
      String ior = "";

      try
      {
        FileReader file = new FileReader(iorfile.value);
        BufferedReader in = new BufferedReader(file);
        ior = in.readLine();
        file.close();
      }
      catch(IOException ex)
      {
        System.out.println (ex.toString());
      }

      System.out.println( "1) IOR lue : " + ior );

      org.omg.CORBA.Object obj = orb.string_to_object(ior);
      OpMatrice service = OpMatriceHelper.narrow(obj);
      // Icarre service = IcarreHelper.narrow(obj);
      System.out.println("2) Reference obtenue a partir de l'IOR");

      //nombre = Integer.parseInt(args[1]);
      //System.out.println("3) Nombre lu sur la ligne de commande : " + nombre);

      res = service.multiplicationMatrice(a,b);
      // res = service.carre(nombre);
      for(int i = 0; i < res.length ;++i)
      {
        for(int j = 0; j < res[i].length; ++j)
        {
          System.out.print("" + res[i][j] + " ");
        }
        System.out.println("");
      }
      //System.out.println("4) Le serveur a trouve' un carre de : " + res);
    }
    catch( org.omg.CORBA.SystemException ex )
    {
      System.err.println( "Erreur !!" );
      ex.printStackTrace();
    }
  }
}
