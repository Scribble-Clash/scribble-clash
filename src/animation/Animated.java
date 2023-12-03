package animation;

import java.awt.image.BufferedImage;
import controller.Loader;

public class Animated {
    BufferedImage image;
    private BufferedImage[] swingAnimation;
    Loader load = new Loader();

    public Animated() {
        image = (BufferedImage) load.mainimage();
    }

    public BufferedImage[] swingAnimation() {
        swingAnimation = new BufferedImage[3];
        // swingAnimation[0] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        swingAnimation[0] = (BufferedImage) Loader.loadImage(
                "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\src\\assets\\pedangkanan.png");
        swingAnimation[1] = (BufferedImage) Loader.loadImage(
                "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\src\\assets\\slash.png");
        swingAnimation[2] = (BufferedImage) Loader.loadImage(
                "D:\\#Programing\\Project\\Fight-Scribble\\Fight-ScribbleGit\\scribble-fight\\src\\assets\\pedangbawah.png");
        return swingAnimation;
    }

    public BufferedImage[] shotanimation() {
        swingAnimation = new BufferedImage[3];
        swingAnimation[0] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        swingAnimation[1] = (BufferedImage) image.getSubimage(512, 128, 64, 64);
        swingAnimation[2] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        return swingAnimation;
    }

    public BufferedImage[] arrowshotanimation() {
        swingAnimation = new BufferedImage[3];
        swingAnimation[0] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        swingAnimation[1] = (BufferedImage) image.getSubimage(512, 128, 64, 64);
        swingAnimation[2] = (BufferedImage) image.getSubimage(448, 0, 64, 64);
        return swingAnimation;
    }
}
