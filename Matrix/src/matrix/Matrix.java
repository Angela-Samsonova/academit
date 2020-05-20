package matrix;

import vector.Vector;

public class Matrix {
    private final Vector[] vectorsArray;

    public Matrix(int rows, int columns) {
        vectorsArray = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            vectorsArray[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.vectorsArray);
    }

    public Matrix(double[][] matrixArray) {
        int maxVectorSize = 0;

        for (double[] d : matrixArray) {
            if (d.length > maxVectorSize) {
                maxVectorSize = d.length;
            }
        }

        vectorsArray = new Vector[matrixArray.length];

        for (int i = 0; i < matrixArray.length; i++) {
            if (matrixArray[i].length < maxVectorSize) {
                vectorsArray[i] = new Vector(maxVectorSize, matrixArray[i]);
            } else {
                vectorsArray[i] = new Vector(matrixArray[i]);
            }
        }
    }

    public Matrix(Vector[] vector) {
        int maxVectorSize = 0;

        for (Vector v : vector) {
            if (v.getSize() > maxVectorSize) {
                maxVectorSize = v.getSize();
            }
        }

        vectorsArray = new Vector[vector.length];

        for (int i = 0; i < vector.length; i++) {
            if (vector[i].getSize() < maxVectorSize) {
                vectorsArray[i] = new Vector(maxVectorSize, vector[i].getElements());
            } else {
                vectorsArray[i] = new Vector(vector[i]);
            }
        }
    }

    public int getMatrixRowsCount() {
        return vectorsArray.length;
    }

    public int getMatrixColumnsCount() {
        return vectorsArray[0].getSize();
    }

    public Vector getMatrixRow(int i) {
        return vectorsArray[i];
    }

    public void setMatrixRow(int i, Vector vector) {
        if (i < 0) {
            throw new IllegalArgumentException("index " + i + " must be >= 0");
        }

        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (vector.getSize() != vectorsArray[i].getSize()) {
            throw new IllegalArgumentException("vector size "
                    + vector.getSize()
                    + " must be equal to matrix row size "
                    + vectorsArray[i].getSize());
        }

        vectorsArray[i] = vector;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Vector v : vectorsArray) {
            sb.append(v).append(", ");
        }

        sb.replace(sb.length() - 2, sb.length(), "}");

        return sb.toString();
    }

    public Vector getColumnByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index " + index + " must be >= 0");
        }

        Vector columnVector = new Vector(vectorsArray.length);

        for (int i = 0; i < vectorsArray.length; i++) {
            columnVector.setElement(i, vectorsArray[i].getElement(index));
        }

        return columnVector;
    }

    //транспонирование
    public Matrix transpose() {
        Vector[] transposedMatrix = new Vector[vectorsArray[0].getSize()];

        for (int i = 0; i < vectorsArray[0].getSize(); i++) {
            transposedMatrix[i] = getColumnByIndex(i);
        }

        return new Matrix(transposedMatrix);
    }

    //умножение на скаляр
    public void multiplyByScalar(double x) {
        for (Vector vector : vectorsArray) {
            vector.multiplyByScalar(x);
        }
    }

    //сложение
    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must be not null");
        }

        if (getMatrixColumnsCount() != matrix.getMatrixColumnsCount()) {
            throw new IllegalArgumentException("added matrix columns count "
                    + matrix.getMatrixColumnsCount()
                    + "should match initial matrix columns count " + getMatrixColumnsCount());
        }

        if (getMatrixRowsCount() != matrix.getMatrixRowsCount()) {
            throw new IllegalArgumentException("added matrix rows count "
                    + matrix.getMatrixRowsCount()
                    + "should match initial matrix rows count " + getMatrixRowsCount());
        }

        for (int i = 0; i < vectorsArray.length; i++) {
            vectorsArray[i].add(matrix.vectorsArray[i]);
        }
    }

    // вычитание
    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must be not null");
        }

        if (getMatrixColumnsCount() != matrix.getMatrixColumnsCount()) {
            throw new IllegalArgumentException("added matrix columns count "
                    + matrix.getMatrixColumnsCount()
                    + "should match initial matrix columns count " + getMatrixColumnsCount());
        }

        if (getMatrixRowsCount() != matrix.getMatrixRowsCount()) {
            throw new IllegalArgumentException("added matrix rows count "
                    + matrix.getMatrixRowsCount()
                    + "should match initial matrix rows count " + getMatrixRowsCount());
        }

        for (int i = 0; i < vectorsArray.length; i++) {
            vectorsArray[i].subtract(matrix.vectorsArray[i]);
        }
    }

    //умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (getMatrixColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("matrix columns count "
                    + getMatrixColumnsCount()
                    + " should match vector length " + vector.getSize());
        }

        Vector newVector = new Vector(getMatrixRowsCount());

        for (int i = 0; i < getMatrixRowsCount(); i++) {
            newVector.setElement(i, Vector.getScalarProduct(getMatrixRow(i), vector));
        }

        return newVector;
    }

    // сумма матриц статическая
    public static Matrix addMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix1 must be not null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix2 must be not null");
        }

        if (matrix1.getMatrixColumnsCount() != matrix2.getMatrixColumnsCount()) {
            throw new IllegalArgumentException("matrix1 columns count "
                    + matrix1.getMatrixColumnsCount()
                    + " should match matrix2 columns count " + matrix2.getMatrixColumnsCount());
        }

        if (matrix1.getMatrixRowsCount() != matrix2.getMatrixRowsCount()) {
            throw new IllegalArgumentException("matrix1 rows count "
                    + matrix1.getMatrixRowsCount()
                    + "should match matrix2 rows count " + matrix2.getMatrixRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix subtractMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix1 must be not null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix2 must be not null");
        }

        if (matrix1.getMatrixColumnsCount() != matrix2.getMatrixColumnsCount()) {
            throw new IllegalArgumentException("matrix1 columns count "
                    + matrix1.getMatrixColumnsCount()
                    + " should match matrix2 columns count " + matrix2.getMatrixColumnsCount());
        }

        if (matrix1.getMatrixRowsCount() != matrix2.getMatrixRowsCount()) {
            throw new IllegalArgumentException("matrix1 rows count "
                    + matrix1.getMatrixRowsCount()
                    + "should match matrix2 rows count " + matrix2.getMatrixRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix multiplyMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix1 must be not null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix2 must be not null");
        }

        if (matrix1.getMatrixColumnsCount() != matrix2.getMatrixRowsCount()) {
            throw new IllegalArgumentException("matrix1 columns count "
                    + matrix1.getMatrixColumnsCount()
                    + " should match matrix2 rows count " + matrix2.getMatrixRowsCount());
        }

        double[][] newMatrixArray = new double[matrix1.getMatrixRowsCount()][matrix2.getMatrixColumnsCount()];

        for (int i = 0; i < matrix1.getMatrixRowsCount(); i++) {
            for (int j = 0; j < matrix2.getMatrixColumnsCount(); j++) {
                newMatrixArray[i][j] = Vector.getScalarProduct(matrix1.getMatrixRow(i), matrix2.getColumnByIndex(j));
            }
        }

        return new Matrix(newMatrixArray);
    }
}