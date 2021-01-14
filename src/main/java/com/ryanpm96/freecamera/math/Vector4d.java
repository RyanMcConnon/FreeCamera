package com.ryanpm96.freecamera.math;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class Vector4d {
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector4d() {
    }

    public Vector4d(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4d(Vector4d vectorIn) {
        this(vectorIn.getX(), vectorIn.getY(), vectorIn.getZ(), 1.0F);
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            Vector4d vector4d = (Vector4d)p_equals_1_;
            if (Double.compare(vector4d.x, this.x) != 0) {
                return false;
            } else if (Double.compare(vector4d.y, this.y) != 0) {
                return false;
            } else if (Double.compare(vector4d.z, this.z) != 0) {
                return false;
            } else {
                return Double.compare(vector4d.w, this.w) == 0;
            }
        } else {
            return false;
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double getW() {
        return this.w;
    }

    public void scale(Vector3f vec) {
        this.x *= vec.getX();
        this.y *= vec.getY();
        this.z *= vec.getZ();
    }

    public void set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double dot(Vector4d vectorIn) {
        return this.x * vectorIn.x + this.y * vectorIn.y + this.z * vectorIn.z + this.w * vectorIn.w;
    }

    public boolean normalize() {
        double f = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
        if ((double)f < 1.0E-5D) {
            return false;
        } else {
            double f1 = MathHelper.fastInvSqrt(f);
            this.x *= f1;
            this.y *= f1;
            this.z *= f1;
            this.w *= f1;
            return true;
        }
    }

    public void transform(Matrix4d matrixIn) {
        double f = this.x;
        double f1 = this.y;
        double f2 = this.z;
        double f3 = this.w;
        this.x = matrixIn.m00 * f + matrixIn.m01 * f1 + matrixIn.m02 * f2 + matrixIn.m03 * f3;
        this.y = matrixIn.m10 * f + matrixIn.m11 * f1 + matrixIn.m12 * f2 + matrixIn.m13 * f3;
        this.z = matrixIn.m20 * f + matrixIn.m21 * f1 + matrixIn.m22 * f2 + matrixIn.m23 * f3;
        this.w = matrixIn.m30 * f + matrixIn.m31 * f1 + matrixIn.m32 * f2 + matrixIn.m33 * f3;
    }

    public void transform(Quaternion quaternionIn) {
        Quaternion quaternion = new Quaternion(quaternionIn);
        quaternion.multiply(new Quaternion((float)this.getX(), (float)this.getY(), (float)this.getZ(), 0.0F));
        Quaternion quaternion1 = new Quaternion(quaternionIn);
        quaternion1.conjugate();
        quaternion.multiply(quaternion1);
        this.set(quaternion.getX(), quaternion.getY(), quaternion.getZ(), this.getW());
    }

    public void perspectiveDivide() {
        this.x /= this.w;
        this.y /= this.w;
        this.z /= this.w;
        this.w = 1.0F;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + "]";
    }

    // Forge start
    public void set(double[] values) {
        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
        this.w = values[3];
    }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }
    public void setW(double z) { this.w = z; }
}
