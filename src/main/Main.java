package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import mouseHandler.MouseHandler;
import shapes.Lists;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class Main {
	public static void main(String[] args) {
		PaintCanvasBase paintCanvas = new PaintCanvas();
		IGuiWindow guiWindow = new GuiWindow(paintCanvas);
		IUiModule uiModule = new Gui(guiWindow);
		ApplicationState appState = new ApplicationState(uiModule);
		Lists lists = new Lists(paintCanvas);
		IJPaintController controller = new JPaintController(uiModule, appState, lists);
		controller.setup();
		MouseHandler MH = new MouseHandler(appState, lists, paintCanvas);
		paintCanvas.addMouseListener(MH);
	}
}
