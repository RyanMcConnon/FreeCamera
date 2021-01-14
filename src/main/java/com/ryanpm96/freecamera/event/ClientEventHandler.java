package com.ryanpm96.freecamera.event;

import com.ryanpm96.freecamera.init.InitKeyBindings;
import com.ryanpm96.freecamera.math.Vector4d;
import com.ryanpm96.freecamera.util.FreeCameraHelper;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;


@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {
    private static boolean isFree = false;
    private static boolean isPanning = false;
    private static FakePlayer camera;
    private static Vector3f camera_position;
    private static float set_distance;
    private static float camera_distance;
    private static float min_distance = 1.0F;
    private static float max_distance = 20.0F;
    private static float zoomSensitivity = 0.5F;
    private static double rotateSensitivity = 0.1F;
    private static double panSensitivity = 0.05F;
    private static double min_pitch = 0.0F;
    private static double max_pitch = 45.0F;
    private static float yaw;
    private static float pitch;

    private static double previous_mouseX = 0.0D;
    private static double previous_mouseY = 0.0D;
    private static double current_mouseY;
    private static double current_mouseX;
    private static double mouse_dX;
    private static double mouse_dY;
    private static Vector4d pan_bounds;        // (lower_x, lower_z, upper_x, upper_z)
    private static double pan_overflow = 5.0D; // Set the distance the camera can push past the bounds

    private static float pan_dX = 0.0F;
    private static float pan_dZ = 0.0F;
    private static ArrayList<Entity> entity_list = new ArrayList<Entity>();




    public ClientEventHandler() {
    }

    @SubscribeEvent
    public static void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        ActiveRenderInfo info = event.getInfo();
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        // Replace with entity list of combatants
        if(entity_list.isEmpty()){
            entity_list.add(player);
        }

        if (player == null || mc.world == null) {
            return;
        }

        if (InitKeyBindings.TOGGLE_PERSPECTIVE.isPressed()) {
            isFree = !isFree;
            isPanning = false;
        }
        if (isFree) {
            // Initialize a camera entity if needed
            if (camera == null) {
                camera = FakePlayerFactory.getMinecraft(mc.getIntegratedServer().getWorld(player.world.getDimensionKey()));
                yaw = 45.0F;
                pitch = 35.264F; //isometric
                set_distance = 10.0F;
            }
            if (InitKeyBindings.RESET_CAMERA.isPressed()){
                pan_dX = 0.0F;
                pan_dZ = 0.0F;
                yaw = 45.0F;
                pitch = 35.264F; //isometric
                set_distance = 10.0F;
                isPanning = false;
            }

            // Get current mouse position
            current_mouseX = mc.mouseHelper.getMouseX();
            current_mouseY = mc.mouseHelper.getMouseY();

            // Right-click: orbit
            if(mc.mouseHelper.isRightDown()){
                mouse_dX = previous_mouseX - current_mouseX;
                yaw += mouse_dX * rotateSensitivity;
                yaw = (yaw % 360.0F + 360.0F) % 360.0F;
            }
            // Middle-click: pan
            if(mc.mouseHelper.isMiddleDown()){
                mouse_dX = previous_mouseX - current_mouseX;
                mouse_dY = previous_mouseY - current_mouseY;
                pan_dX -= (mouse_dX*Math.cos(Math.toRadians(yaw))) * panSensitivity;
                pan_dZ -= (mouse_dX*Math.sin(Math.toRadians(yaw))) * panSensitivity;
                pan_dX -= (mouse_dY*Math.cos(Math.toRadians(yaw+90))) * panSensitivity;
                pan_dZ -= (mouse_dY*Math.sin(Math.toRadians(yaw+90))) * panSensitivity;
            }

            // Set previous mouse position to current mouse position
            previous_mouseX = mc.mouseHelper.getMouseX();
            previous_mouseY = mc.mouseHelper.getMouseY();

            float step = 0.1F;
            if(!isPanning) {
                camera_distance = min_distance;
            } else {
                camera_distance = set_distance;
            }
            do {
                pitch = (float) ((camera_distance - min_distance) / (max_distance - min_distance) * (max_pitch - min_pitch) + min_pitch);
                camera_position = FreeCameraHelper.getCameraPosition(camera_distance, pitch, yaw);
                camera_position.add(pan_dX,0.0F,pan_dZ);
                camera.setPosition(player.getPosX() + camera_position.getX(),
                        player.getPosY() + camera_position.getY(),
                        player.getPosZ() + camera_position.getZ());
                if (camera.isEntityInsideOpaqueBlock()) {
                    while (camera.isEntityInsideOpaqueBlock()) {
                        camera_distance -= step;
                        camera_position = FreeCameraHelper.getCameraPosition(camera_distance, pitch, yaw);
                        camera_position.add(pan_dX,0.0F,pan_dZ);
                        camera.setPosition(player.getPosX() + camera_position.getX(),
                                player.getPosY() + camera_position.getY(),
                                player.getPosZ() + camera_position.getZ());
                    }
                    set_distance = MathHelper.clamp(camera_distance, min_distance, set_distance);
                    break;
                }
                camera_distance += step;
            } while (camera_distance < set_distance);
            event.setYaw(yaw + 180.0F);
            event.setPitch(pitch);
            camera.prevPosX = player.prevPosX + camera_position.getX();
            camera.prevPosY = player.prevPosY + camera_position.getY();
            camera.prevPosZ = player.prevPosZ + camera_position.getZ();
            camera.prevCameraYaw = yaw;
            camera.prevRotationPitch = pitch;
            info.update(mc.world, camera, false, false, mc.getRenderPartialTicks());
        }
    }

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        if (isFree) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void preRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
        if (isFree && event.getType().equals(RenderGameOverlayEvent.ElementType.CROSSHAIRS)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent event){
        PlayerEntity player = Minecraft.getInstance().player;
        Vector3d playerPos = new Vector3d(player.getPosX(), player.getPosY(), player.getPosZ());
        Vector3d camPos = new Vector3d(camera.getPosX(), camera.getPosY(), camera.getPosZ());
        if(isFree && event.getPlayer().equals(Minecraft.getInstance().player) && playerPos.distanceTo(camPos)<min_distance){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseInputEvent event){
        GameSettings gameSettings = Minecraft.getInstance().gameSettings;
        if(isFree) {
            if (gameSettings.keyBindPickBlock.isPressed()) {
                Minecraft.getInstance().mouseHelper.grabMouse();
                isPanning = true;
            } else if(gameSettings.keyBindUseItem.isPressed()){
                Minecraft.getInstance().mouseHelper.grabMouse();
            }else {
                Minecraft.getInstance().mouseHelper.ungrabMouse();
            }
        }
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollEvent event){
        if(isFree){
            set_distance -= (event.getScrollDelta()*zoomSensitivity);
            set_distance = MathHelper.clamp(set_distance, min_distance, max_distance);
        }
    }

}

//Hold right click orbits
//Holding middle click pans
//Mouse wheel scrolls