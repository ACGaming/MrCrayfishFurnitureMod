/**
 * MrCrayfish's Furniture Mod
 * Copyright (C) 2016  MrCrayfish (http://www.mrcrayfish.com/)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mrcrayfish.furniture.handler;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.mrcrayfish.furniture.init.FurnitureBlocks;
import com.mrcrayfish.furniture.init.FurnitureSounds;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageFart;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class InputHandler
{
	public static KeyBinding key_fart;
	public static boolean keyPressed = false;
	public static boolean keyHasBeenPressed = false;
	private Random rand = new Random();

	public InputHandler()
	{
		key_fart = new KeyBinding("key.fart.desc", Keyboard.KEY_G, "keys.cfm.category");
		ClientRegistry.registerKeyBinding(key_fart);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (key_fart.isPressed())
		{
			keyPressed = true;
		}
	}

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if (event.player.worldObj.getBlockState(new BlockPos((int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY), (int) Math.floor(event.player.posZ))).getBlock() == FurnitureBlocks.toilet)
		{
			if (keyPressed)
			{
				keyPressed = false;
				PacketHandler.INSTANCE.sendToServer(new MessageFart());
			}
		}
	}

	public KeyBinding getFartKey()
	{
		return key_fart;
	}
}
