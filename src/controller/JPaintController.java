package controller;

import commands.CommandHistory;
import commands.CopyShapeCommand;
import commands.ICommand;
import commands.PasteShapeCommand;
import commands.RedoCommand;
import commands.UndoCommand;
import commands.UngroupCommand;
import model.interfaces.IApplicationState;
import shapes.Lists;
import view.EventName;
import view.interfaces.IUiModule;
import commands.DeleteShapeCommand;
import commands.GroupCommand;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private Lists lists;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, Lists lists) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.lists = lists;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.COPY, () -> new CopyShapeCommand(lists).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteShapeCommand(lists).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteShapeCommand(lists).run());
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupCommand(lists).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand(lists).run());
        
    }
}
