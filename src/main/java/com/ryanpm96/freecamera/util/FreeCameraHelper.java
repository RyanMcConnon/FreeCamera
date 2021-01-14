package com.ryanpm96.freecamera.util;

import com.ryanpm96.freecamera.math.Vector4d;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3f;

import java.util.ArrayList;

public class FreeCameraHelper {

    public FreeCameraHelper(){
    }

    public static Vector3f getCameraPosition(float camera_distance, double pitch, double yaw){
        float dX = (float) (camera_distance * Math.sin(Math.toRadians(-yaw)));
        float dY = (float) (camera_distance * Math.tan(Math.toRadians(pitch)));
        float dZ = (float) (camera_distance * Math.cos(Math.toRadians(yaw)));
        return new Vector3f(dX, dY, dZ);
    }
    
    public static Vector4d getPanBounds(ArrayList<Entity> entity_list, double pan_overflow){
        double lower_x = Double.MAX_VALUE;
        double lower_z = Double.MAX_VALUE;
        double upper_x = Double.MIN_VALUE;
        double upper_z = Double.MIN_VALUE;
        for (Entity entity: entity_list) {
            lower_x = Math.min(lower_x, entity.getPosX());
            lower_z = Math.min(lower_z, entity.getPosZ());
            upper_x = Math.max(upper_x, entity.getPosX());
            upper_z = Math.max(upper_z, entity.getPosZ());
        }
        return new Vector4d(lower_x-pan_overflow, lower_z-pan_overflow, upper_x+pan_overflow, upper_z+pan_overflow);
    }
}
