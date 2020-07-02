package samsonova.matrix_main;

import samsonova.matrix.Matrix;
import vector.Vector;

public class MatrixDemo {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 4);
        System.out.println("matrix1 = " + matrix1.toString());

        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        matrix1.setRow(0, vector);
        System.out.println("matrix1 = " + matrix1.toString());

        double[][] matrixArray1 = new double[][]{
                {1, 2, 3},
                {3, 4},
                {4, 5, 6, 7}};

        double[][] matrixArray2 = new double[][]{
                {1, 2, 3},
                {3, 4},
                {4, 5, 6, 7},
                {8, 9}};

        Matrix matrix11 = new Matrix(matrixArray2);
        System.out.println("matrix11 = " + matrix11.toString());

        Matrix matrix2 = new Matrix(matrixArray1);
        System.out.println("matrix2 = " + matrix2.toString());

        Matrix matrix3 = new Matrix(matrix2);
        System.out.println("matrix3 = " + matrix3.toString());

        Vector[] vector1 = new Vector[]{
                new Vector(new double[]{11, 12}),
                new Vector(new double[]{11, 12, 13}),
                new Vector(new double[]{15})};

        Matrix matrix4 = new Matrix(vector1);
        System.out.println("matrix4 = " + matrix4.toString());

        System.out.println("matrix4.getColumnVector(1) = " + matrix4.getColumn(1).toString());

        Matrix matrix5 = new Matrix(vector1);
        System.out.println("matrix5 =  " + matrix5.toString());
        matrix5.transpose();
        System.out.println("matrix5 after transpose = " + matrix5.toString());

        Matrix matrix6 = new Matrix(matrix4);
        matrix6.multiplyByScalar(2);
        System.out.println("matrix6 = matrix4; matrix6.multiplyByScalar(2) = " + matrix6.toString());

        Matrix matrix7 = new Matrix(matrix6);
        System.out.println("matrix7 = matrix6 = " + matrix7.toString());
        matrix7.add(matrix6);
        System.out.println("matrix7.add(matrix6),  matrix7 = " + matrix7.toString());

        Matrix matrix8 = new Matrix(matrix7);
        System.out.println("matrix8 = matrix7 = " + matrix8.toString());
        matrix8.subtract(matrix5);
        System.out.println("matrix8.subtract(matrix5), matrix8 = ) = " + matrix8.toString());

        Vector[] vector2 = new Vector[]{
                new Vector(new double[]{11, 12}),
                new Vector(new double[]{11, 12, 13}),
                new Vector(new double[]{15})};

        Matrix matrix9 = new Matrix(vector2);

        Vector[] vector3 = new Vector[]{
                new Vector(new double[]{10, 11, 12}),
                new Vector(new double[]{11, 12, 13}),
                new Vector(new double[]{15})};

        Matrix matrix10 = new Matrix(vector3);

        Matrix m11 = Matrix.getSum(matrix9, matrix10);
        System.out.println("m11 = getSum(matrix9, matrix10) = " + m11.toString());

        System.out.println(matrix9.toString());
        System.out.println(matrix10.toString());

        Matrix m12 = Matrix.getDifference(matrix9, matrix10);
        System.out.println("m12 = getDifference(matrix9, matrix10) = " + m12.toString());

        Matrix m13 = Matrix.getProduct(matrix2, matrix11);
        System.out.println("m13 = getProduct(matrix2, matrix11) = " + m13.toString());

        Vector vector5 = m13.multiplyByVector(matrix1.getRow(0));
        System.out.println("vector5 = m13.multiplyByVector(matrix1.getMatrixRow(0) = " + vector5);

        System.out.println("m11.getDeterminant() = " + m11.getDeterminant());
    }
}