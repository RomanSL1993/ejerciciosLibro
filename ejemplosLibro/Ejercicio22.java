package ejemplosLibro;
public class Ejercicio22 {

  public static void main(String[] args) {

    int a, b;
    a = 5;
    b = 2;
    try {
      System.out.println(a + "/" + b + "=" + a / b);
      
      b = 0;
      System.out.println(a + "/" + b + "=" + a / b);

      b = 3;
      System.out.println(a + "/" + b + "=" + a / b);
    } catch (ArithmeticException e) {
      System.err.println("Error al dividir: " + a + "/" + b);
    }
  }
}
