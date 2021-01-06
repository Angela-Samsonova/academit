package samsonova.matrix;

import vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("rows count " + rowsCount + "must be > 0");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("columns count " + columnsCount + "must be > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] matrixArray) {
        if (matrixArray.length == 0 || matrixArray[0].length == 0) {
            throw new IllegalArgumentException("Array size must be not 0");
        }

        int maxVectorSize = 0;

        for (double[] row : matrixArray) {
            if (row.length > maxVectorSize) {
                maxVectorSize = row.length;
            }
        }

        rows = new Vector[matrixArray.length];

        for (int i = 0; i < matrixArray.length; i++) {
            if (matrixArray[i].length < maxVectorSize) {
                rows[i] = new Vector(maxVectorSize, matrixArray[i]);
            } else {
                rows[i] = new Vector(matrixArray[i]);
            }
        }
    }

    public Matrix(Vector[] vectorsArray) {
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException("Vector size must be not null");
        }

        int maxVectorSize = 0;

        for (Vector v : vectorsArray) {
            if (v.getSize() > maxVectorSize) {
                maxVectorSize = v.getSize();
            }
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            if (vectorsArray[i].getSize() < maxVectorSize) {
                rows[i] = new Vector(maxVectorSize, vectorsArray[i].getElements());
            } else {
                rows[i] = new Vector(vectorsArray[i]);
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int i) {
        if (i < 0 || i >= rows.length) {
            throw new IndexOutOfBoundsException("index " + i + " must be >= 0 and < matrix rows count");
        }

        return new Vector(rows[i]);
    }

    public void setRow(int i, Vector vector) {
        if (i < 0 || i >= rows.length) {
            throw new IndexOutOfBoundsException("index " + i + " must be >= 0 and < matrix rows count");
        }

        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (vector.getSize() != rows[i].getSize()) {
            throw new IllegalArgumentException("vector size " + vector.getSize() + " must be equal to matrix row length "
                    + rows[i].getSize());
        }

        rows[i] = new Vector(vector);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (Vector v : rows) {
            sb.append(v).append(", ");
        }

        sb.replace(sb.length() - 2, sb.length(), "}");

        return sb.toString();
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("index " + index + " must be >= 0 and < matrix columns count "
                    + rows.length);
        }

        Vector columnVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            columnVector.setElement(i, rows[i].getElement(index));
        }

        return columnVector;
    }

    //транспонирование
    public void transpose() {
        Vector[] transposedMatrixRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedMatrixRows[i] = getColumn(i);
        }

        rows = transposedMatrixRows;
    }

    //умножение на скаляр
    public void multiplyByScalar(double x) {
        for (Vector vector : rows) {
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

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
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

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
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
            newVector.setElement(i, Vector.getScalarProduct(rows[i], vector));
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
                    newMatrixArray[i][j] += matrix1.rows[i].getElement(k) * matrix2.rows[k].getElement(j);
                }
            }
        }

        return new Matrix(newMatrixArray);
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new UnsupportedOperationException("The number of rows " + getRowsCount()
                    + " should match the number of columns " + getColumnsCount());
        }

        if (rows.length == 1) {
            return rows[0].getElement(0);
        } else if (rows.length == 2) {
            return rows[0].getElement(0) * rows[1].getElement(1)
                    - rows[0].getElement(1) * rows[1].getElement(0);
        }

        int determinant = 0;

        for (int j = 0; j < rows.length; j++) {
            Matrix minor = getMinor(j);

            if (j % 2 == 0) {
                determinant += rows[0].getElement(j) * minor.getDeterminant();
            } else {
                determinant -= rows[0].getElement(j) * minor.getDeterminant();
            }
        }

        return determinant;
    }

    private Matrix getMinor(int column) {
        double[][] newMatrixArray = new double[rows.length - 1][rows.length - 1];

        for (int i = 0; i < rows.length - 1; i++) {
            for (int j = 0; j < rows.length; j++) {
                if (j < column) {
                    newMatrixArray[i][j] = rows[i + 1].getElement(j);
                } else if (j > column) {
                    newMatrixArray[i][j - 1] = rows[i + 1].getElement(j);
                }
            }
        }

        return new Matrix(newMatrixArray);
    }
}