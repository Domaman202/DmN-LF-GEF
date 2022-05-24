package ru.DmN.lfgef.mixin;

import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(value = Display.class, remap = false)
public class DisplayMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/DisplayImplementation;resetDisplayMode()V"), method = "reset", cancellable = true)
	private static void reset(CallbackInfo info) throws NoSuchFieldException, IllegalAccessException {
		Field f = Display.class.getDeclaredField("display_impl");
		f.setAccessible(true);
		if (f.get(null).getClass().getName().contains("Linux"))
			info.cancel();
	}
}
