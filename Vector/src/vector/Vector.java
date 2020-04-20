package vector;

import java.util.Arrays;

public class Vector {
    private int n;
    private double[] elements;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        this.elements = new double[n];
    }

    public Vector(Vector vector) {
        this(vector.n, vector.elements);
    }

    public Vector(double[] array) {
        this(array.length, array);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        this.elements = new double[n];

        System.arraycopy(array, 0, elements, 0, Math.min(array.length, n));
    }

    public int getSize() {
        return n;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        return n == vector.n &&
                Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Integer.hashCode(n);
        hash = prime * hash + Arrays.hashCode(elements);
        return hash;
    }

    //
    public void addVector(Vector vector) {
        Vector newVector = addVectors(this, vector);

        this.n = newVector.n;
        this.elements = newVector.elements;
    }

    //
    public void subtractVector(Vector vector) {
        Vector newVector = subtractVectors(this, vector);

        this.n = newVector.n;
        this.elements = newVector.elements;
    }

    public void multiplyByScalar(double x) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= x;
        }
    }

    public void revertVector() {
        this.multiplyByScalar(-1);
    }

    public double getLength() {
        double sumOfElements = 0;

        for (double e : elements) {
            sumOfElements += Math.pow(e, 2.0);
        }

        return Math.sqrt(sumOfElements);
    }

    public void getAndChangeElement(int index, double newValue) {
        elements[index] = newValue;
    }

    public static Vector addVectors(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        Vector resultingVector;
        int resultingVectorSize;

        if (vector1.getSize() > vector2.getSize()) {
            resultingVectorSize = vector1.getSize();
            resultingVector = new Vector(resultingVectorSize);

            double[] extendedArray = Arrays.copyOf(vector2.elements, resultingVectorSize);
            Vector newVectorFromVector2 = new Vector(resultingVectorSize, extendedArray);

            for (int i = 0; i < resultingVectorSize; i++) {
                resultingVector.elements[i] = vector1.elements[i] + newVectorFromVector2.elements[i];
            }
        } else {
            resultingVectorSize = vector2.getSize();
            resultingVector = new Vector(resultingVectorSize);

            double[] extendedArray = Arrays.copyOf(vector1.elements, resultingVectorSize);
            Vector newVectorFromVector1 = new Vector(resultingVectorSize, extendedArray);

            for (int i = 0; i < resultingVectorSize; i++) {
                resultingVector.elements[i] = vector2.elements[i] + newVectorFromVector1.elements[i];
            }
        }

        return resultingVector;
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        vector2.revertVector();

        Vector newVector = addVectors(vector1, vector2);

        vector2.revertVector();

        return newVector;
    }

    public static Vector multiplyVectors(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        Vector resultingVector;
        int resultingVectorSize;

        if (vector1.getSize() == vector2.getSize()) {
            resultingVectorSize = vector1.getSize();
            resultingVector = new Vector(resultingVectorSize);

            for (int i = 0; i < resultingVectorSize; i++) {
                resultingVector.elements[i] = vector1.elements[i] * vector2.elements[i];
            }
        } else if (vector1.getSize() > vector2.getSize()) {
            resultingVectorSize = vector1.getSize();
            resultingVector = new Vector(resultingVectorSize);

            double[] extendedArray = Arrays.copyOf(vector2.elements, resultingVectorSize);
            Vector newVectorFromVector2 = new Vector(resultingVectorSize, extendedArray);

            for (int i = 0; i < resultingVectorSize; i++) {
                resultingVector.elements[i] = vector1.elements[i] * newVectorFromVector2.elements[i];
            }
        } else {
            resultingVectorSize = vector2.getSize();
            resultingVector = new Vector(resultingVectorSize);

            double[] extendedArray = Arrays.copyOf(vector1.elements, resultingVectorSize);
            Vector newVectorFromVector1 = new Vector(resultingVectorSize, extendedArray);

            for (int i = 0; i < resultingVectorSize; i++) {
                resultingVector.elements[i] = vector2.elements[i] * newVectorFromVector1.elements[i];
            }
        }

        return resultingVector;
    }
}