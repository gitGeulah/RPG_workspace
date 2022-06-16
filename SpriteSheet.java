import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private int[] pixels;
    private BufferedImage image;
    private final int SIZEX;
    private final int SIZEY;    
    private Sprite[] loadedSprites = null;
    private boolean spritesLoaded = false; 
    
    private int spriteSizeX;
        
    public SpriteSheet(BufferedImage sheetImage) {
        image = sheetImage;
        SIZEX = sheetImage.getWidth();
        SIZEY = sheetImage.getHeight();
        
        pixels = new int[SIZEX * SIZEY];
        pixels = sheetImage.getRGB(0, 0, SIZEX, SIZEY, pixels, 0, SIZEX);
    }
    
    public void loadSprites(int spriteSizeX, int spriteSizeY) {
        this.spriteSizeX = spriteSizeX;
                                //Divide the size of the image by the size of the sprite
        loadedSprites = new Sprite[(SIZEX / spriteSizeX) * (SIZEY / spriteSizeY)]; //50
        
        int spriteID = 0;

        for(int y = 0; y < SIZEY; y += spriteSizeY) { // X5
            for(int x = 0; x < SIZEX; x += spriteSizeX) { //X10
                loadedSprites[spriteID] = new Sprite(this, x, y, spriteSizeX, spriteSizeY);
                spriteID++; 
            }
        }
        spritesLoaded = true;
    }

    public Sprite getSprite(int x, int y) {
        
        if(spritesLoaded) {
                //    10       0 + 1    160  /  16 = 10 
                int spriteID = x + y *(SIZEX / spriteSizeX);
                
                if(spriteID < loadedSprites.length) {
                    return loadedSprites[spriteID];
                }
                else {
                    System.out.println("SpriteID of " + spriteID + " is out of the range with a length of " + loadedSprites.length + ".");
                } 

          } else {
                System.out.println("Cannot get a sprite if we have no loaded sprites");
          } 
        
        return null;
    }

    public Sprite[] getLoadedSprites() {
        return loadedSprites;
    }
    
    public int[] getPixels () {
        return pixels;
    }
    
    public BufferedImage getImage() {
        return image;
    }
}
