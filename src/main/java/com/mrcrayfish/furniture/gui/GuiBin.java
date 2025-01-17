package com.mrcrayfish.furniture.gui;

import com.mrcrayfish.furniture.gui.containers.ContainerBin;
import com.mrcrayfish.furniture.network.PacketHandler;
import com.mrcrayfish.furniture.network.message.MessageEmptyBin;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiBin extends GuiContainer
{
    private static final ResourceLocation gui = new ResourceLocation("cfm:textures/gui/bin.png");
    private GuiButton button_empty;
    private int tileX, tileY, tileZ;

    public GuiBin(IInventory playerInventory, IInventory binInventory, int tileX, int tileY, int tileZ)
    {
        super(new ContainerBin(playerInventory, binInventory));
        this.tileX = tileX;
        this.tileY = tileY;
        this.tileZ = tileZ;
        this.xSize = 176;
        this.ySize = 197;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
    }

    @Override
    public void initGui()
    {
        super.initGui();
        Keyboard.enableRepeatEvents(false);
        buttonList.clear();
        int posX = width / 2;
        int posY = height / 2;
        button_empty = new GuiButton(0, posX + 40, posY - 50, 40, 20, I18n.format("cfm.button.empty"));
        button_empty.enabled = true;
        buttonList.add(button_empty);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id == 0)
        {
            this.emptyBin();
        }
    }

    @Override
    protected void mouseClicked(int i, int j, int k)
    {
        try
        {
            super.mouseClicked(i, j, k);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    protected void emptyBin()
    {
        PacketHandler.INSTANCE.sendToServer(new MessageEmptyBin(tileX, tileY, tileZ));
    }
}
