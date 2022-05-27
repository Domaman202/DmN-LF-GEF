package ru.DmN.lfgef;

import javassist.ClassPool;
import javassist.CtClass;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.fabricmc.api.ClientModInitializer;

import java.lang.instrument.ClassDefinition;

public class Main implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        try {
            CtClass clazz = ClassPool.getDefault().get("org.lwjgl.opengl.LinuxDisplay");
            clazz.getMethod("resetDisplayMode", "()V").setBody("{}");
            ByteBuddyAgent.install().redefineClasses(new ClassDefinition(Class.forName("org.lwjgl.opengl.LinuxDisplay"), clazz.toBytecode()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
