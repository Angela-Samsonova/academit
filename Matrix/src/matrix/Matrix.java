package matrix;

import vector.Vector;

public class Matrix {
    private int m; // number of columns, vector size
    private int n; //number of rows, vectorsArray size
   // private double[][] matrixArray;
    private Vector[] vectorsArray;

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        vectorsArray = new Vector[n];

        for (int i = 0; i < n; i++) {
            vectorsArray[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.m, matrix.n);
        this.vectorsArray = matrix.vectorsArray;
    }

    public Matrix(double[][] matrixArray) {
        this.m = matrixArray[0].length; // number of columns, vector size
        this.n = matrixArray.length; //number of rows, vectorsArray size
        this.matrixArray = matrixArray;
    }

    public Matrix(Vector[] vector) {

    }

    // Return column length
    public int getM() {
        return m;
    }

    // Return row length
    public int getN() {
        return n;
    }

    public double get(int i, int j) {
        return matrixArray[i][j];
    }

    // сумма матриц
    public Matrix sum(Matrix secondMatrix) {
        double[][] newMatrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrixArray[i][j] + secondMatrix.get(i, j);
            }
        }
        return new Matrix(newMatrix);
    }

    // вычитание матриц
    public Matrix sub(Matrix secondMatrix) {
        double[][] newMatrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrixArray[i][j] - secondMatrix.get(i, j);
            }
        }
        return new Matrix(newMatrix);
    }

    // печать в консоль
    private void print() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%5.2f ", matrixArray[i][j]);
            }
            System.out.println();
        }
    }

    // умножение
    public Matrix mult(Matrix secondMatrix) {
        int k = secondMatrix.getN();
        double[][] newMatrix = new double[m][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                int temp = 0;
                for (int l = 0; l < n; l++) {
                    temp += matrixArray[i][l] * secondMatrix.get(l, j);
                }
                newMatrix[i][j] = temp;
            }
        }
        return new Matrix(newMatrix);
    }

    private Matrix mult(double a) {
        double[][] newMatrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[i][j] = matrixArray[i][j] * a;
            }
        }
        return new Matrix(newMatrix);
    }

    public static Matrix unitMatrix(int n) {
        if (n < 1) {
            System.out.println("Impossible to create unit matrix size of " + n);
            return null;
        }
        double[][] newMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    newMatrix[i][j] = 1;
                } else {
                    newMatrix[i][j] = 0;
                }
            }
        }
        return new Matrix(newMatrix);
    }

    public Matrix transpose() {
        double[][] newMatrix = new double[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newMatrix[j][i] = matrixArray[i][j];
            }
        }
        return new Matrix(newMatrix);
    }

    // детерминант
    public double determinant() {
        return determinant(m, matrixArray);
    }

    private double determinant(int m, double[][] a) {
        if (m > 2) {
            int temp = 0;
            for (int j = 0; j < m; j++) {
                double[][] b = getMinore(m, 0, j, a);

                if (j % 2 == 0) {
                    temp += a[0][j] * determinant(m - 1, b);
                } else {
                    temp -= a[0][j] * determinant(m - 1, b);
                }
            }
            return temp;
        } else if (m == 2) {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        } else {
            return a[0][0];
        }
    }

    private double[][] getMinore(int m, int i, int j, double[][] a) {
        double[][] b = new double[m - 1][m - 1];
        int rowIndex = 0;
        for (int t = 0; t < m; t++) {
            if (t != i) {
                int colomnIndex = 0;
                for (int l = 0; l < m; l++) {
                    if (l != j) {
                        b[colomnIndex][rowIndex] = a[t][l];
                        colomnIndex++;
                    }
                }
                rowIndex++;
            }
        }
        return b;
    }

    // инвертирование матрицы
    public Matrix inverseMatrix() {
        if (m == n) {
            double det = determinant();
            if (det != 0) {
                // матрица миноров
                double[][] minorMatrix = new double[m][m];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        double[][] ijMinor = getMinore(m, i, j, matrixArray);
                        minorMatrix[i][j] = new Matrix(ijMinor).determinant();
                    }
                }
                // матрица алгабраических дополнений
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        if (i % 2 + j % 2 == 1) {
                            minorMatrix[i][j] = -minorMatrix[i][j];
                        }
                    }
                }
                // транспортированная матрица алгебраических дополнений

                return (new Matrix(minorMatrix).transpose()).mult((1 / det));
            }
        }
        System.out.println("Impossible to create an inverse matrix.");
        return null;
    }

//    Во всех методах, кроме конструкторов, если размеры входных данных неверные, то кидать исключение.


//            a.	Matrix(n, m) – матрица нулей размера nxm
//b.	Matrix(Matrix) – конструктор копирования
//c.	Matrix(double[][]) – из двумерного массива (в C#double[,])
//d.	Matrix(Vector[]) – из массива векторов-строк
//2.	Методы:
//    a.	Получение размеров матрицы
//    b.	Получение и задание вектора-строки по индексу
//    c.	Получение вектора-столбца по индексу
//    d.	Транспонирование матрицы
//    e.	Умножение на скаляр
//    f.	Вычисление определителя матрицы
//    g.	toStringопределить так, чтобы результат получался в таком виде:{ {1, 2 },{ 2, 3} }
//    h.	умножение матрицы на вектор
//    i.	Сложение матриц
//    j.	Вычитание матриц
//3.	Статические методы:
//    a.	Сложение матриц
//    b.	Вычитание матриц
//    c.	Умножение матриц

}
