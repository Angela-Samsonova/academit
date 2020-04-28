package vector;

import java.util.Arrays;

public class Vector {
    private double[] elements;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }

        elements = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.elements);
    }

    public Vector(double[] array) {
        this(array.length, array);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }

        elements = new double[size];

        System.arraycopy(array, 0, elements, 0, Math.min(array.length, size));
    }

    public double getElement(int index) {
        return elements[index];
    }

    public void setElement(int index, double newValue) {
        elements[index] = newValue;
    }

    public int getSize() {
        return elements.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (double element : elements) {
            sb.append(element).append(", ");
        }

        sb.replace(sb.length() - 2, sb.length(), "}");

        return sb.toString();
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
        return Arrays.equals(elements, vector.elements);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(elements);
        return hash;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (getSize() > vector.getSize()) {
            double[] extendedArray = Arrays.copyOf(vector.elements, getSize());
            vector = new Vector(extendedArray);

            for (int i = 0; i < getSize(); i++) {
                elements[i] += vector.elements[i];
            }
        } else {
            elements = Arrays.copyOf(elements, vector.getSize());

            for (int i = 0; i < vector.getSize(); i++) {
                elements[i] += vector.elements[i];
            }
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        if (getSize() > vector.getSize()) {
            double[] extendedArray = Arrays.copyOf(vector.elements, getSize());
            vector = new Vector(extendedArray);

            for (int i = 0; i < getSize(); i++) {
                elements[i] -= vector.elements[i];
            }
        } else {
            elements = Arrays.copyOf(elements, vector.getSize());

            for (int i = 0; i < vector.getSize(); i++) {
                elements[i] -= vector.elements[i];
            }
        }
    }

    public void multiplyByScalar(double x) {
        for (int i = 0; i < elements.length; i++) {
            elements[i] *= x;
        }
    }

    public void revert() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double elementsSquaresSum = 0; //поменять имя по смыслу

        for (double e : elements) {
            elementsSquaresSum += Math.pow(e, 2.0);
        }

        return Math.sqrt(elementsSquaresSum);
    }

    public static Vector add(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        Vector newvector = new Vector(vector1);
        newvector.add(vector2);

        return newvector;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        Vector newvector = new Vector(vector1);
        newvector.subtract(vector2);

        return newvector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null || vector2 == null) {
            throw new IllegalArgumentException("vector must be not null");
        }

        double sum = 0;

        if (vector1.getSize() == vector2.getSize()) {
            for (int i = 0; i < vector1.getSize(); i++) {
                sum += vector1.elements[i] * vector2.elements[i];
            }
        } else if (vector1.getSize() > vector2.getSize()) {
            double[] extendedArray = Arrays.copyOf(vector2.elements, vector1.getSize());

            for (int i = 0; i < vector1.getSize(); i++) {
                sum += vector1.elements[i] * extendedArray[i];
            }
        } else {
            double[] extendedArray = Arrays.copyOf(vector1.elements, vector2.getSize());

            for (int i = 0; i < vector2.getSize(); i++) {
                sum += vector2.elements[i] * extendedArray[i];
            }
        }

        return sum;
    }
}