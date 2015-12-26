package presentation.commoncontainer;

import javax.swing.*;

/**
 * 这是一个空白块，用于摆放在panel最下方给自动消失提示框提供空间，从而不至于提示框遮住panel上的内容
 */
public class BlankBlock extends JLabel {
    private int width;
    private int height;

    public BlankBlock(){
        this.setOpaque(false);
        this.setSize(50, 50);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
