public class SDKButton extends GUIButton
{
    private Game game;
    private int tileID;
    private boolean isGreen = false;
    
    public SDKButton(Game game, int tileID, Sprite tileSprite, Rectangle rect) {
       super(tileSprite, rect, true);
       this.game = game;
       this.tileID = tileID;
       rect.generateGraphics(0xFFDB3D);
    }
    
    //Call at 60 fps rate.
    public void update(Game game) {
        if(tileID == game.getSelectedTile()) {
            if(!isGreen) {
                rect.generateGraphics(0x67FF3D);
                isGreen = true;
            }
        } else {
            rect.generateGraphics(0xFFDB3D);
            isGreen = false;
        }
    }
    
    @Override
    public void render(RenderHandler renderer, int xZoom, int yZoom, Rectangle interfaceRect) {
        renderer.renderRectangle(rect, interfaceRect, 1, 1, fixed);
        renderer.renderSprite(sprite,
                              rect.x + interfaceRect.x + (xZoom - (xZoom - 1)) * rect.w/2/xZoom,// 0 + 5 + (3 -(3 - 1)) * 48/2/3 = 13
                              rect.y + interfaceRect.y + (yZoom - (yZoom - 1)) * rect.h/2/yZoom,//50 + 5 + (3 -(3 - 1)) * 48/2/3 = 63
                              xZoom - 1,
                              yZoom - 1,
                              fixed);
                              
    }
    
    public void activate() {
        game.changeTile(tileID);
    }

}
