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
package com.mrcrayfish.furniture.blocks;

import com.mrcrayfish.furniture.util.StateHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCounter extends BlockFurniture
{
	public static final PropertyEnum TYPE = PropertyEnum.create("type", CounterType.class);

	public BlockCounter(Material material)
	{
		super(material);
		this.setHardness(0.5F);
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, CounterType.NORMAL));
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		if (StateHelper.getBlock(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.DOWN) instanceof BlockCounter)
		{
			if (StateHelper.getRotation(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.DOWN) == StateHelper.Direction.LEFT)
			{
				return state.withProperty(TYPE, CounterType.CORNER_LEFT);
			}
			else if (StateHelper.getRotation(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.DOWN) == StateHelper.Direction.RIGHT)
			{
				return state.withProperty(TYPE, CounterType.CORNER_RIGHT);
			}
		}
		if (StateHelper.getBlock(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.UP) instanceof BlockCounter)
		{
			if (StateHelper.getRotation(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.UP) == StateHelper.Direction.LEFT && !(StateHelper.getBlock(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.LEFT) instanceof BlockCounter))
			{
				return state.withProperty(TYPE, CounterType.INVERT_RIGHT);
			}
			else if (StateHelper.getRotation(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.UP) == StateHelper.Direction.RIGHT && !(StateHelper.getBlock(world, pos, (EnumFacing) state.getValue(FACING), StateHelper.Direction.RIGHT) instanceof BlockCounter))
			{
				return state.withProperty(TYPE, CounterType.INVERT_LEFT);
			}
		}
		return state.withProperty(TYPE, CounterType.NORMAL);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING, TYPE });
	}

	public static enum CounterType implements IStringSerializable
	{
		NORMAL, CORNER_LEFT, CORNER_RIGHT, INVERT_LEFT, INVERT_RIGHT;

		@Override
		public String getName()
		{
			return this.toString().toLowerCase();
		}
	}
}
