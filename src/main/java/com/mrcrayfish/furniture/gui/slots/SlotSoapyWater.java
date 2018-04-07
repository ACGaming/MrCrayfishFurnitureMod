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
package com.mrcrayfish.furniture.gui.slots;

import com.mrcrayfish.furniture.Reference;
import com.mrcrayfish.furniture.init.FurnitureItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSoapyWater extends Slot
{

	public SlotSoapyWater(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		if (par1ItemStack == null) {
			return false;
		}

		Item item = par1ItemStack.getItem();
		return item == FurnitureItems.itemSoapyWater || item == FurnitureItems.itemSuperSoapyWater;
	}

	@Override
	public String getSlotTexture()
	{
		return Reference.MOD_ID + ":items/outline_bucket";
	}
}