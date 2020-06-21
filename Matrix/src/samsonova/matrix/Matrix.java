package samsonova.matrix;

import vector.Vector;

public class Matrix {
    private Vector[] vectorsMatrix;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("rows count " + rowsCount + "must be > 0");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("columns count " + columnsCount + "must be > 0");
        }

        vectorsMatrix = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            vectorsMatrix[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.vectorsMatrix);
    }

    public Matrix(double[][] matrixArray) {
        int maxVectorSize = 0;

        for (double[] row : matrixArray) {
            if (row.length > maxVectorSize) {
                maxVectorSize = row.length;
            }
        }

        vectorsMatrix = new Vector[matrixArray.length];

        for (int i = 0; i < matrixArray.length; i++) {
            if (matrixArray[i].length < maxVectorSize) {
                vectorsMatrix[i] = new Vector(maxVectorSize, matrixArray[i]);
            } else {
                vectorsMatrix[i] = new Vector(matrixArray[i]);
            }
        }
    }

    public Matrix(Vector[] vectorsArray) {
        int maxVectorSize = 0;

        for (Vector v : vectorsArray) {
            if (v.getSize() > maxVectorSize) {
                maxVectorSize = v.getSize();
            }
        }

        vectorsMatrix = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            if (vectorsArray[i].getSize() < maxVectorSize) {
                vectorsMatrix[i] = new Vector(maxVectorSize, vectorsArray[i].getElements());
            } else {
                vectorsMatrix[i] = new Vector(vectorsArray[i]);
            }
        }
    }

    public int getRowsCount() {
        return vectorsMatrix.length;
    }

    public int getColumnsCount() {
        return vectorsMatrix[0].getSize();
    }

    public Vector getRow(int i) {
        return new Vector(vectorsMatrix[i]);
    }

    public void setRow(int i, Vector vector) {
        if (i < 0 || i > vectorsMatrix[0].getSize()) {
            throw new ArrayIndexOutOfBoundsException("index " + i + " must be >= 0 and <= matrix columns count");
        }

        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (vector.getSize() != vectorsMatrix[i].getSize()) {
            throw new ArrayIndexOutOfBoundsException("vector size " + vector.getSize() + " must be equal to matrix row length "
                    + vectorsMatrix[i].getSize());
        }

        vectorsMatrix[i] = new Vector(vector);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Vector v : vectorsMatrix) {
            sb.append(v).append(", ");
        }

        sb.replace(sb.length() - 2, sb.length(), "}");

        return sb.toString();
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= vectorsMatrix.length) {
            throw new ArrayIndexOutOfBoundsException("index " + index + " must be >= 0 and < matrix rows count "
                    + vectorsMatrix.length);
        }

        Vector columnVector = new Vector(vectorsMatrix.length);

        for (int i = 0; i < vectorsMatrix.length; i++) {
            columnVector.setElement(i, vectorsMatrix[i].getElement(index));
        }

        return columnVector;
    }

    //транспонирование
    public void transpose() {
        Vector[] transposedMatrix = new Vector[vectorsMatrix[0].getSize()];

        for (int i = 0; i < vectorsMatrix[0].getSize(); i++) {
            transposedMatrix[i] = getColumn(i);
        }

        Matrix newMatrix = new Matrix(transposedMatrix);
        vectorsMatrix = newMatrix.vectorsMatrix;
    }

    //умножение на скаляр
    public void multiplyByScalar(double x) {
        for (Vector vector : vectorsMatrix) {
            vector.multiplyByScalar(x);
        }
    }

    //сложение
    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must be not null");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("added matrix columns count " + matrix.getColumnsCount()
                    + "should match initial matrix columns count " + getColumnsCount());
        }

        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("added matrix rows count " + matrix.getRowsCount()
                    + "should match initial matrix rows count " + getRowsCount());
        }

        for (int i = 0; i < vectorsMatrix.length; i++) {
            vectorsMatrix[i].add(matrix.vectorsMatrix[i]);
        }
    }

    // вычитание
    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("matrix must be not null");
        }

        if (getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("added matrix columns count " + matrix.getColumnsCount()
                    + "should match initial matrix columns count " + getColumnsCount());
        }

        if (getRowsCount() != matrix.getRowsCount()) {
            throw new IllegalArgumentException("added matrix rows count " + matrix.getRowsCount()
                    + "should match initial matrix rows count " + getRowsCount());
        }

        for (int i = 0; i < vectorsMatrix.length; i++) {
            vectorsMatrix[i].subtract(matrix.vectorsMatrix[i]);
        }
    }

    //умножение матрицы на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("matrix columns count " + getColumnsCount()
                    + " should match vector length " + vector.getSize());
        }

        Vector newVector = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            newVector.setElement(i, Vector.getScalarProduct(getRow(i), vector));
        }

        return newVector;
    }

    // сумма матриц статическая
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix1 must be not null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix2 must be not null");
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrix1 columns count " + matrix1.getColumnsCount()
                    + " should match matrix2 columns count " + matrix2.getColumnsCount());
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("matrix1 rows count " + matrix1.getRowsCount()
                    + "should match matrix2 rows count " + matrix2.getRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.add(matrix2);

        return newMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix1 must be not null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix2 must be not null");
        }

        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("matrix1 columns count " + matrix1.getColumnsCount()
                    + " should match matrix2 columns count " + matrix2.getColumnsCount());
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("matrix1 rows count " + matrix1.getRowsCount()
                    + "should match matrix2 rows count " + matrix2.getRowsCount());
        }

        Matrix newMatrix = new Matrix(matrix1);
        newMatrix.subtract(matrix2);

        return newMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("matrix1 must be not null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("matrix2 must be not null");
        }

        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("matrix1 columns count " + matrix1.getColumnsCount()
                    + " should match matrix2 rows count " + matrix2.getRowsCount());
        }

        double[][] newMatrixArray = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                for (int k = 0; k < matrix2.getRowsCount(); k++) {
                    newMatrixArray[i][j] += matrix1.vectorsMatrix[i].getElement(k) * matrix2.vectorsMatrix[k].getElement(j);
                }
            }
        }

        return new Matrix(newMatrixArray);
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("The number of rows " + getRowsCount()
                    + " should match the number of columns " + getColumnsCount());
        }

        if (vectorsMatrix.length > 2) {
            int determinant = 0;

            for (int j = 0; j < vectorsMatrix.length; j++) {
                Matrix minore = getMinore(j);

                if (j % 2 == 0) {
                    determinant += vectorsMatrix[0].getElement(j) * minore.getDeterminant();
                } else {
                    determinant -= vectorsMatrix[0].getElement(j) * minore.getDeterminant();
                }
            }

            return determinant;

        } else if (vectorsMatrix.length == 2) {
            return vectorsMatrix[0].getElement(0) * vectorsMatrix[1].getElement(1)
                    - vectorsMatrix[0].getElement(1) * vectorsMatrix[1].getElement(0);
        }

        return vectorsMatrix[0].getElement(0);
    }

    private Matrix getMinore(int column) {
        double[][] newMatrixArray = new double[vectorsMatrix.length - 1][vectorsMatrix.length - 1];

        for (int i = 0; i < vectorsMatrix.length - 1; i++) {
            for (int j = 0; j < vectorsMatrix.length; j++) {
                if (j < column) {
                    newMatrixArray[i][j] = vectorsMatrix[i + 1].getElement(j);
                } else if (j > column) {
                    newMatrixArray[i][j - 1] = vectorsMatrix[i + 1].getElement(j);
                }
            }
        }

        return new Matrix(newMatrixArray);
    }
}