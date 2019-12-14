package com.github.Ukasz09;

import com.github.Ukasz09.graphiceUserInterface.sprites.ISpriteGraphic;
import com.github.Ukasz09.graphiceUserInterface.sprites.computer.panes.dialogPanes.errorPane.ErrorKind;

public interface IMonitor extends ISpriteGraphic {

   void showPrintErrorMessage(ErrorKind errorKind);
}
