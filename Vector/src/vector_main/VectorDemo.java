package vector_main;

import vector.Vector;

import static vector.Vector.*;

public class VectorDemo {
    public static void main(String[] args) {
        double[] array1 = new double[]{2.0, 3.0, 4.0};
        double[] array2 = new double[]{}; //для проверки исключений
        double[] array4 = new double[]{2.5, 3.5, 4.5};

        Vector vector1 = new Vector(3);
        Vector vector2 = new Vector(array1);
        Vector vector3 = new Vector(vector2);
        Vector vector4 = new Vector(4, array1);
        Vector vector5 = new Vector(2, array1);

        Vector vector6 = add(vector3, vector5);
        Vector vector7 = add(vector5, vector5);
        Vector vector8 = new Vector(array4);
        vector8.multiplyByScalar(2);
        Vector vector9 = new Vector(array4);
        vector9.revert();
        Vector vector10 = subtract(vector5, vector3);
        Vector vector11 = new Vector(array4);
        vector11.subtract(vector2);
        Vector vector12 = new Vector(array4);
        vector12.add(vector2);
        Vector vector13 = new Vector(array1);
        vector13.getLength();
        Vector vector14 = new Vector(array1);
        vector14.setElement(2, 99.0);

        System.out.println("vector1 = " + vector1.toString());
        System.out.println("vector2 = " + vector2.toString());
        System.out.println("vector3 = " + vector3.toString());
        System.out.println("vector4 = " + vector4.toString());
        System.out.println("vector5 = " + vector5.toString());
        System.out.println("element of vector2 by index 1  = " + vector2.getElement(1));
        System.out.println("vector2 equals vector3 = " + vector2.equals(vector3));
        System.out.println("vector3 equals vector4 = " + vector3.equals(vector4));
        System.out.println("vector6 = " + vector6.toString());
        System.out.println("vector7 = " + vector7.toString());
        System.out.println("vector8 = " + vector8.toString());
        System.out.println("vector9 = " + vector9.toString());
        System.out.println("vector10 = " + vector10.toString());
        System.out.println("vector11 = " + vector11.toString());
        System.out.println("vector12 = " + vector12.toString());
        System.out.println("vector13 length = " + vector13.getLength());
        System.out.println("vector14 = " + vector14.toString());
        System.out.println("scalar product of vector2 and vector5 = " + getScalarProduct(vector2, vector5));

        Vector vector15 = new Vector(3);
        vector15.setElement(1, 3);
        System.out.println("3.0 set as element of vector15 by index 1 = " + vector15);
    }
}