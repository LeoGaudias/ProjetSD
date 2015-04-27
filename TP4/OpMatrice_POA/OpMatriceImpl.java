import org.omg.CORBA.*;

public class OpMatriceImpl extends OpMatricePOA
{

  public int[][] multiplicationMatrice(int[][] a, int[][] b)
  {
    int value = 0,i,j,k, tmp = 0;
    int [][] res;

    System.out.println("Matrice a");

    for(i = 0; i < a.length ;++i)
    {
      for(j = 0; j < a[i].length; ++j)
      {
        System.out.print("" + a[i][j] + " ");
      }
      System.out.println("");
    }

    System.out.println("Matrice b");

    for(i = 0; i < b.length ;++i)
    {
      for(j = 0; j < b[i].length; ++j)
      {
        System.out.print("" + b[i][j] + " ");
      }
      System.out.println("");
    }

    System.out.println("");

    if(a.length >= b.length)
    {
      res = new int[a.length][a[0].length];
      for(i = 0; i < a.length; ++i)
      {
        for(k = 0; k < res[i].length; ++k)
        {
          for(j = 0; j < a[i].length; ++j)
          {
            // System.out.println("i : " + i + "; k : " + k + "; j :" + j);
            // System.out.println(a[i][j] + " * " + b[j][k]);
            value += a[i][j] * b[j][k];
          }
          // System.out.println("End");
          // System.out.println("value = " + value);
          res[i][k] = value;
          value = 0;
        }
      }
    }
    else
    {
      res = new int[b.length][b[0].length];
      for(i = 0; i < b.length; ++i)
      {
        for(k = 0; k < res[i].length; ++k)
        {
          for(j = 0; j < b[i].length; ++j)
          {
            // System.out.println("i : " + i + "; k : " + k + "; j :" + j);
            // System.out.println(a[i][j] + " * " + b[j][k]);
            value += b[i][j] * a[j][k];
          }
          // System.out.println("End");
          // System.out.println("value = " + value);
          res[i][k] = value;
          value = 0;
        }
      }
    }
    return res;
  }
}
