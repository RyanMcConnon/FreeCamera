package com.ryanpm96.freecamera.math;

import java.nio.DoubleBuffer;

import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public final class Matrix4d {
    protected double m00;
    protected double m01;
    protected double m02;
    protected double m03;
    protected double m10;
    protected double m11;
    protected double m12;
    protected double m13;
    protected double m20;
    protected double m21;
    protected double m22;
    protected double m23;
    protected double m30;
    protected double m31;
    protected double m32;
    protected double m33;

    public Matrix4d() {
    }

    public Matrix4d(com.ryanpm96.freecamera.math.Matrix4d matrixIn) {
        this.m00 = matrixIn.m00;
        this.m01 = matrixIn.m01;
        this.m02 = matrixIn.m02;
        this.m03 = matrixIn.m03;
        this.m10 = matrixIn.m10;
        this.m11 = matrixIn.m11;
        this.m12 = matrixIn.m12;
        this.m13 = matrixIn.m13;
        this.m20 = matrixIn.m20;
        this.m21 = matrixIn.m21;
        this.m22 = matrixIn.m22;
        this.m23 = matrixIn.m23;
        this.m30 = matrixIn.m30;
        this.m31 = matrixIn.m31;
        this.m32 = matrixIn.m32;
        this.m33 = matrixIn.m33;
    }

    public Matrix4d(Quaternion quaternionIn) {
        double f = quaternionIn.getX();
        double f1 = quaternionIn.getY();
        double f2 = quaternionIn.getZ();
        double f3 = quaternionIn.getW();
        double f4 = 2.0F * f * f;
        double f5 = 2.0F * f1 * f1;
        double f6 = 2.0F * f2 * f2;
        this.m00 = 1.0F - f5 - f6;
        this.m11 = 1.0F - f6 - f4;
        this.m22 = 1.0F - f4 - f5;
        this.m33 = 1.0F;
        double f7 = f * f1;
        double f8 = f1 * f2;
        double f9 = f2 * f;
        double f10 = f * f3;
        double f11 = f1 * f3;
        double f12 = f2 * f3;
        this.m10 = 2.0F * (f7 + f12);
        this.m01 = 2.0F * (f7 - f12);
        this.m20 = 2.0F * (f9 - f11);
        this.m02 = 2.0F * (f9 + f11);
        this.m21 = 2.0F * (f8 + f10);
        this.m12 = 2.0F * (f8 - f10);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            com.ryanpm96.freecamera.math.Matrix4d matrix4f = (com.ryanpm96.freecamera.math.Matrix4d)p_equals_1_;
            return Double.compare(matrix4f.m00, this.m00) == 0 && Double.compare(matrix4f.m01, this.m01) == 0 && Double.compare(matrix4f.m02, this.m02) == 0 && Double.compare(matrix4f.m03, this.m03) == 0 && Double.compare(matrix4f.m10, this.m10) == 0 && Double.compare(matrix4f.m11, this.m11) == 0 && Double.compare(matrix4f.m12, this.m12) == 0 && Double.compare(matrix4f.m13, this.m13) == 0 && Double.compare(matrix4f.m20, this.m20) == 0 && Double.compare(matrix4f.m21, this.m21) == 0 && Double.compare(matrix4f.m22, this.m22) == 0 && Double.compare(matrix4f.m23, this.m23) == 0 && Double.compare(matrix4f.m30, this.m30) == 0 && Double.compare(matrix4f.m31, this.m31) == 0 && Double.compare(matrix4f.m32, this.m32) == 0 && Double.compare(matrix4f.m33, this.m33) == 0;
        } else {
            return false;
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static int bufferIndex(int p_226594_0_, int p_226594_1_) {
        return p_226594_1_ * 4 + p_226594_0_;
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Matrix4d:\n");
        stringbuilder.append(this.m00);
        stringbuilder.append(" ");
        stringbuilder.append(this.m01);
        stringbuilder.append(" ");
        stringbuilder.append(this.m02);
        stringbuilder.append(" ");
        stringbuilder.append(this.m03);
        stringbuilder.append("\n");
        stringbuilder.append(this.m10);
        stringbuilder.append(" ");
        stringbuilder.append(this.m11);
        stringbuilder.append(" ");
        stringbuilder.append(this.m12);
        stringbuilder.append(" ");
        stringbuilder.append(this.m13);
        stringbuilder.append("\n");
        stringbuilder.append(this.m20);
        stringbuilder.append(" ");
        stringbuilder.append(this.m21);
        stringbuilder.append(" ");
        stringbuilder.append(this.m22);
        stringbuilder.append(" ");
        stringbuilder.append(this.m23);
        stringbuilder.append("\n");
        stringbuilder.append(this.m30);
        stringbuilder.append(" ");
        stringbuilder.append(this.m31);
        stringbuilder.append(" ");
        stringbuilder.append(this.m32);
        stringbuilder.append(" ");
        stringbuilder.append(this.m33);
        stringbuilder.append("\n");
        return stringbuilder.toString();
    }

    @OnlyIn(Dist.CLIENT)
    public void write(DoubleBuffer doubleBufferIn) {
        doubleBufferIn.put(bufferIndex(0, 0), this.m00);
        doubleBufferIn.put(bufferIndex(0, 1), this.m01);
        doubleBufferIn.put(bufferIndex(0, 2), this.m02);
        doubleBufferIn.put(bufferIndex(0, 3), this.m03);
        doubleBufferIn.put(bufferIndex(1, 0), this.m10);
        doubleBufferIn.put(bufferIndex(1, 1), this.m11);
        doubleBufferIn.put(bufferIndex(1, 2), this.m12);
        doubleBufferIn.put(bufferIndex(1, 3), this.m13);
        doubleBufferIn.put(bufferIndex(2, 0), this.m20);
        doubleBufferIn.put(bufferIndex(2, 1), this.m21);
        doubleBufferIn.put(bufferIndex(2, 2), this.m22);
        doubleBufferIn.put(bufferIndex(2, 3), this.m23);
        doubleBufferIn.put(bufferIndex(3, 0), this.m30);
        doubleBufferIn.put(bufferIndex(3, 1), this.m31);
        doubleBufferIn.put(bufferIndex(3, 2), this.m32);
        doubleBufferIn.put(bufferIndex(3, 3), this.m33);
    }

    @OnlyIn(Dist.CLIENT)
    public void setIdentity() {
        this.m00 = 1.0F;
        this.m01 = 0.0F;
        this.m02 = 0.0F;
        this.m03 = 0.0F;
        this.m10 = 0.0F;
        this.m11 = 1.0F;
        this.m12 = 0.0F;
        this.m13 = 0.0F;
        this.m20 = 0.0F;
        this.m21 = 0.0F;
        this.m22 = 1.0F;
        this.m23 = 0.0F;
        this.m30 = 0.0F;
        this.m31 = 0.0F;
        this.m32 = 0.0F;
        this.m33 = 1.0F;
    }

    @OnlyIn(Dist.CLIENT)
    public double adjugateAndDet() {
        double f = this.m00 * this.m11 - this.m01 * this.m10;
        double f1 = this.m00 * this.m12 - this.m02 * this.m10;
        double f2 = this.m00 * this.m13 - this.m03 * this.m10;
        double f3 = this.m01 * this.m12 - this.m02 * this.m11;
        double f4 = this.m01 * this.m13 - this.m03 * this.m11;
        double f5 = this.m02 * this.m13 - this.m03 * this.m12;
        double f6 = this.m20 * this.m31 - this.m21 * this.m30;
        double f7 = this.m20 * this.m32 - this.m22 * this.m30;
        double f8 = this.m20 * this.m33 - this.m23 * this.m30;
        double f9 = this.m21 * this.m32 - this.m22 * this.m31;
        double f10 = this.m21 * this.m33 - this.m23 * this.m31;
        double f11 = this.m22 * this.m33 - this.m23 * this.m32;
        double f12 = this.m11 * f11 - this.m12 * f10 + this.m13 * f9;
        double f13 = -this.m10 * f11 + this.m12 * f8 - this.m13 * f7;
        double f14 = this.m10 * f10 - this.m11 * f8 + this.m13 * f6;
        double f15 = -this.m10 * f9 + this.m11 * f7 - this.m12 * f6;
        double f16 = -this.m01 * f11 + this.m02 * f10 - this.m03 * f9;
        double f17 = this.m00 * f11 - this.m02 * f8 + this.m03 * f7;
        double f18 = -this.m00 * f10 + this.m01 * f8 - this.m03 * f6;
        double f19 = this.m00 * f9 - this.m01 * f7 + this.m02 * f6;
        double f20 = this.m31 * f5 - this.m32 * f4 + this.m33 * f3;
        double f21 = -this.m30 * f5 + this.m32 * f2 - this.m33 * f1;
        double f22 = this.m30 * f4 - this.m31 * f2 + this.m33 * f;
        double f23 = -this.m30 * f3 + this.m31 * f1 - this.m32 * f;
        double f24 = -this.m21 * f5 + this.m22 * f4 - this.m23 * f3;
        double f25 = this.m20 * f5 - this.m22 * f2 + this.m23 * f1;
        double f26 = -this.m20 * f4 + this.m21 * f2 - this.m23 * f;
        double f27 = this.m20 * f3 - this.m21 * f1 + this.m22 * f;
        this.m00 = f12;
        this.m10 = f13;
        this.m20 = f14;
        this.m30 = f15;
        this.m01 = f16;
        this.m11 = f17;
        this.m21 = f18;
        this.m31 = f19;
        this.m02 = f20;
        this.m12 = f21;
        this.m22 = f22;
        this.m32 = f23;
        this.m03 = f24;
        this.m13 = f25;
        this.m23 = f26;
        this.m33 = f27;
        return f * f11 - f1 * f10 + f2 * f9 + f3 * f8 - f4 * f7 + f5 * f6;
    }

    @OnlyIn(Dist.CLIENT)
    public void transpose() {
        double f = this.m10;
        this.m10 = this.m01;
        this.m01 = f;
        f = this.m20;
        this.m20 = this.m02;
        this.m02 = f;
        f = this.m21;
        this.m21 = this.m12;
        this.m12 = f;
        f = this.m30;
        this.m30 = this.m03;
        this.m03 = f;
        f = this.m31;
        this.m31 = this.m13;
        this.m13 = f;
        f = this.m32;
        this.m32 = this.m23;
        this.m23 = f;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean invert() {
        double f = this.adjugateAndDet();
        if (Math.abs(f) > 1.0E-6F) {
            this.mul(f);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Multiply self on the right by the parameter
     */
    @OnlyIn(Dist.CLIENT)
    public void mul(com.ryanpm96.freecamera.math.Matrix4d matrix) {
        double f = this.m00 * matrix.m00 + this.m01 * matrix.m10 + this.m02 * matrix.m20 + this.m03 * matrix.m30;
        double f1 = this.m00 * matrix.m01 + this.m01 * matrix.m11 + this.m02 * matrix.m21 + this.m03 * matrix.m31;
        double f2 = this.m00 * matrix.m02 + this.m01 * matrix.m12 + this.m02 * matrix.m22 + this.m03 * matrix.m32;
        double f3 = this.m00 * matrix.m03 + this.m01 * matrix.m13 + this.m02 * matrix.m23 + this.m03 * matrix.m33;
        double f4 = this.m10 * matrix.m00 + this.m11 * matrix.m10 + this.m12 * matrix.m20 + this.m13 * matrix.m30;
        double f5 = this.m10 * matrix.m01 + this.m11 * matrix.m11 + this.m12 * matrix.m21 + this.m13 * matrix.m31;
        double f6 = this.m10 * matrix.m02 + this.m11 * matrix.m12 + this.m12 * matrix.m22 + this.m13 * matrix.m32;
        double f7 = this.m10 * matrix.m03 + this.m11 * matrix.m13 + this.m12 * matrix.m23 + this.m13 * matrix.m33;
        double f8 = this.m20 * matrix.m00 + this.m21 * matrix.m10 + this.m22 * matrix.m20 + this.m23 * matrix.m30;
        double f9 = this.m20 * matrix.m01 + this.m21 * matrix.m11 + this.m22 * matrix.m21 + this.m23 * matrix.m31;
        double f10 = this.m20 * matrix.m02 + this.m21 * matrix.m12 + this.m22 * matrix.m22 + this.m23 * matrix.m32;
        double f11 = this.m20 * matrix.m03 + this.m21 * matrix.m13 + this.m22 * matrix.m23 + this.m23 * matrix.m33;
        double f12 = this.m30 * matrix.m00 + this.m31 * matrix.m10 + this.m32 * matrix.m20 + this.m33 * matrix.m30;
        double f13 = this.m30 * matrix.m01 + this.m31 * matrix.m11 + this.m32 * matrix.m21 + this.m33 * matrix.m31;
        double f14 = this.m30 * matrix.m02 + this.m31 * matrix.m12 + this.m32 * matrix.m22 + this.m33 * matrix.m32;
        double f15 = this.m30 * matrix.m03 + this.m31 * matrix.m13 + this.m32 * matrix.m23 + this.m33 * matrix.m33;
        this.m00 = f;
        this.m01 = f1;
        this.m02 = f2;
        this.m03 = f3;
        this.m10 = f4;
        this.m11 = f5;
        this.m12 = f6;
        this.m13 = f7;
        this.m20 = f8;
        this.m21 = f9;
        this.m22 = f10;
        this.m23 = f11;
        this.m30 = f12;
        this.m31 = f13;
        this.m32 = f14;
        this.m33 = f15;
    }

    @OnlyIn(Dist.CLIENT)
    public void mul(Quaternion quaternion) {
        this.mul(new com.ryanpm96.freecamera.math.Matrix4d(quaternion));
    }

    @OnlyIn(Dist.CLIENT)
    public void mul(double scale) {
        this.m00 *= scale;
        this.m01 *= scale;
        this.m02 *= scale;
        this.m03 *= scale;
        this.m10 *= scale;
        this.m11 *= scale;
        this.m12 *= scale;
        this.m13 *= scale;
        this.m20 *= scale;
        this.m21 *= scale;
        this.m22 *= scale;
        this.m23 *= scale;
        this.m30 *= scale;
        this.m31 *= scale;
        this.m32 *= scale;
        this.m33 *= scale;
    }

    @OnlyIn(Dist.CLIENT)
    public static com.ryanpm96.freecamera.math.Matrix4d perspective(double fov, double aspectRatio, double nearPlane, double farPlane) {
        double f = (double)(1.0D / Math.tan(fov * (double)((double)Math.PI / 180F) / 2.0D));
        com.ryanpm96.freecamera.math.Matrix4d matrix4f = new com.ryanpm96.freecamera.math.Matrix4d();
        matrix4f.m00 = f / aspectRatio;
        matrix4f.m11 = f;
        matrix4f.m22 = (farPlane + nearPlane) / (nearPlane - farPlane);
        matrix4f.m32 = -1.0F;
        matrix4f.m23 = 2.0F * farPlane * nearPlane / (nearPlane - farPlane);
        return matrix4f;
    }

    @OnlyIn(Dist.CLIENT)
    public static com.ryanpm96.freecamera.math.Matrix4d orthographic(double width, double height, double nearPlane, double farPlane) {
        com.ryanpm96.freecamera.math.Matrix4d matrix4f = new com.ryanpm96.freecamera.math.Matrix4d();
        matrix4f.m00 = 2.0F / width;
        matrix4f.m11 = 2.0F / height;
        double f = farPlane - nearPlane;
        matrix4f.m22 = -2.0F / f;
        matrix4f.m33 = 1.0F;
        matrix4f.m03 = -1.0F;
        matrix4f.m13 = -1.0F;
        matrix4f.m23 = -(farPlane + nearPlane) / f;
        return matrix4f;
    }

    @OnlyIn(Dist.CLIENT)
    public void translate(Vector3d vector) {
        this.m03 += vector.getX();
        this.m13 += vector.getY();
        this.m23 += vector.getZ();
    }

    @OnlyIn(Dist.CLIENT)
    public com.ryanpm96.freecamera.math.Matrix4d copy() {
        return new com.ryanpm96.freecamera.math.Matrix4d(this);
    }

    @OnlyIn(Dist.CLIENT)
    public static com.ryanpm96.freecamera.math.Matrix4d makeScale(double p_226593_0_, double p_226593_1_, double p_226593_2_) {
        com.ryanpm96.freecamera.math.Matrix4d matrix4f = new com.ryanpm96.freecamera.math.Matrix4d();
        matrix4f.m00 = p_226593_0_;
        matrix4f.m11 = p_226593_1_;
        matrix4f.m22 = p_226593_2_;
        matrix4f.m33 = 1.0F;
        return matrix4f;
    }

    @OnlyIn(Dist.CLIENT)
    public static com.ryanpm96.freecamera.math.Matrix4d makeTranslate(double p_226599_0_, double p_226599_1_, double p_226599_2_) {
        com.ryanpm96.freecamera.math.Matrix4d matrix4f = new com.ryanpm96.freecamera.math.Matrix4d();
        matrix4f.m00 = 1.0F;
        matrix4f.m11 = 1.0F;
        matrix4f.m22 = 1.0F;
        matrix4f.m33 = 1.0F;
        matrix4f.m03 = p_226599_0_;
        matrix4f.m13 = p_226599_1_;
        matrix4f.m23 = p_226599_2_;
        return matrix4f;
    }

    // Forge start
    public Matrix4d(double[] values) {
        m00 = values[0];
        m01 = values[1];
        m02 = values[2];
        m03 = values[3];
        m10 = values[4];
        m11 = values[5];
        m12 = values[6];
        m13 = values[7];
        m20 = values[8];
        m21 = values[9];
        m22 = values[10];
        m23 = values[11];
        m30 = values[12];
        m31 = values[13];
        m32 = values[14];
        m33 = values[15];
    }

    public void set(com.ryanpm96.freecamera.math.Matrix4d mat) {
        this.m00 = mat.m00;
        this.m01 = mat.m01;
        this.m02 = mat.m02;
        this.m03 = mat.m03;
        this.m10 = mat.m10;
        this.m11 = mat.m11;
        this.m12 = mat.m12;
        this.m13 = mat.m13;
        this.m20 = mat.m20;
        this.m21 = mat.m21;
        this.m22 = mat.m22;
        this.m23 = mat.m23;
        this.m30 = mat.m30;
        this.m31 = mat.m31;
        this.m32 = mat.m32;
        this.m33 = mat.m33;
    }

    public void add(com.ryanpm96.freecamera.math.Matrix4d other) {
        m00 += other.m00;
        m01 += other.m01;
        m02 += other.m02;
        m03 += other.m03;
        m10 += other.m10;
        m11 += other.m11;
        m12 += other.m12;
        m13 += other.m13;
        m20 += other.m20;
        m21 += other.m21;
        m22 += other.m22;
        m23 += other.m23;
        m30 += other.m30;
        m31 += other.m31;
        m32 += other.m32;
        m33 += other.m33;
    }

    public void multiplyBackward(com.ryanpm96.freecamera.math.Matrix4d other) {
        com.ryanpm96.freecamera.math.Matrix4d copy = other.copy();
        copy.mul(this);
        this.set(copy);
    }

    public void setTranslation(double x, double y, double z) {
        this.m00 = 1.0F;
        this.m11 = 1.0F;
        this.m22 = 1.0F;
        this.m33 = 1.0F;
        this.m03 = x;
        this.m13 = y;
        this.m23 = z;
    }
}
