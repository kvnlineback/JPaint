package mouseHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import commands.CreateShapeCommand;
import commands.ICommand;
import commands.MoveShapeCommand;
import commands.SelectShapeCommand;
import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import shapes.Lists;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter {
	private Point startPoint;
	private Point endPoint;
	private IApplicationState as;
	private PaintCanvasBase pc;
	private Lists lists;

	public MouseHandler(IApplicationState as, Lists lists, PaintCanvasBase pc) {
		this.as = as;
		this.lists = lists;
		this.pc = pc;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		StartAndEndPointMode mode = as.getActiveStartAndEndPointMode();
		endPoint = new Point((int) e.getPoint().getX(), (int) e.getPoint().getY());

		if (mode.equals(StartAndEndPointMode.DRAW)) {
			ICommand CreateShape = new CreateShapeCommand(startPoint, endPoint, as, lists);
			CreateShape.run();
		}
		if (mode.equals(StartAndEndPointMode.SELECT)) {
			ICommand SelectShape = new SelectShapeCommand(startPoint, endPoint, lists, pc);
			SelectShape.run();
		}
		if (mode.equals(StartAndEndPointMode.MOVE)) {
			if (!lists.getSelectedShapesList().isEmpty()) {
				ICommand MoveShape = new MoveShapeCommand(startPoint, endPoint, lists);
				MoveShape.run();
			}
		}
	}
}
