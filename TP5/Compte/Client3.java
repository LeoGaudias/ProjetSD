import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.io.*;

// Exemple d'utilisation : java Client localhost 3000
public class Client3
{
  public static void printContext( NamingContext nc, String parent )
  {
    try
    {
      final int batchSize = 1000;
      BindingListHolder bList = new BindingListHolder();
      BindingIteratorHolder bIterator = new BindingIteratorHolder();

      nc.list(batchSize, bList, bIterator);

      for (int i = 0; i < bList.value.length; ++i)
      {
        NameComponent[] name ={bList.value[i].binding_name[0]};
        if (bList.value[i].binding_type == BindingType.ncontext)
        {
          NamingContext context = NamingContextHelper.narrow(nc.resolve( name ) );
          printContext(context, parent + name[0].id + "." );
        }
        else
        {
          System.out.println(parent + name[0].id);
        }
      }
    }
    catch (Exception e)
    {
      System.out.println("ERROR : " + e);
    }
  }

  public static void main(String args[])
  {
    try
    {
      if (args.length != 2)
      {
        System.out.println("Usage : java Client <machineServeurDeNoms> <port>");
        return;
      }

      String [] argv = {"-ORBInitialHost", args[0], "-ORBInitialPort", args[1]};
      ORB orb = ORB.init(argv, null);

      org.omg.CORBA.Object objRef = null;
      objRef = orb.resolve_initial_references("NameService");
      NamingContext rootCtx = NamingContextHelper.narrow(objRef);

      printContext(rootCtx, "" );
    }
    catch (org.omg.CORBA.SystemException ex)
    {
      ex.printStackTrace();
    }
    catch (org.omg.CORBA.UserException ex)
    {
      ex.printStackTrace();
    }
  }
}
