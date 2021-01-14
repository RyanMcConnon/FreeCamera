package com.ryanpm96.freecamera.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class InitKeyBindings {
    private static final String category = "Free Camera";
    private static final String rotateCW = "Rotate Clockwise";
    private static final String rotateCCW = "Rotate Counter-Clockwise";
    private static final String zoomIn = "Zoom In";
    private static final String zoomOut = "Zoom Out";

    private static final String togglePerspective = "Toggle Perspective";
    private static final String resetCamera = "Reset Camera";

    public static KeyBinding ROTATE_CW = new KeyBinding(rotateCW, 74, category);
    public static KeyBinding ROTATE_CCW = new KeyBinding(rotateCCW, 76, category);
    public static KeyBinding ZOOM_IN = new KeyBinding(zoomIn, 73, category);
    public static KeyBinding ZOOM_OUT = new KeyBinding(zoomOut, 75, category);
    public static KeyBinding TOGGLE_PERSPECTIVE = new KeyBinding(togglePerspective, 79, category);
    public static KeyBinding RESET_CAMERA = new KeyBinding(resetCamera, 80, category);


    public InitKeyBindings(){}

    public static void registerKeyBindings(){
        ClientRegistry.registerKeyBinding(ROTATE_CW);
        ClientRegistry.registerKeyBinding(ROTATE_CCW);
        ClientRegistry.registerKeyBinding(ZOOM_IN);
        ClientRegistry.registerKeyBinding(ZOOM_OUT);
        ClientRegistry.registerKeyBinding(TOGGLE_PERSPECTIVE);
        ClientRegistry.registerKeyBinding(RESET_CAMERA);
    }
}
