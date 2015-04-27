import java.io.*;

import org.omg.CORBA.*;

public class Client
{
  public static void main( String args[] )  {

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
      Annuaire service = AnnuaireHelper.narrow(obj);
      //OpMatrice service = OpMatriceHelper.narrow(obj);
      // Icarre service = IcarreHelper.narrow(obj);
      System.out.println("2) Reference obtenue a partir de l'IOR");

      //nombre = Integer.parseInt(args[1]);
      //System.out.println("3) Nombre lu sur la ligne de commande : " + nombre);

      StringHolder num = new StringHolder();
      StringHolder liste = new StringHolder();

      service.chercheNom("milou",num);
      System.out.println("num de milou -> " + num.value);

      service.listerNoms(liste);
      System.out.println(liste.value);
    }
    catch( org.omg.CORBA.SystemException ex )
    {
      System.err.println( "Erreur !!" );
      ex.printStackTrace();
    }
  }
}
